package com.masai.demo.service;

import com.masai.demo.configuration.AppConstants;
import com.masai.demo.dto.AddressDto;
import com.masai.demo.dto.UserDto;
import com.masai.demo.exception.AddressException;
import com.masai.demo.exception.RoleException;
import com.masai.demo.exception.UserException;
import com.masai.demo.model.*;
import com.masai.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private CartDao cartDao;

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserDto userDto, Integer roleId) {

        User user = this.dtoToUser(userDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Cart cart = new Cart();
//
        cart.setUser(user);

        user.setCart(cart);

        Wallet wallet = new Wallet();

        wallet.setUser(user);

        user.setWallet(wallet);

        Role role = this.roleDao.findById(roleId).get();

        user.getRoles().add(role);

        return this.userDao.save(user);

    }

    @Override
    public User registerUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Cart cart = new Cart();

        cart.setUser(user);

        user.setCart(cart);

        Wallet wallet = new Wallet();

        wallet.setUser(user);

        user.setWallet(wallet);

        Role role = this.roleDao.findById(AppConstants.NORMAL_USER).get();

        user.getRoles().add(role);

        return this.userDao.save(user);

    }

    @Override
    public User updateUserFirstName(String FirstName, Integer userId) {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        user.setUserFirstName(FirstName);
        this.userDao.save(user);

        return user;

    }

    @Override
    public User updateUserLastName(String LastName, Integer userId) {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        user.setUserLastName(LastName);
        this.userDao.save(user);

        return user;
    }

    @Override
    public User updateUserEmail(String email, Integer userId) {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        user.setEmail(email);

        this.userDao.save(user);

        return user;
    }

    @Override
    public User updateUserPassword(String Password, Integer userId) {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        user.setPassword(Password);
        this.userDao.save(user);

        return user;
    }

    @Override
    public User findUser(Integer userId) {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        return user;
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
    public User updateUserRole(Boolean makeAdmin, Integer userId, Integer roleId) {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id " + userId));

        Set<Role> roleSet = user.getRoles();

        Role role = this.roleDao.findById(roleId)
                .orElseThrow(() -> new RoleException("Role Not Found"));

        if(makeAdmin == true){

            for (Role role1 : roleSet) {
                if(role1.getName() == role.getName()) return user;
            }

            user.getRoles().add(role);

        }
        else{
            for (Role role1 : roleSet) {
                if(role1.getName() == role.getName()) user.getRoles().remove(role);
            }

        }

        return this.userDao.save(user);
    }

    @Override
    public User addAddress(AddressDto addressDto, Integer userId) throws UserException {

        User user = this.userDao.findById(userId)
                .orElseThrow(() -> new UserException("No User Found with user Id "+ userId));

        Set<Address> address = user.getAddresses();

        Address address1 = this.dtoToAddress(addressDto);
        address.add(address1);
        user.setAddresses(address);

        this.addressDao.save(address1);

        return user;
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

        if (addressList.isEmpty()) throw new AddressException("No Address Found with User Id "+ userId);

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

    @Override
    public Address getAddressById(Integer Id) throws AddressException {

        Address address = this.addressDao.findById(Id)
                .orElseThrow( () -> new AddressException("No Address Found with Address Id "+ Id));

        return address;
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
