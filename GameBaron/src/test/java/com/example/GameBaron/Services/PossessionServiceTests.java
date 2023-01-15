package com.example.GameBaron.Services;

import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Entities.Possession;
import com.example.GameBaron.Entities.User;
import com.example.GameBaron.Repositories.GameRepository;
import com.example.GameBaron.Repositories.PossessionRepository;
import com.example.GameBaron.Repositories.UserRepository;
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
public class PossessionServiceTests {

    @InjectMocks
    private PossessionService possessionService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PossessionRepository possessionRepository;

    @Test
    void getPossessionsTest(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Possession testPossession = new Possession(1, testUser, testGame);
        when(possessionRepository.findAll()).thenReturn(List.of(testPossession));

        List<Possession> testResult = possessionService.getPossessions();

        assertThat(testResult).isNotNull();
    }

    @Test
    void addPossessionTest(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Possession testPossession = new Possession(testUser, testGame);
        when(userRepository.findById(testPossession.getUser().getUserId())).thenReturn(Optional.of(testUser));
        when(gameRepository.findById(testPossession.getGame().getGameId())).thenReturn(Optional.of(testGame));
        when(possessionRepository.save(testPossession)).thenReturn(testPossession);

        Possession testResult = possessionService.addPossession(testPossession);

        assertEquals(testPossession, testResult);
    }

    @Test
    void addPossessionTest_gameNotFound(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Possession testPossession = new Possession(testUser, testGame);
        when(userRepository.findById(testPossession.getUser().getUserId())).thenReturn(Optional.of(testUser));

        Exception exception = assertThrows(Exception.class, () -> possessionService.addPossession(testPossession));
        assertEquals("The specified game does not exist!", exception.getMessage());
    }

    @Test
    void addPossessionTest_userNotFound(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Possession testPossession = new Possession(testUser, testGame);
        when(gameRepository.findById(testPossession.getGame().getGameId())).thenReturn(Optional.of(testGame));

        Exception exception = assertThrows(Exception.class, () -> possessionService.addPossession(testPossession));
        assertEquals("The specified user does not exist!", exception.getMessage());
    }

    @Test
    void deletePossessionTest(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Possession testPossession = new Possession(1, testUser, testGame);
        when(possessionRepository.existsById(testPossession.getPossessionId())).thenReturn(true);

        Integer testResult = possessionService.deletePossession(testPossession.getPossessionId());

        assertEquals(testPossession.getPossessionId(), testResult);
    }

    @Test
    void deletePossessionTest_notFound(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Possession testPossession = new Possession(1, testUser, testGame);

        Exception exception = assertThrows(Exception.class, () -> possessionService.deletePossession(testPossession.getPossessionId()));
        assertEquals("The specified game possession does not exist!", exception.getMessage());
    }
}
