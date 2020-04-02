package com.example.businesscardmanager.models;

import io.realm.RealmObject;

public class User extends RealmObject {

    private String name;
    private String emailAdress;
    private String telephone;
    private String companyName;
    private String password;
    //The user profile can change and a user can have many profiles

    public User() {
    }

    public User(String name,
                String emailAdress,
                String telephone,
                String companyName
                ) {

        this.name = name;
        this.emailAdress = emailAdress;
        this.telephone = telephone;
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
