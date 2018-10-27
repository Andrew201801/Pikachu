package com.Pikachu.Bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Member {

    @NotEmpty
    @Length(min = 6, max = 30)
    private String accountID;

    @NotEmpty
    @Length(min = 6, max = 30)
    private String password;

    @NotEmpty
    private String asset;

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAsset(String asset) {
        this.asset = asset;
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
        return "Member " + accountID + " " + password+" "+asset;
    }
}
