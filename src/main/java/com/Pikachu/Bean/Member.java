package com.Pikachu.Bean;


import org.hibernate.validator.constraints.Length;

import org.hibernate.validator.constraints.NotEmpty;

public class Member {
    @NotEmpty
    @Length(min = 3, max = 30)
    private String firstName;

    @NotEmpty
    @Length(min = 3, max = 30)
    private String lastName;

    @NotEmpty
    @Length(min = 6, max = 30)
    private String accountID;

    @NotEmpty
    @Length(min = 6, max = 30)
    private String password;

    @NotEmpty
    private String asset;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getPassword() {
        return password;
    }

    public String getAsset() {
        return asset;
    }

    @Override
    public String toString() {
        return "Member " + firstName + " " + lastName + " " + accountID + " " + password+" "+asset;
    }
}
