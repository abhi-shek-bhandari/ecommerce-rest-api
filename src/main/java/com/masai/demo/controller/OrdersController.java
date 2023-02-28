package com.masai.demo.controller;

import com.masai.demo.model.Orders;
import com.masai.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/order/")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/viewOrdersByUserId/{userId}")
    public ResponseEntity<Set<Orders>> viewOrdersByUserIdHandler(@PathVariable Integer userId){

        Set<Orders> orders = this.ordersService.viewOrdersByUserId(userId);

        return new ResponseEntity<>(orders,HttpStatus.FOUND);

    }

    @GetMapping("/viewAllOrders")
    public ResponseEntity<List<Orders>> viewAllOrdersHandler(){

        List<Orders> orders = this.ordersService.viewAllOrders();

        return new ResponseEntity<>(orders,HttpStatus.FOUND);

    }

    @PutMapping("/cancelOrder/{orderId}/{walletId}")
    public ResponseEntity<Orders> cancelOrder(@PathVariable Integer orderId,@PathVariable Integer walletId){

        Orders orders = this.ordersService.cancelOrder(orderId,walletId);

        return new ResponseEntity<>(orders,HttpStatus.OK);

    }

}
