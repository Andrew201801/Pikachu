package com.Pikachu.Bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Trade {
    @NotEmpty
    @Length(min = 6, max = 30)
    private String accountID;

    @NotEmpty

    private String asset;
}
