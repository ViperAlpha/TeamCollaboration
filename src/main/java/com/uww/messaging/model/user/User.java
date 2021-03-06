package com.uww.messaging.model.user;

import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Created by horvste on 1/18/16.
 */
@Entity
public class User {
    private int userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Timestamp lastLoggedIn;
    private String avatarPicture;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, String phoneNumber, String avatarPicture) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.avatarPicture = avatarPicture;
    }

    @Id
    @GeneratedValue
    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 40)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 1000)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "firstName", nullable = true, length = 40)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName", nullable = true, length = 40)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "phoneNumber", nullable = true, length = 40)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "lastLoggedIn", nullable = false)
    public Timestamp getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(final Timestamp lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    @Basic
    @Column(name = "avatarPicture", nullable = true)
    public String getAvatarPicture() {
        return this.avatarPicture;
    }

    public void setAvatarPicture(String avatarPicture) {
        this.avatarPicture = avatarPicture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if(!avatarPicture.equals(user.avatarPicture)) return false;
        return phoneNumber.equals(user.phoneNumber);

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        return result;
    }
}
