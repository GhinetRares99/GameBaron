package com.example.GameBaron.Services;

import com.example.GameBaron.Controllers.UserController;
import com.example.GameBaron.Entities.User;
import com.example.GameBaron.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void getUsersTest(){
        User testUser = new User("TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        when(userRepository.findAll()).thenReturn(List.of(testUser));

        List<User> testResult = userService.getUsers();

        assertThat(testResult).isNotNull();
    }

    @Test
    void registerUserTest(){
        //Arrange
        User testUser = new User("TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        when(userRepository.save(testUser)).thenReturn(testUser);

        //Act
        User testResult = userService.registerUser(testUser);

        //Assert
        assertEquals(testUser, testResult);
    }

    @Test
    void registerUserTest_duplicateName(){
        User testUser = new User(1, "TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        User testUser2 = new User("TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        when(userRepository.findUserByUserName(testUser2.getUserName())).thenReturn(Optional.of(testUser));

        Exception exception = assertThrows(Exception.class, () -> userService.registerUser(testUser2));
        assertEquals("Your username must be unique!", exception.getMessage());
    }

    @Test
    void updateUserTest(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        when(userRepository.findById(testUser.getUserId())).thenReturn(Optional.of(testUser));

        testUser.setUserName("NewName");
        User testResult = userService.updateUser(testUser.getUserId(), "NewName", "", "", "", "", "", 0, "", "", "");

        assertEquals(testUser.getUserName(), testResult.getUserName());
    }

    @Test
    void updateUserIdTest(){
        User testUser = new User(1, "TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Integer newId = 2;

        testUser.setUserId(newId);
        assertEquals(newId, testUser.getUserId());
    }

    @Test
    void updateUserTest_updatesAll(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        when(userRepository.findById(testUser.getUserId())).thenReturn(Optional.of(testUser));

        testUser.setUserName("NewName");
        testUser.setPassword("NewPassword");
        testUser.setFirstName("NewFN");
        testUser.setLastName("NewLN");
        testUser.setEmail("NewEmail");
        testUser.setPhoneNumber("NewPhone");
        testUser.setWalletMoney(1);
        testUser.setCountry("NewCountry");
        testUser.setCity("NewCity");
        testUser.setStreetName("NewStreetName");
        User testResult = userService.updateUser(testUser.getUserId(), "NewName", "NewPassword", "NewFN", "NewLN", "NewEmail", "NewPhone", 1, "NewCountry", "NewCity", "NewStreetName");
        assertEquals(testUser, testResult);
    }

    @Test
    void updateUserTest_notFound(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);

        Exception exception = assertThrows(Exception.class, () -> userService.updateUser(testUser.getUserId(), "NewName", "", "", "", "", "", 0, "", "", ""));
        assertEquals("The specified user does not exist!", exception.getMessage());
    }

    @Test
    void deleteUserTest(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        when(userRepository.existsById(testUser.getUserId())).thenReturn(true);

        Integer testResult = userService.deleteUser(testUser.getUserId());
        assertEquals(testUser.getUserId(),testResult);
    }

    @Test
    void deleteUserTest_notFound(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);

        Exception exception = assertThrows(Exception.class, () -> userService.deleteUser(testUser.getUserId()));
        assertEquals("The specified user does not exist!", exception.getMessage());
    }

}
