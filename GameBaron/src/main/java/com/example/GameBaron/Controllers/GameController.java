package com.example.GameBaron.Controllers;

import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    @RequestMapping(path = "GameBaron/Game/getGames")
    public List<Game> getGames(){
        return gameService.getGames();
    }

    @PostMapping
    @RequestMapping(path = "GameBaron/Game/addGame")
    public void registerUser(@RequestBody Game game){
        //System.out.println(user);
        gameService.addGame(game);
    }

    @PutMapping
    @RequestMapping(path = "GameBaron/Game/updateGame/{gameId}")
    public void updateGame(@PathVariable Integer gameId, @RequestParam(required = false) String gameName, @RequestParam(required = false) String gameGenre,
                           @RequestParam(required = false) String gameCreator, @RequestParam(required = false) String gamePublisher,
                           @RequestParam(required = false) String gameDescription, @RequestParam(required = false) Integer gamePrice){
        gameService.updateGame(gameId, gameName, gameGenre, gameCreator, gamePublisher, gameDescription, gamePrice);
    }

    @DeleteMapping
    @RequestMapping(path = "GameBaron/Game/deleteGame/{gameId}")
    public void deleteUser(@PathVariable("gameId") Integer gameId){
        gameService.deleteGame(gameId);
    }
}
