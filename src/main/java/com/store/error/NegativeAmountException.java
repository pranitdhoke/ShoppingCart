package com.store.error;

public class NegativeAmountException extends RuntimeException {
    public NegativeAmountException(){
        super("Invalid amount value. Amount should be non-negative.");
    }
}
