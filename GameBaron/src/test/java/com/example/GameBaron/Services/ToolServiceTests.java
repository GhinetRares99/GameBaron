package com.example.GameBaron.Services;

import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Entities.Tool;
import com.example.GameBaron.Repositories.GameRepository;
import com.example.GameBaron.Repositories.ToolRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ToolServiceTests {

    @InjectMocks
    private ToolService toolService;

    @Mock
    private ToolRepository toolRepository;

    @Mock
    private GameRepository gameRepository;

    @Test
    void getToolsTest(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Tool testTool = new Tool(1,"TestName", "TestDescription", "TestVersion", testGame);
        when(toolRepository.findAll()).thenReturn(List.of(testTool));

        List<Tool> testResult = toolService.getTools();

        assertThat(testResult).isNotNull();
    }


    @Test
    void addToolTest(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Tool testTool = new Tool("TestName", "TestDescription", "TestVersion", testGame);
        when(gameRepository.findById(testTool.getGame().getGameId())).thenReturn(Optional.of(testGame));
        when(toolRepository.save(testTool)).thenReturn(testTool);

        Tool testResult = toolService.addTool(testTool);

        assertEquals(testTool, testResult);
    }

    @Test
    void addToolTest_gameNotFound(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Tool testTool = new Tool("TestName", "TestDescription", "TestVersion", testGame);

        Exception exception = assertThrows(Exception.class, () -> toolService.addTool(testTool));
        assertEquals("This game does not exist!", exception.getMessage());
    }

    @Test
    void updateToolTest(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Tool testTool = new Tool(1,"TestName", "TestDescription", "TestVersion", testGame);
        when(toolRepository.findById(testTool.getToolId())).thenReturn(Optional.of(testTool));

        testTool.setToolName("NewToolName");
        Tool testResult = toolService.updateTool(testTool.getToolId(),"NewToolName", "", "");

        assertEquals(testTool.getToolName(), testResult.getToolName());
    }

    @Test
    void updateToolIdTest(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Tool testTool = new Tool(1,"TestName", "TestDescription", "TestVersion", testGame);

        Integer newId = 2;

        testTool.setToolId(newId);
        assertEquals(newId, testTool.getToolId());
    }

    @Test
    void updateToolTest_updatesAll(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Tool testTool = new Tool(1,"TestName", "TestDescription", "TestVersion", testGame);
        when(toolRepository.findById(testTool.getToolId())).thenReturn(Optional.of(testTool));

        testTool.setToolName("NewToolName");
        testTool.setToolDescription("NewToolDescription");
        testTool.setToolVersion("NewToolVersion");
        Tool testResult = toolService.updateTool(testTool.getToolId(),"NewToolName", "NewToolDescription", "NewToolVersion");

        assertEquals(testTool, testResult);
    }

    @Test
    void updateToolTest_notFound(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Tool testTool = new Tool(1,"TestName", "TestDescription", "TestVersion", testGame);

        Exception exception = assertThrows(Exception.class, () -> toolService.updateTool(testTool.getToolId(), "NewName", "", ""));
        assertEquals("The specified tool does not exist!", exception.getMessage());
    }

    @Test
    void deleteToolTest(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Tool testTool = new Tool(1,"TestName", "TestDescription", "TestVersion", testGame);
        when(toolRepository.existsById(testTool.getToolId())).thenReturn(true);

        Integer testResult = toolService.deleteTool(testTool.getToolId());

        assertEquals(testTool.getToolId(), testResult);
    }

    @Test
    void deleteToolTest_notFound(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Tool testTool = new Tool(1,"TestName", "TestDescription", "TestVersion", testGame);

        Exception exception = assertThrows(Exception.class, () -> toolService.deleteTool(testTool.getToolId()));
        assertEquals("The specified tool does not exist!", exception.getMessage());
    }
}
