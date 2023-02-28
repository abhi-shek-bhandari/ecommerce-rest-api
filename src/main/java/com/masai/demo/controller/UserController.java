package com.masai.demo.controller;

import com.masai.demo.dto.AddressDto;
import com.masai.demo.dto.UserDto;
import com.masai.demo.model.Address;
import com.masai.demo.model.User;
import com.masai.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> createUserHandler(@Valid @RequestBody UserDto userDto) {

        User savedUser = this.userService.registerUser(userDto);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    @GetMapping("/{uid}")
    public ResponseEntity<User> findUserHandler(@PathVariable Integer uid){
        User user = this.userService.findUser(uid);

        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @PutMapping("/firstname")
    public ResponseEntity<User> updateUserFirstNameHandler(@RequestParam(name = "firstname") String FirstName,@RequestParam(name = "id") Integer userId ){
        User user = this.userService.updateUserFirstName(FirstName,userId);

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @PutMapping("/lastname")
    public ResponseEntity<User> updateUserLastNameHandler(@RequestParam(name = "lastname") String lastName,@RequestParam(name = "id") Integer userId ){
        User user = this.userService.updateUserLastName(lastName,userId);

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @PutMapping("/email")
    public ResponseEntity<User> updateUserEmailHandler(@RequestParam(name = "email") String email,@RequestParam(name = "id") Integer userId ){
        User user = this.userService.updateUserEmail(email,userId);

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }


    @PutMapping("/password")
    public ResponseEntity<User> updateUserPasswordHandler(@RequestParam(name = "password") String password,@RequestParam(name = "id") Integer userId ){
        User user = this.userService.updateUserPassword(password,userId);

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<User>> getListOfAllUsersHandler(){

        List<User> listOfAllUsers = this.userService.getListOfAllUsers();

        return new ResponseEntity<>(listOfAllUsers,HttpStatus.FOUND);
    }

    @DeleteMapping("/deleteuser/{userid}")
    public ResponseEntity<User> deleteUserHandler(@PathVariable Integer userid){

        User user = this.userService.deleteUser(userid);

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @PostMapping("/addAddress/{userid}")
    public ResponseEntity<User> addAddressHandler(@RequestBody AddressDto addressDto,@PathVariable(name = "userid") Integer userid){
        User user = this.userService.addAddress(addressDto,userid);

        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteaddress/{userid}")
    public ResponseEntity<Address> deleteAddressHandler(@PathVariable(name = "userid") Integer userid){
        Address address = this.userService.deleteAddress(userid);

        return new ResponseEntity<>(address,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getalladdress/{userid}")
    public ResponseEntity<Set<AddressDto>> getAllUserAddressHandler(@PathVariable(name = "userid") Integer userid){
        Set<AddressDto> allUserAddress = this.userService.getAllUserAddress(userid);

        return new ResponseEntity<>(allUserAddress,HttpStatus.FOUND);
    }

    @PutMapping("/updateaddress/{addressId}")
    public ResponseEntity<AddressDto> updateAddressByAddressIdHandler(@RequestBody AddressDto addressDto, @PathVariable(name = "addressId") Integer addressId){
        AddressDto addressDto1 = this.userService.updateAddressByAddressId(addressDto, addressId);

        return new ResponseEntity<>(addressDto1,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAddressById/{Id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer Id){

        Address addressById = this.userService.getAddressById(Id);

        return new ResponseEntity<>(addressById,HttpStatus.FOUND);

    }

    @PutMapping("/updateUserRole/{makeAdmin}/{userId}/{roleId}")
    public ResponseEntity<User> updateUserRoleHandler(
            @PathVariable("makeAdmin") Boolean makeAdmin
            ,@PathVariable("userId") Integer userId,
            @PathVariable("roleId") Integer roleId)
    {
        User user = this.userService.updateUserRole(makeAdmin, userId, roleId);

        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }
}
