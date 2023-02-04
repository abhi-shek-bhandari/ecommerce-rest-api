package com.masai.demo.service;

import com.masai.demo.dto.AddressDto;
import com.masai.demo.dto.UserDto;
import com.masai.demo.exception.AddressException;
import com.masai.demo.exception.UserException;
import com.masai.demo.model.Address;
import com.masai.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User createUser(UserDto userDto);
    UserDto updateUserFirstName(String FirstName, Integer userId);
    UserDto updateUserLastName(String LastName, Integer userId);
    UserDto updateUserEmail(String email, Integer userId);
    UserDto updateUserPassword(String Password, Integer userId);
    UserDto findUser(Integer userId);
    List<User> getListOfAllUsers();
    User deleteUser(Integer userId);

    UserDto addAddress(AddressDto addressDto, Integer userId)throws AddressException, UserException;
    Address deleteAddress(Integer id)throws AddressException;
    Set<AddressDto> getAllUserAddress(Integer userId)throws AddressException;
    AddressDto updateAddressByAddressId(AddressDto addressDto, Integer id)throws AddressException;
}
