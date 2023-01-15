package com.example.GameBaron.Configurations;

import com.example.GameBaron.Entities.Badge;
import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Entities.User;
import com.example.GameBaron.Repositories.BadgeRepository;
import com.example.GameBaron.Repositories.GameRepository;
import com.example.GameBaron.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, BadgeRepository badgeRepository, GameRepository gameRepository){

        return args -> {

            //Numele de utilizator trebuie sa fie unic
            User rares = new User("Rares123", "parola123","Ghinet", "Rares", "rares.ghinet@gmail.com", "0722401668", 0, "Romania", "Bucharest", "Str. Monetariei", null, null);
            userRepository.save(rares);

            Badge firstPurchase = new Badge("First Purchase", "You have purchased your first game!", rares);
            badgeRepository.save(firstPurchase);

            Game callOfDuty = new Game("Call of Duty: Space Warfare", "FPS", "Infinity Ward", "Activision", "A shooter set in space.", 260, null, null, null);
            gameRepository.save(callOfDuty);

        };
    }

}
