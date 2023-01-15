package com.example.GameBaron.Services;

import com.example.GameBaron.Entities.Badge;
import com.example.GameBaron.Entities.User;
import com.example.GameBaron.Repositories.BadgeRepository;
import com.example.GameBaron.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;

    @Autowired
    public BadgeService(BadgeRepository badgeRepository, UserRepository userRepository) {

        this.badgeRepository = badgeRepository;
        this.userRepository = userRepository;
    }

    public List<Badge> getBadges(){
        return badgeRepository.findAll();
    }

    public Badge addBadge(Badge badge) {
        Optional<User> findUserByUserId = userRepository.findById(badge.getUser().getUserId());
        if(findUserByUserId.isEmpty())
        {
            throw new IllegalStateException("This user does not exist!");
        }
        badgeRepository.save(badge);
        return badge;
    }

    @Transactional
    public Badge updateBadge(Integer badgeId, String badgeName, String badgeDescription) {
        Badge badge = badgeRepository.findById(badgeId).orElseThrow(() -> new IllegalStateException("The specified badge does not exist!"));

        if (badgeName != null && badgeName.length() > 0 && !Objects.equals(badge.getBadgeName(), badgeName)) {
            badge.setBadgeName(badgeName);
        }

        if (badgeDescription != null && badgeDescription.length() > 0 && !Objects.equals(badge.getBadgeDescription(), badgeDescription)) {
            badge.setBadgeDescription(badgeDescription);
        }
        return badge;
    }

    public Integer deleteBadge(Integer badgeId) {
        boolean exists = badgeRepository.existsById(badgeId);
        if(!exists){
            throw new IllegalStateException("The specified badge does not exist!");
        }
        badgeRepository.deleteById(badgeId);
        return badgeId;
    }
}
