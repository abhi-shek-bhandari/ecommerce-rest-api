package com.masai.demo.service;

import com.masai.demo.dto.UserDto;
import com.masai.demo.exception.UserException;
import com.masai.demo.model.User;
import com.masai.demo.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User createUser(UserDto userDto) {

        return this.userDao.save(this.dtoToUser(userDto));

    }

    @Override
    public UserDto updateUserName(String Name, Integer userId) {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        user.setUser_name(Name);
        this.userDao.save(user);

        return this.UserToDto(user);

    }

    @Override
    public UserDto updateUserEmail(String email, Integer userId) {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        user.setEmail(email);

        this.userDao.save(user);

        return this.UserToDto(user);
    }

    @Override
    public UserDto updateUserPassword(String Password, Integer userId) {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        user.setPassword(Password);
        this.userDao.save(user);

        return this.UserToDto(user);
    }

    @Override
    public UserDto findUser(Integer userId) {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        return this.UserToDto(user);
    }

    @Override
    public List<User> getListOfAllUsers() {

        List<User> userList = this.userDao.findAll();

        if (userList.size() == 0) throw new UserException("No User Founded");

        return userList;
    }

<<<<<<< HEAD
    @Override
    public User deleteUser(Integer userId) {
        User deletedUser = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));
        return deletedUser;
    }

=======
>>>>>>> 86f3e830b822613583154b7a4fb6995679c28ee9
    private User dtoToUser(UserDto userDto){

        User user = new User();
        user.setUser_name(userDto.getUser_name());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }

    private UserDto UserToDto(User user){

        UserDto userDto = new UserDto();
        userDto.setUser_name(user.getUser_name());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(userDto.getPassword());

        return userDto;
    }
}
