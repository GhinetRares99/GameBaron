package com.example.GameBaron.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table
public class Achievement {

    @Id
    @SequenceGenerator(name = "achievement_sequence", sequenceName = "achievement_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "achievement_sequence")
    private Integer AchievementId;

    private String AchievementName;
    private String AchievementDescription;
    private String AchievementRarity;

    @JsonBackReference(value = "game-achievement")
    @ManyToOne
    @JoinColumn(name = "GameId", nullable = false)
    private Game game;

    public Achievement() {}

    public Achievement(String achievementName, String achievementDescription, String achievementRarity, Game game) {
        AchievementName = achievementName;
        AchievementDescription = achievementDescription;
        AchievementRarity = achievementRarity;
        this.game = game;
    }

    public Achievement(Integer achievementId, String achievementName, String achievementDescription, String achievementRarity, Game game) {
        AchievementId = achievementId;
        AchievementName = achievementName;
        AchievementDescription = achievementDescription;
        AchievementRarity = achievementRarity;
        this.game = game;
    }

    public Integer getAchievementId() {
        return AchievementId;
    }

    public void setAchievementId(Integer achievementId) {
        AchievementId = achievementId;
    }

    public String getAchievementName() {
        return AchievementName;
    }

    public void setAchievementName(String achievementName) {
        AchievementName = achievementName;
    }

    public String getAchievementDescription() {
        return AchievementDescription;
    }

    public void setAchievementDescription(String achievementDescription) {
        AchievementDescription = achievementDescription;
    }

    public String getAchievementRarity() {
        return AchievementRarity;
    }

    public void setAchievementRarity(String achievementRarity) {
        AchievementRarity = achievementRarity;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}
