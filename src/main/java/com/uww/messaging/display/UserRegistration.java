package com.uww.messaging.display;

public class UserRegistration {
    private String firstName;
    private String lastName;
    private String email;
    private String emailAgain;
    private String password;
    private String passwordAgain;
    private String phoneNumber;

    public UserRegistration() {

    }

    public UserRegistration(String firstName, String lastName, String email, String emailAgain, String password, String passwordAgain, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emailAgain = emailAgain;
        this.password = password;
        this.passwordAgain = passwordAgain;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailAgain() {
        return emailAgain;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmailAgain(String emailAgain) {
        this.emailAgain = emailAgain;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object[] toArray() {
        return new Object[]{
                firstName,
                lastName,
                email,
                emailAgain,
                password,
                passwordAgain,
                phoneNumber
        };
    }
}
