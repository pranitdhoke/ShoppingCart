package com.store.model;

import com.store.error.NegativeAmountException;

import java.math.BigDecimal;

/**
 * Cart model class
 * @author Pranit
 * @since 18th July 2020
 */
public class Cart {

    private BigDecimal amount;

    private Cart(BigDecimal amount) {
        this.amount = amount;
    }

    public static Cart getInstance(BigDecimal amount) {
        if(amount.signum() <0){
            throw new NegativeAmountException();
        }
        return new Cart(amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
