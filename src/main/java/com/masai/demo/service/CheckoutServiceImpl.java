package com.masai.demo.service;

import com.masai.demo.dto.OrderDto;
import com.masai.demo.exception.*;
import com.masai.demo.model.*;
import com.masai.demo.repository.CartDao;
import com.masai.demo.repository.UserDao;
import com.masai.demo.repository.WalletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private PromocodeService promocode;

    @Autowired
    private CartService cartService;

    @Autowired
    private WalletService walletService;

    @Override
    public Integer checkout(Integer userId, Integer addressId, String promo) throws PromocodeException, UserException, CartException, WalletException, AddressException {

        //checking user
        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("Not User Found With User Id: " + userId));


        Wallet wallet = user.getWallet();

        Cart cart = user.getCart();

        Set<Product> products = cart.getProducts();

        //checking for products
        if(products.isEmpty()) throw new CartException("Cart is Empty!!! ");

        //checking for wallet Amount
        if (wallet.getAmount() < cart.getTotalAmount()) throw new WalletException("Wallet Balance is low then Cart Value");

        Promocode promocode = this.promocode.findPromocode(promo);

        Address address = this.userService.getAddressById(addressId);

        //OrderDto Created
        OrderDto orderDto = new OrderDto();

        orderDto.setFirstName(user.getUserFirstName());
        orderDto.setLastName(user.getUserLastName());

        double finalAmount = cart.getTotalAmount() - (cart.getTotalAmount()*(promocode.getDiscountPercentage())/100);
        orderDto.setOrderAmount(finalAmount);

        orderDto.setCity(address.getCity());
        orderDto.setState(address.getState());
        orderDto.setPhone(address.getPhone());
        orderDto.setPincode(address.getPincode());
        orderDto.setLandmark(address.getLandmark());
        orderDto.setOrderCancelled(false);
        orderDto.setPromoCode(promo);
        orderDto.setOrderTimeCreatedOn(LocalDateTime.now());

        Map<String, Double> orderProducts = new HashMap<>();
        for (Product product:products) {
            orderProducts.put(product.getProductName(),product.getSellingPrice());
        }

        orderDto.setProducts(orderProducts);

//        Saving Orders
        Orders orders = this.ordersService.addOrder(orderDto);

        Cart cart1 = this.cartService.removeAllFromCart(cart.getCartId());

        user.getOrders().add(orders);

        walletService.updateAmount(-(orders.getOrderAmount()),wallet.getWalletId());

        this.userDao.save(user);

        return orders.getOrderId();
    }
}
