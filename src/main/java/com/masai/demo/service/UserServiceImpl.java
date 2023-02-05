package com.masai.demo.service;

import com.masai.demo.dto.AddressDto;
import com.masai.demo.dto.UserDto;
import com.masai.demo.exception.AddressException;
import com.masai.demo.exception.UserException;
import com.masai.demo.model.Address;
import com.masai.demo.model.User;
import com.masai.demo.repository.AddressDao;
import com.masai.demo.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddressDao addressDao;

    @Override
    public User createUser(UserDto userDto) {

        return this.userDao.save(this.dtoToUser(userDto));

    }

    @Override
    public UserDto updateUserFirstName(String FirstName, Integer userId) {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        user.setUserFirstName(FirstName);
        this.userDao.save(user);

        return this.UserToDto(user);

    }

    @Override
    public UserDto updateUserLastName(String LastName, Integer userId) {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        user.setUserLastName(LastName);
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

    @Override
    public User deleteUser(Integer userId) {
        User deletedUser = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));
        return deletedUser;
    }

    @Override
    public UserDto addAddress(AddressDto addressDto, Integer userId) throws UserException {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        Set<Address> address = user.getAddresses();
        address.add(this.dtoToAddress(addressDto));
        user.setAddresses(address);
        this.userDao.save(user);

        return this.UserToDto(user);
    }

    @Override
    public Address deleteAddress(Integer id) throws AddressException {

        Address address = this.addressDao.findById(id)
                .orElseThrow(() -> new AddressException("No Address Found with Address Id "+ id));

        this.addressDao.delete(address);

        return address;
    }

    @Override
    public Set<AddressDto> getAllUserAddress(Integer userId)throws UserException, AddressException {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        Set<Address> addressList = user.getAddresses();

        Set<AddressDto> addressDos = new HashSet<>();

        if (addressDos.isEmpty()) throw new AddressException("No Address Found with User Id "+ userId);

        for (Address address : addressList) {
            addressDos.add(this.addressToDto(address));
        }

        return addressDos;
    }

    @Override
    public AddressDto updateAddressByAddressId(AddressDto addressDto, Integer id) throws AddressException {

        Address address = this.addressDao.findById(id)
                .orElseThrow( () -> new AddressException("No Address Found with Address Id "+ id));

        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setPhone(addressDto.getPhone());
        address.setPincode(addressDto.getPincode());
        address.setLandmark(addressDto.getLandmark());

        this.addressDao.save(address);

        return this.addressToDto(address);
    }

    private User dtoToUser(UserDto userDto){

        User user = new User();
        user.setUserFirstName(userDto.getUserFirstName());
        user.setUserLastName(userDto.getUserLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setAddresses(userDto.getAddresses());

        return user;
    }

    private UserDto UserToDto(User user){

        UserDto userDto = new UserDto();
        userDto.setUserFirstName(user.getUserFirstName());
        userDto.setUserLastName(user.getUserLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhone(user.getPhone());
        userDto.setAddresses(user.getAddresses());

        return userDto;
    }

    private AddressDto addressToDto(Address address){

        AddressDto addressDto = new AddressDto();
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setPhone(address.getPhone());
        addressDto.setPincode(address.getPincode());
        addressDto.setLandmark(address.getLandmark());

        return addressDto;
    }

    private Address dtoToAddress(AddressDto addressDto){

        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setPhone(addressDto.getPhone());
        address.setPincode(addressDto.getPincode());
        address.setLandmark(addressDto.getLandmark());

        return address;
    }
}
