package com.masai.demo.service;

import com.masai.demo.dto.OrderDto;
import com.masai.demo.exception.OrdersException;
import com.masai.demo.exception.UserException;
import com.masai.demo.model.Orders;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Set;

public interface OrdersService {
    public Orders addOrder(OrderDto orderDto)throws OrdersException;
    public Set<Orders> viewOrdersByUserId(Integer userId)throws UserException, OrdersException;
    public List<Orders> viewAllOrders()throws OrdersException;
    public Orders cancelOrder(Integer orderId, Integer walletId)throws OrdersException;
}
