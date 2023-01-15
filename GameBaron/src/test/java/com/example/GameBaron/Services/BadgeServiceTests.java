package com.example.GameBaron.Services;

import com.example.GameBaron.Entities.Badge;
import com.example.GameBaron.Entities.User;
import com.example.GameBaron.Repositories.BadgeRepository;
import com.example.GameBaron.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BadgeServiceTests {

    @InjectMocks
    private BadgeService badgeService;

    @Mock
    private BadgeRepository badgeRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void getBadgesTest(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Badge testBadge = new Badge(1,"TestName", "TestDescription", testUser);
        when(badgeRepository.findAll()).thenReturn(List.of(testBadge));

        List<Badge> testResult = badgeService.getBadges();
        assertThat(testResult).isNotNull();
    }

    @Test
    void addBadgeTest(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Badge testBadge = new Badge("TestName", "TestDescription", testUser);
        when(userRepository.findById(testBadge.getUser().getUserId())).thenReturn(Optional.of(testUser));
        when(badgeRepository.save(testBadge)).thenReturn(testBadge);

        Badge testResult = badgeService.addBadge(testBadge);

        assertEquals(testBadge, testResult);
    }

    @Test
    void addBadgeTest_userNotFound(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Badge testBadge = new Badge("TestName", "TestDescription", testUser);

        Exception exception = assertThrows(Exception.class, () -> badgeService.addBadge(testBadge));
        assertEquals("This user does not exist!", exception.getMessage());
    }

    @Test
    void updateBadgeTest(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Badge testBadge = new Badge(1,"TestName", "TestDescription", testUser);
        when(badgeRepository.findById(testBadge.getBadgeId())).thenReturn(Optional.of(testBadge));

        testBadge.setBadgeName("NewBadgeName");
        Badge testResult = badgeService.updateBadge(testBadge.getBadgeId(),"", "");

        assertEquals(testBadge.getBadgeName(), testResult.getBadgeName());
    }

    @Test
    void updateBadgeIdTest(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Badge testBadge = new Badge(1,"TestName", "TestDescription", testUser);

        Integer newId = 2;

        testBadge.setBadgeId(newId);
        assertEquals(newId, testBadge.getBadgeId());
    }

    @Test
    void updateBadgeTest_updatesAll(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Badge testBadge = new Badge(1,"TestName", "TestDescription", testUser);
        when(badgeRepository.findById(testBadge.getBadgeId())).thenReturn(Optional.of(testBadge));

        testBadge.setBadgeName("NewBadgeName");
        testBadge.setBadgeDescription("NewBadgeDescription");
        Badge testResult = badgeService.updateBadge(testBadge.getBadgeId(),"NewBadgeName", "NewBadgeDescription");

        assertEquals(testBadge, testResult);
    }

    @Test
    void updateBadgeTest_notFound(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Badge testBadge = new Badge(1,"TestName", "TestDescription", testUser);

        Exception exception = assertThrows(Exception.class, () -> badgeService.updateBadge(testBadge.getBadgeId(), "NewName", ""));
        assertEquals("The specified badge does not exist!", exception.getMessage());
    }

    @Test
    void deleteBadgeTest(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Badge testBadge = new Badge(1,"TestName", "TestDescription", testUser);
        when(badgeRepository.existsById(testBadge.getBadgeId())).thenReturn(true);

        Integer testResult = badgeService.deleteBadge(testBadge.getBadgeId());

        assertEquals(testBadge.getBadgeId(), testResult);
    }

    @Test
    void deleteBadgeTest_notFound(){
        User testUser = new User(1,"TestName", "TestPassword","TestFN", "TestLN", "TestEmail", "TestPhone", 0, "TestCountry", "TestCity", "TestStreet", null, null);
        Badge testBadge = new Badge(1,"TestName", "TestDescription", testUser);

        Exception exception = assertThrows(Exception.class, () -> badgeService.deleteBadge(testBadge.getBadgeId()));
        assertEquals("The specified badge does not exist!", exception.getMessage());
    }
}
