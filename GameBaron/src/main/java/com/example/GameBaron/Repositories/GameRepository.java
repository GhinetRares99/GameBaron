package com.example.GameBaron.Repositories;

import com.example.GameBaron.Entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query("SELECT g FROM Game g WHERE g.GameGenre = ?1")
    Optional<Game> findGameByGameGenre(String gameGenre);
}
