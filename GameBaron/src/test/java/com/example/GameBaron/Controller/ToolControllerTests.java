package com.example.GameBaron.Controller;

import com.example.GameBaron.Controllers.ToolController;
import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Entities.Tool;
import com.example.GameBaron.Services.ToolService;
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

@WebMvcTest(controllers = ToolController.class)
public class ToolControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ToolService toolService;

    @Test
    void tooGetTest() throws Exception{
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Tool testTool = new Tool(1,"TestName", "TestDescription", "TestVersion", testGame);
        when(toolService.getTools()).thenReturn(List.of(testTool));

        mockMvc.perform(get("/GameBaron/Tool/getTools").content("application/json")).andExpect(status().isOk());
    }
}
