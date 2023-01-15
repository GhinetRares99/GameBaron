package com.example.GameBaron.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table
public class Badge {

    @Id
    @SequenceGenerator(name = "badge_sequence", sequenceName = "badge_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "badge_sequence")
    private Integer BadgeId;
    private String BadgeName;
    private String BadgeDescription;

    @JsonBackReference(value = "user-badge")
    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    public Badge() {}

    public Badge(String badgeName, String badgeDescription, User user) {
        BadgeName = badgeName;
        BadgeDescription = badgeDescription;
        this.user = user;
    }

    public Badge(Integer badgeId, String badgeName, String badgeDescription, User user) {
        BadgeId = badgeId;
        BadgeName = badgeName;
        BadgeDescription = badgeDescription;
        this.user = user;
    }

    public Integer getBadgeId() {
        return BadgeId;
    }

    public void setBadgeId(Integer badgeId) {
        BadgeId = badgeId;
    }

    public String getBadgeName() {
        return BadgeName;
    }

    public void setBadgeName(String badgeName) {
        BadgeName = badgeName;
    }

    public String getBadgeDescription() {
        return BadgeDescription;
    }

    public void setBadgeDescription(String badgeDescription) {
        BadgeDescription = badgeDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Badge{" +
                "BadgeId=" + BadgeId +
                ", BadgeName='" + BadgeName + '\'' +
                ", BadgeDescription='" + BadgeDescription + '\'' +
                ", user=" + user +
                '}';
    }
}
