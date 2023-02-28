package com.masai.demo.service;

import com.masai.demo.dto.OrderDto;
import com.masai.demo.exception.OrdersException;
import com.masai.demo.exception.UserException;
import com.masai.demo.model.Orders;
import com.masai.demo.model.User;
import com.masai.demo.model.Wallet;
import com.masai.demo.repository.OrdersDao;
import com.masai.demo.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;
    
    @Autowired
    private WalletService walletService;

    @Autowired
    private UserDao userDao;

    @Override
    public Orders addOrder(OrderDto orderDto) throws OrdersException {
        Orders orders = this.dtoToOrder(orderDto);
        this.ordersDao.save(orders);

        return orders;
    }

    @Override
    public Set<Orders> viewOrdersByUserId(Integer userId) throws UserException, OrdersException {
        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        Set<Orders> orders = user.getOrders();

        if (orders.isEmpty()) throw new OrdersException("No Order Found with user Id " + userId);

        return orders;
    }

    @Override
    public List<Orders> viewAllOrders() throws OrdersException {

        List<Orders> ordersList = this.ordersDao.findAll();

        if (ordersList.isEmpty())  throw new OrdersException("No Orders made till now");

        return ordersList;
    }

    @Override
    public Orders cancelOrder(Integer orderId, Integer walletId) throws OrdersException {

        Orders orders = this.ordersDao.findById(orderId)
                .orElseThrow(() -> new OrdersException("No Order Found with Order Id " + orderId));

        Wallet wallet = this.walletService.updateAmount(orders.getOrderAmount(), walletId);

        orders.setOrderCancelled(true);

        return this.ordersDao.save(orders);
    }


    public Orders dtoToOrder(OrderDto orderDto){

        Orders orders = new Orders();
        orders.setFirstName(orderDto.getFirstName());
        orders.setLastName(orderDto.getLastName());
        orders.setOrderAmount(orderDto.getOrderAmount());
        orders.setCity(orderDto.getCity());
        orders.setState(orderDto.getState());
        orders.setPhone(orderDto.getPhone());
        orders.setPincode(orderDto.getPincode());
        orders.setLandmark(orderDto.getLandmark());
        orders.setOrderCancelled(orderDto.getOrderCancelled());
        orders.setPromoCode(orderDto.getPromoCode());
        orders.setOrderTimeCreatedOn(orderDto.getOrderTimeCreatedOn());
        orders.setProducts(orderDto.getProducts());

        return orders;
    }
}
