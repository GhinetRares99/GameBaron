package com.example.GameBaron.Controller;

import com.example.GameBaron.Controllers.PossessionController;
import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Entities.Possession;
import com.example.GameBaron.Entities.User;
import com.example.GameBaron.Services.PossessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PossessionController.class)
public class PossessionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PossessionService possessionService;

    @Test
    void possessionGetTest() throws Exception{
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Possession testPossession = new Possession(1, testUser, testGame);
        when(possessionService.getPossessions()).thenReturn(List.of(testPossession));

        mockMvc.perform(get("/GameBaron/Possession/getPossessions").content("application/json")).andExpect(status().isOk());
    }
}
