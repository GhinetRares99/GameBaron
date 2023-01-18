package com.example.GameBaron.Services;

import com.example.GameBaron.Entities.Achievement;
import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Repositories.AchievementRepository;
import com.example.GameBaron.Repositories.GameRepository;
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
public class AchievementServiceTests {

    @InjectMocks
    private AchievementService achievementService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private AchievementRepository achievementRepository;

    @Test
    void getAchievementsTest(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Achievement testAchievement = new Achievement(1, "TestName", "TestDescription", "TestRarity", testGame);
        when(achievementRepository.findAll()).thenReturn(List.of(testAchievement));

        List<Achievement> testResult = achievementService.getAchievements();

        assertThat(testResult).isNotNull();
    }

    @Test
    void addAchievementTest(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Achievement testAchievement = new Achievement("TestName", "TestDescription", "TestRarity", testGame);
        when(gameRepository.findById(testAchievement.getGame().getGameId())).thenReturn(Optional.of(testGame));
        when(achievementRepository.save(testAchievement)).thenReturn(testAchievement);

        Achievement testResult = achievementService.addAchievement(testAchievement);

        assertEquals(testAchievement, testResult);
    }

    @Test
    void addAchievementTest_gameNotFound(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Achievement testAchievement = new Achievement("TestName", "TestDescription", "TestRarity", testGame);

        Exception exception = assertThrows(Exception.class, () -> achievementService.addAchievement(testAchievement));
        assertEquals("This game does not exist!", exception.getMessage());
    }

    @Test
    void updateAchievementTest(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Achievement testAchievement = new Achievement(1, "TestName", "TestDescription", "TestRarity", testGame);
        when(achievementRepository.findById(testAchievement.getAchievementId())).thenReturn(Optional.of(testAchievement));

        testAchievement.setAchievementName("NewAchievementName");
        Achievement testResult = achievementService.updateAchievement(testAchievement.getAchievementId(), "NewAchievementName", "", "");

        assertEquals(testAchievement.getAchievementName(), testResult.getAchievementName());
    }

    @Test
    void updateAchievementTest_updatesAll(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Achievement testAchievement = new Achievement(1, "TestName", "TestDescription", "TestRarity", testGame);
        when(achievementRepository.findById(testAchievement.getAchievementId())).thenReturn(Optional.of(testAchievement));

        testAchievement.setAchievementName("NewAchievementName");
        testAchievement.setAchievementDescription("NewAchievementDescription");
        testAchievement.setAchievementRarity("NewAchievementRarity");
        Achievement testResult = achievementService.updateAchievement(testAchievement.getAchievementId(), "NewAchievementName", "NewAchievementDescription", "NewAchievementRarity");

        assertEquals(testAchievement, testResult);
    }

    @Test
    void updateAchievementTest_notFound(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Achievement testAchievement = new Achievement(1, "TestName", "TestDescription", "TestRarity", testGame);

        Exception exception = assertThrows(Exception.class, () -> achievementService.updateAchievement(testAchievement.getAchievementId(), "NewName", "", ""));

        assertEquals("The specified achievement does not exist!", exception.getMessage());
    }

    @Test
    void deleteAchievementTest(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Achievement testAchievement = new Achievement(1, "TestName", "TestDescription", "TestRarity", testGame);
        when(achievementRepository.existsById(testAchievement.getAchievementId())).thenReturn(true);

        Integer testResult = achievementService.deleteAchievement(testAchievement.getAchievementId());

        assertEquals(testAchievement.getAchievementId(), testResult);
    }

    @Test
    void deleteAchievementTest_notFound(){
        Game testGame = new Game(1, "TestName", "TestGenre", "TestCreator", "TestPublisher", "TestDescription", 0, null, null, null);
        Achievement testAchievement = new Achievement(1, "TestName", "TestDescription", "TestRarity", testGame);

        Exception exception = assertThrows(Exception.class, () -> achievementService.deleteAchievement(testAchievement.getAchievementId()));
        assertEquals("The specified achievement does not exist!", exception.getMessage());
    }
}
