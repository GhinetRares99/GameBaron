package com.example.GameBaron.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table
public class Game {

    @Id
    @SequenceGenerator(name = "game_sequence", sequenceName = "game_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_sequence")
    private Integer GameId;
    @NotBlank(message = "The name field is mandatory!")
    private String GameName;
    @NotBlank(message = "The genre field is mandatory!")
    private String GameGenre;
    @NotBlank(message = "The creator field is mandatory!")
    private String GameCreator;
    @NotBlank(message = "The publisher field is mandatory!")
    private String GamePublisher;
    @NotBlank(message = "The description field is mandatory!")
    private String GameDescription;

    @Column(columnDefinition = "integer default 0")
    @Min(0)
    private Integer GamePrice;

    @JsonManagedReference(value = "game-tool")
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "game")
    private Set<Tool> tools;

    @JsonManagedReference(value = "game-achievement")
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "game")
    private Set<Achievement> achievements;

    @JsonManagedReference(value = "game-possession")
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "game")
    private Set<Possession> possessions;

    public Game() {
    }

    public Game(String gameName, String gameGenre, String gameCreator, String gamePublisher, String gameDescription, Integer gamePrice, Set<Tool> tools, Set<Achievement> achievements, Set<Possession> possessions) {
        GameName = gameName;
        GameGenre = gameGenre;
        GameCreator = gameCreator;
        GamePublisher = gamePublisher;
        GameDescription = gameDescription;
        GamePrice = gamePrice;
        this.tools = tools;
        this.achievements = achievements;
        this.possessions = possessions;
    }

    public Game(Integer gameId, String gameName, String gameGenre, String gameCreator, String gamePublisher, String gameDescription, Integer gamePrice, Set<Tool> tools, Set<Achievement> achievements, Set<Possession> possessions) {
        GameId = gameId;
        GameName = gameName;
        GameGenre = gameGenre;
        GameCreator = gameCreator;
        GamePublisher = gamePublisher;
        GameDescription = gameDescription;
        GamePrice = gamePrice;
        this.tools = tools;
        this.achievements = achievements;
        this.possessions = possessions;
    }

    public Integer getGameId() {
        return GameId;
    }

    public void setGameId(Integer gameId) {
        GameId = gameId;
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }

    public String getGameGenre() {
        return GameGenre;
    }

    public void setGameGenre(String gameGenre) {
        GameGenre = gameGenre;
    }

    public String getGameCreator() {
        return GameCreator;
    }

    public Integer getGamePrice() {
        return GamePrice;
    }

    public void setGamePrice(Integer gamePrice) {
        GamePrice = gamePrice;
    }

    public void setGameCreator(String gameCreator) {
        GameCreator = gameCreator;
    }

    public String getGamePublisher() {
        return GamePublisher;
    }

    public void setGamePublisher(String gamePublisher) {
        GamePublisher = gamePublisher;
    }

    public String getGameDescription() {
        return GameDescription;
    }

    public void setGameDescription(String gameDescription) {
        GameDescription = gameDescription;
    }

    public Set<Tool> getTools() {
        return tools;
    }

    public void setTools(Set<Tool> tools) {
        this.tools = tools;
    }

    public Set<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(Set<Achievement> achievements) {
        this.achievements = achievements;
    }

    public Set<Possession> getPossessions() {
        return possessions;
    }

    public void setPossessions(Set<Possession> possessions) {
        this.possessions = possessions;
    }

    @Override
    public String toString() {
        return "Game{" +
                "GameId=" + GameId +
                ", GameName='" + GameName + '\'' +
                ", GameGenre='" + GameGenre + '\'' +
                ", GameCreator='" + GameCreator + '\'' +
                ", GamePublisher='" + GamePublisher + '\'' +
                ", GameDescription='" + GameDescription + '\'' +
                ", GamePrice=" + GamePrice +
                ", tools=" + tools +
                ", achievements=" + achievements +
                ", possessions=" + possessions +
                '}';
    }
}
