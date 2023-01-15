package com.example.GameBaron.Services;

import com.example.GameBaron.Entities.Achievement;
import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Repositories.AchievementRepository;
import com.example.GameBaron.Repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final GameRepository gameRepository;

    @Autowired
    public AchievementService(AchievementRepository achievementRepository, GameRepository gameRepository) {
        this.achievementRepository = achievementRepository;
        this.gameRepository = gameRepository;
    }

    public List<Achievement> getAchievements(){
        return achievementRepository.findAll();
    }

    public Achievement addAchievement(Achievement achievement){
        Optional<Game> findGameByGameId = gameRepository.findById(achievement.getGame().getGameId());
        if(findGameByGameId.isEmpty()){
            throw new IllegalStateException("This game does not exist!");
        }
        achievementRepository.save(achievement);
        return achievement;
    }

    @Transactional
    public Achievement updateAchievement(Integer achievementId, String achievementName, String achievementDescription, String achievementRarity){
        Achievement achievement = achievementRepository.findById(achievementId).orElseThrow(() -> new IllegalStateException("The specified achievement does not exist!"));

        if(achievementName != null && achievementName.length() > 0 && !Objects.equals(achievement.getAchievementName(), achievementName)){
            achievement.setAchievementName(achievementName);
        }

        if(achievementDescription != null && achievementDescription.length() > 0 && !Objects.equals(achievement.getAchievementDescription(), achievementDescription)){
            achievement.setAchievementDescription(achievementDescription);
        }

        if(achievementRarity != null && achievementRarity.length() > 0 && !Objects.equals(achievement.getAchievementRarity(), achievementRarity)){
            achievement.setAchievementRarity(achievementRarity);
        }
        return achievement;
    }

    public Integer deleteAchievement(Integer achievementId){
        boolean exists = achievementRepository.existsById(achievementId);
        if(!exists){
            throw new IllegalStateException("The specified achievement does not exist!");
        }
        achievementRepository.deleteById(achievementId);
        return achievementId;
    }
}
