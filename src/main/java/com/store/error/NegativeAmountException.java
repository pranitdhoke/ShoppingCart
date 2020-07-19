package com.store.error;

/**
 * Exception class for invalid amount in cart.
 * @author Pranit
 * @since 19th July 2020
 */
public class NegativeAmountException extends RuntimeException {
    public NegativeAmountException() {
        super("Invalid amount value. Amount should be non-negative.");
    }
}
