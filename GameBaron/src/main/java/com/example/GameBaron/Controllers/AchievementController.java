package com.example.GameBaron.Controllers;

import com.example.GameBaron.Entities.Achievement;
import com.example.GameBaron.Services.AchievementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AchievementController {

    private final AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping
    @RequestMapping(path = "GameBaron/Achievement/getAchievements")
    public List<Achievement> getAchievements(){
        return achievementService.getAchievements();
    }

    @PostMapping
    @RequestMapping(path = "GameBaron/Achievement/addAchievement")
    public void addAchievement(@Valid @RequestBody Achievement achievement){
        achievementService.addAchievement(achievement);
    }

    @PutMapping
    @RequestMapping(path = "GameBaron/Achievement/updateAchievement/{achievementId}")
    public void updateAchievement(@PathVariable Integer achievementId, @RequestParam(required = false) String achievementName,
                                  @RequestParam(required = false) String achievementDescription,
                                  @RequestParam(required = false) String achievementRarity){
        achievementService.updateAchievement(achievementId, achievementName, achievementDescription, achievementRarity);
    }

    @DeleteMapping
    @RequestMapping(path = "GameBaron/Achievement/deleteAchievement/{achievementId}")
    public void deleteAchievement(@PathVariable("achievementId") Integer achievementId){ achievementService.deleteAchievement(achievementId); }
}
