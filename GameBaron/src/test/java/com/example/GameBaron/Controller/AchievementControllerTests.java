package com.example.GameBaron.Controller;

import com.example.GameBaron.Controllers.AchievementController;
import com.example.GameBaron.Entities.Achievement;
import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Services.AchievementService;
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

@WebMvcTest(controllers = AchievementController.class)
public class AchievementControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AchievementService achievementService;

    @Test
    void achievementGetTest() throws Exception{
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Achievement testAchievement = new Achievement(1, "TestName", "TestDescription", "TestRarity", testGame);
        when(achievementService.getAchievements()).thenReturn(List.of(testAchievement));

        mockMvc.perform(get("/GameBaron/Achievement/getAchievements").content("application/json")).andExpect(status().isOk());
    }

//    @Test
//    void achievementAddTest() throws Exception{
//        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
//        Achievement testAchievement = new Achievement(1, "TestName", "TestDescription", "TestRarity", testGame);
//        when(achievementService.addAchievement(any())).thenReturn(testAchievement);
//
//        mockMvc.perform(post("/GameBaron/Achievement/addAchievement").content("application/json").contentType(objectMapper.writeValueAsString(testAchievement))).andExpect(status().isOk());
//
//    }
}
