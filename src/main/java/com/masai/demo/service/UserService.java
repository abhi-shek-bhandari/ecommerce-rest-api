package com.masai.demo.service;

import com.masai.demo.dto.UserDto;
import com.masai.demo.model.User;

import java.util.List;

public interface UserService {
    User createUser(UserDto userDto);
    UserDto updateUserName(String Name, Integer userId);
    UserDto updateUserEmail(String email, Integer userId);
    UserDto updateUserPassword(String Password, Integer userId);
    UserDto findUser(Integer userId);
    List<User> getListOfAllUsers();
}
