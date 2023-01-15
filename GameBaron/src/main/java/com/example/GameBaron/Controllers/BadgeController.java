package com.example.GameBaron.Controllers;

import com.example.GameBaron.Entities.Badge;
import com.example.GameBaron.Entities.User;
import com.example.GameBaron.Services.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BadgeController {

    private final BadgeService badgeService;

    @Autowired
    public BadgeController(BadgeService badgeService) {this.badgeService = badgeService;}

    @GetMapping
    @RequestMapping(path = "GameBaron/Badge/getBadges")
    public List<Badge> getBadges(){
        return badgeService.getBadges();
    }

    @PostMapping
    @RequestMapping(path = "GameBaron/Badge/addBadge")
    public void addBadge(@RequestBody Badge badge){
        badgeService.addBadge(badge);
    }

    @PutMapping
    @RequestMapping(path = "GameBaron/Badge/updateBadge/{badgeId}")
    public void updateBadge(@PathVariable Integer badgeId, @RequestParam(required = false) String badgeName, @RequestParam(required = false) String badgeDescription){
        badgeService.updateBadge(badgeId, badgeName, badgeDescription);
    }

    @DeleteMapping
    @RequestMapping(path = "GameBaron/Badge/deleteBadge/{badgeId}")
    public void deleteUser(@PathVariable("badgeId") Integer badgeId){
        badgeService.deleteBadge(badgeId);
    }


}
