package com.Pikachu.Bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Transaction {

    @NotEmpty
    @Length(min = 3, max = 30)
    private String accountID;

    @NotEmpty
    private String point;

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getPoint() {
        return point;
    }
}
