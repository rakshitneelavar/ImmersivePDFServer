package com.ssdi.immersivepdf.model.Register;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String role = "User";
    private String password;

    @JsonProperty
    private Boolean isAllowedToResetPassword;

    //NOTICE : This kind of initializer is not required for model classes and infact caused problem. Creating another blank constructor solved the problem and was able to map the json request to model classes. This construsuctor will be deleted in future commits
    public User(String firstname, String lastname, String email, String password, Boolean isAllowedToResetPassword) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.isAllowedToResetPassword = isAllowedToResetPassword;
    }

    public User(){

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAllowedToResetPassword() { return isAllowedToResetPassword; }

    public void setAllowedToResetPassword(Boolean allowedToResetPassword) { isAllowedToResetPassword = allowedToResetPassword; }
}
