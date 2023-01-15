package com.example.GameBaron.Services;

import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames(){
        return gameRepository.findAll();
    }

    public Game addGame(Game game) {
        gameRepository.save(game);
        return game;
    }

    @Transactional
    public Game updateGame(Integer gameId, String gameName, String gameGenre, String gameCreator, String gamePublisher, String gameDescription, Integer gamePrice){
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalStateException("The specified game does not exist!"));

        if(gameName != null && gameName.length() > 0 && !Objects.equals(game.getGameName(), gameName)){
            game.setGameName(gameName);
        }

        if(gameGenre != null && gameGenre.length() > 0 && !Objects.equals(game.getGameGenre(), gameGenre)){
            game.setGameGenre(gameGenre);
        }

        if(gameCreator != null && gameCreator.length() > 0 && !Objects.equals(game.getGameCreator(), gameCreator)){
            game.setGameCreator(gameCreator);
        }

        if(gamePublisher != null && gamePublisher.length() > 0 && !Objects.equals(game.getGamePublisher(), gamePublisher)){
            game.setGamePublisher(gamePublisher);
        }

        if(gameDescription != null && gameDescription.length() > 0 && !Objects.equals(game.getGameDescription(), gameDescription)){
            game.setGameDescription(gameDescription);
        }

        if(gamePrice >=0 && !Objects.equals(game.getGamePrice(), gamePrice)){
            game.setGamePrice(gamePrice);
        }
        return game;
    }

    public Integer deleteGame(Integer gameId) {
        boolean exists = gameRepository.existsById(gameId);
        if(!exists){
            throw new IllegalStateException("The specified game does not exist!");
        }
        gameRepository.deleteById(gameId);
        return gameId;
    }
}
