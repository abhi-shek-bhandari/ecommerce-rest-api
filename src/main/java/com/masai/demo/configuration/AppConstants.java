package com.masai.demo.configuration;

public class AppConstants {
    public static final Integer NORMAL_USER = 2;
    public static final Integer ADMIN_USER = 1;
    public static final String[] PUBLIC_URLS = {
            "/api/v1/auth/**",
            "/v3/api-docs",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/product/viewAll",
            "/product/findByName/{name}",
            "/product/findByCategory/{catId}",
            "/product/findByBrand/{brand}",
            "/product/findByPrice/{price}",
            "/category/viewAll",
            "/category/find/{categoryId}"
    };

    public static final String[] NORMAL_URLS = {
            "/cart/**",
            "/user/getAddressById/{Id}",
            "/user/updateaddress/{addressId}",
            "/user/getalladdress/{userid}",
            "/user/deleteaddress/{userid}",
            "/user/addAddress/{userid}",
            "/user/deleteuser/{userid}",
            "/user/password",
            "/user/email",
            "/user/lastname",
            "/user/firstname",
            "/wallet/updateWallet/{amount}/{walletId}",
            "/promocode/add/{discount}",
            "/order/viewOrdersByUserId/{userId}",
            "/order/cancelOrder/{orderId}/{walletId}",
            "/checkout"


    };

    public static final String[] ADMIN_URLS = {
            "/cart/**",
            "/user/updateUserRole/{makeAdmin}/{userId}/{roleId}",
            "/user/getAddressById/{Id}",
            "/user/updateaddress/{addressId}",
            "/user/getalladdress/{userid}",
            "/user/deleteaddress/{userid}",
            "/user/addAddress/{userid}",
            "/user/deleteuser/{userid}",
            "/user/getallusers",
            "/user/password",
            "/user/email",
            "/user/lastname",
            "/user/firstname",
            "/user/{uid}",
            "/user/add",
            "/wallet/updateWallet/{amount}/{walletId}",
            "/promocode/find/{promo}",
            "/promocode/delete/{Id}",
            "/product/add/{catId}",
            "/order/viewOrdersByUserId/{userId}",
            "/order/viewAllOrders",
            "/order/cancelOrder/{orderId}/{walletId}",
            "/checkout",
            "/category/add",
            "/category/delete/{catId}",
            "/category/updateDesc/{categoryId}",
            "/category/updateName/{categoryId}"


    };
}
