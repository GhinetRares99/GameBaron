package com.example.GameBaron.Services;

import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Entities.Possession;
import com.example.GameBaron.Entities.User;
import com.example.GameBaron.Repositories.GameRepository;
import com.example.GameBaron.Repositories.PossessionRepository;
import com.example.GameBaron.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PossessionService {

    private final UserRepository userRepository;

    private final GameRepository gameRepository;

    private final PossessionRepository possessionRepository;

    @Autowired
    public PossessionService(UserRepository userRepository, GameRepository gameRepository, PossessionRepository possessionRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.possessionRepository = possessionRepository;
    }

    public List<Possession> getPossessions() { return possessionRepository.findAll(); }

    public Possession addPossession(Possession possession){
        Optional<User> findUserByUserId = userRepository.findById(possession.getUser().getUserId());
        Optional<Game> findGameByGameId = gameRepository.findById(possession.getGame().getGameId());

        if(findUserByUserId.isEmpty()){
            throw new IllegalStateException("The specified user does not exist!");
        }

        if(findGameByGameId.isEmpty()){
            throw new IllegalStateException("The specified game does not exist!");
        }

        possessionRepository.save(possession);
        return possession;
    }

    public Integer deletePossession(Integer possessionId){
        boolean exists = possessionRepository.existsById(possessionId);
        if(!exists){
            throw new IllegalStateException("The specified game possession does not exist!");
        }
        possessionRepository.deleteById(possessionId);
        return possessionId;
    }
}
