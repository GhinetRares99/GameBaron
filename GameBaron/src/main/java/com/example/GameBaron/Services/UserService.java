package com.example.GameBaron.Services;

import com.example.GameBaron.Entities.User;
import com.example.GameBaron.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User registerUser(User user) {
        Optional<User> userByUserName = userRepository.findUserByUserName(user.getUserName());
        if(userByUserName.isPresent())
        {
            throw new IllegalStateException("Your username must be unique!");
        }
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User updateUser(Integer userId, String userName, String password, String firstName, String lastName, String email, String phoneNumber, Integer walletMoney, String country, String city, String streetName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("The specified user does not exist!"));

        if (userName != null && userName.length() > 0 && !Objects.equals(user.getUserName(), userName)) {
            user.setUserName(userName);
        }

        if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) {
            user.setPassword(password);
        }

        if (firstName != null && firstName.length() > 0 && !Objects.equals(user.getFirstName(), firstName)) {
            user.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(user.getLastName(), lastName)) {
            user.setLastName(lastName);
        }

        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            user.setEmail(email);
        }

        if (phoneNumber != null && phoneNumber.length() > 0 && !Objects.equals(user.getPhoneNumber(), phoneNumber)) {
            user.setPhoneNumber(phoneNumber);
        }

        if (walletMoney != null && walletMoney >= 0 && !Objects.equals(user.getWalletMoney(), walletMoney)) {
            user.setWalletMoney(walletMoney);
        }

        if (country != null && country.length() > 0 && !Objects.equals(user.getCountry(), country)) {
            user.setCountry(country);
        }

        if (city != null && city.length() > 0 && !Objects.equals(user.getCity(), city)) {
            user.setCity(city);
        }

        if (streetName != null && streetName.length() > 0 && !Objects.equals(user.getStreetName(), streetName)) {
            user.setStreetName(streetName);
        }

        return user;
    }

    public Integer deleteUser(Integer userId) {
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("The specified user does not exist!");
        }
        userRepository.deleteById(userId);
        return userId;
    }

}
