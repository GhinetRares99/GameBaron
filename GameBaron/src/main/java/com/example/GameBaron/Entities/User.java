package com.example.GameBaron.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table
public class User {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Integer UserId;
    @Column(unique = true)
    @NotBlank(message = "The username field is mandatory!")
    private String UserName;
    @NotBlank(message = "The password field is mandatory!")
    private String Password;
    @NotBlank(message = "The first name field is mandatory!")
    private String FirstName;
    @NotBlank(message = "The last name field is mandatory!")
    private String LastName;
    @NotBlank(message = "The email field is mandatory!")
    private String Email;
    @NotBlank(message = "The phone field is mandatory!")
    private String PhoneNumber;

    @Column(columnDefinition = "integer default 0")
    @Min(0)
    private Integer WalletMoney;

    @NotBlank(message = "The country field is mandatory!")
    private String Country;
    private String City;
    private String StreetName;

    @JsonManagedReference(value = "user-badge")
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private Set<Badge> badges;

    @JsonManagedReference(value = "user-possession")
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private Set<Possession> possessions;

    public User() {
    }

    public User(String userName, String password, String firstName, String lastName, String email, String phoneNumber, Integer walletMoney, String country, String city, String streetName, Set<Badge> badges, Set<Possession> possessions) {
        UserName = userName;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        PhoneNumber = phoneNumber;
        WalletMoney = walletMoney;
        Country = country;
        City = city;
        StreetName = streetName;
        this.badges = badges;
        this.possessions = possessions;
    }

    public User(Integer userId, String userName, String password, String firstName, String lastName, String email, String phoneNumber, Integer walletMoney, String country, String city, String streetName, Set<Badge> badges, Set<Possession> possessions) {
        UserId = userId;
        UserName = userName;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        PhoneNumber = phoneNumber;
        WalletMoney = walletMoney;
        Country = country;
        City = city;
        StreetName = streetName;
        this.badges = badges;
        this.possessions = possessions;
    }

    public Integer getUserId() {
        return UserId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Integer getWalletMoney() {
        return WalletMoney;
    }

    public void setWalletMoney(Integer walletMoney) {
        WalletMoney = walletMoney;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    public Set<Badge> getBadges() {
        return badges;
    }

    public void setBadges(Set<Badge> badges) {
        this.badges = badges;
    }

    public Set<Possession> getPossessions() {
        return possessions;
    }

    public void setPossessions(Set<Possession> possessions) {
        this.possessions = possessions;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", WalletMoney=" + WalletMoney +
                ", Country='" + Country + '\'' +
                ", City='" + City + '\'' +
                ", StreetName='" + StreetName + '\'' +
                ", badges=" + badges +
                ", possessions=" + possessions +
                '}';
    }
}
