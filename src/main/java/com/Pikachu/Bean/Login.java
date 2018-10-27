package com.Pikachu.Bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Login {
    @NotEmpty
    @Length(min = 6, max = 30)
    private String accountID;

    @NotEmpty
    @Length(min = 6, max = 30)
    private String password;

    private String credit;

    private String point;

    public String getAccountID() {
        return accountID;
    }

    public String getPassword() {
        return password;
    }

    public String getCredit() {
        return credit;
    }

    public String getPoint() {
        return point;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setPoint(String point) {
        this.point = point;
    }

}
