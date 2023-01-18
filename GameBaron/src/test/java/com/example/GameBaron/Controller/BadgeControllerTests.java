package com.example.GameBaron.Controller;

import com.example.GameBaron.Controllers.BadgeController;
import com.example.GameBaron.Entities.Badge;
import com.example.GameBaron.Entities.User;
import com.example.GameBaron.Services.BadgeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BadgeController.class)
public class BadgeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BadgeService badgeService;

    @Test
    void badgeGetTest() throws Exception{
        User testUser = new User(1, "TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Badge testBadge = new Badge(1,"TestName", "TestDescription", testUser);
        when(badgeService.getBadges()).thenReturn(List.of(testBadge));

        mockMvc.perform(get("/GameBaron/Badge/getBadges").content("application/json")).andExpect(status().isOk());
    }

//    @Test
//    void badgeAddTest() throws Exception{
//        User testUser = new User(1, "TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
//        Badge testBadge = new Badge(1,"TestName", "TestDescription", testUser);
//        when(badgeService.addBadge(any())).thenReturn(testBadge);
//
//        mockMvc.perform(post("/GameBaron/Badge/addBadge").content("application/json").contentType(objectMapper.writeValueAsString(testBadge))).andExpect(status().isOk());
//    }

}
