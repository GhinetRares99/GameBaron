package com.example.GameBaron.Controller;

import com.example.GameBaron.Controllers.GameController;
import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Services.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GameController.class)
public class GameControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GameService gameService;

    @MockBean
    private GameController gameController;

    @Test
    void gameGetTest() throws Exception {
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        when(gameService.getGames()).thenReturn(List.of(testGame));

        ResultActions testResult = mockMvc.perform(get("/GameBaron/Game/getGames").content("application/json").contentType(objectMapper.writeValueAsString(testGame))).andExpect(status().isOk());
        assertThat(testResult).isNotNull();
    }
}
