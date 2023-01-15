package com.example.GameBaron.Controllers;

import com.example.GameBaron.Entities.User;
import com.example.GameBaron.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @RequestMapping(path = "GameBaron/User/getUsers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    @RequestMapping(path = "GameBaron/User/registerUser")
    public void registerUser(@RequestBody User user){
        //System.out.println(user);
        userService.registerUser(user);
    }

    @PutMapping
    @RequestMapping(path = "GameBaron/User/updateUser/{userId}")
    public void updateUser(@PathVariable Integer userId, @RequestParam(required = false) String userName, @RequestParam(required = false) String password, @RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String lastName, @RequestParam(required = false) String email, @RequestParam(required = false) String phoneNumber,
                           @RequestParam(required = false) Integer walletMoney, @RequestParam(required = false) String country, @RequestParam(required = false) String city,
                           @RequestParam(required = false) String streetName){
        userService.updateUser(userId, userName, password, firstName, lastName, email, phoneNumber, walletMoney, country, city, streetName);
    }

    @DeleteMapping
    @RequestMapping(path = "GameBaron/User/deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId){
        userService.deleteUser(userId);
    }
}
