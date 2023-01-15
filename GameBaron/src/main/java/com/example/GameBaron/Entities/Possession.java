package com.example.GameBaron.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table
public class Possession {

    @Id
    @SequenceGenerator(name = "possession_sequence", sequenceName = "possession_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "possession_sequence")
    private Integer PossessionId;

    @JsonBackReference(value = "user-possession")
    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @JsonBackReference(value = "game-possession")
    @ManyToOne
    @JoinColumn(name = "GameId", nullable = false)
    private Game game;

    public Possession() {}

    public Possession(User user, Game game) {
        this.user = user;
        this.game = game;
    }

    public Possession(Integer possessionId, User user, Game game) {
        PossessionId = possessionId;
        this.user = user;
        this.game = game;
    }

    public Integer getPossessionId() {
        return PossessionId;
    }

    public void setPossessionId(Integer possessionId) {
        PossessionId = possessionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Possession{" +
                "PossessionId=" + PossessionId +
                ", user=" + user +
                ", game=" + game +
                '}';
    }
}
