package com.example.GameBaron.Services;

import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Entities.User;
import com.example.GameBaron.Repositories.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Test
    void getGamesTest(){
        Game testGame = new Game("TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        when(gameRepository.findAll()).thenReturn(List.of(testGame));

        List<Game> testResult = gameService.getGames();

        assertThat(testResult).isNotNull();
    }

    @Test
    void addGameTest(){
        Game testGame = new Game("TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        when(gameRepository.save(testGame)).thenReturn(testGame);

        Game testResult = gameService.addGame(testGame);

        assertEquals(testGame, testResult);
    }

    @Test
    void updateUserTest(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        when(gameRepository.findById(testGame.getGameId())).thenReturn(Optional.of(testGame));

        testGame.setGameName("NewName");
        Game testResult = gameService.updateGame(testGame.getGameId(), "NewName", "", "", "", "", 0);

        assertEquals(testGame.getGameName(), testResult.getGameName());
    }

    @Test
    void updateGameTest_updatesAll(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        when(gameRepository.findById(testGame.getGameId())).thenReturn(Optional.of(testGame));

        testGame.setGameName("NewName");
        testGame.setGameGenre("NewGenre");
        testGame.setGameCreator("NewCreator");
        testGame.setGamePublisher("NewPublisher");
        testGame.setGameDescription("NewDescription");
        testGame.setGamePrice(60);
        Game testResult = gameService.updateGame(testGame.getGameId(), "NewName", "NewGenre", "NewCreator", "NewPublisher", "NewDescription", 60);

        assertEquals(testGame, testResult);
    }

    @Test
    void updateGameTest_notFound(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);

        Exception exception = assertThrows(Exception.class, () -> gameService.updateGame(testGame.getGameId(), "NewName", "", "", "", "", 0));
        assertEquals("The specified game does not exist!", exception.getMessage());
    }

    @Test
    void deleteGameTest(){
        Game testGame = new Game(1,"TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        when(gameRepository.existsById(testGame.getGameId())).thenReturn(true);

        Integer testResult = gameService.deleteGame(testGame.getGameId());

        assertEquals(testGame.getGameId(),testResult);
    }

    @Test
    void deleteGameTest_notFound(){
        Game testGame = new Game(1,"TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);

        Exception exception = assertThrows(Exception.class, () -> gameService.deleteGame(testGame.getGameId()));
        assertEquals("The specified game does not exist!", exception.getMessage());
    }
}
