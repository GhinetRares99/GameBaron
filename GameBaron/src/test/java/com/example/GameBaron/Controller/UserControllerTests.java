package com.example.GameBaron.Controller;

import com.example.GameBaron.Controllers.UserController;
import com.example.GameBaron.Entities.User;
import com.example.GameBaron.Repositories.UserRepository;
import com.example.GameBaron.Services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    @Test
    void userGetTest() throws Exception {
        User testUser = new User(1, "TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        when(userService.getUsers()).thenReturn(List.of(testUser));

        mockMvc.perform(get("/GameBaron/User/getUsers").contentType("application/json")).andExpect(status().isOk());
    }

    @Test
    void userRegisterTest() throws Exception{
        User testUser = new User(1, "TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        when(userService.registerUser(any())).thenReturn(testUser);

        mockMvc.perform(post("/GameBaron/User/registerUser").contentType("application/json").content(objectMapper.writeValueAsString(testUser))).andExpect(status().isOk());
    }

}
