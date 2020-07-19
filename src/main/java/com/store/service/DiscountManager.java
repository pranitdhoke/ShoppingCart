package com.store.service;

import com.store.error.NegativeAmountException;

import java.math.BigDecimal;

/**
 * This is a DiscountManger interface. This is to apply the strategy pattern for
 * discount module of shopping cart. On the basis of implementation class the
 * respective discount strategy is executed.
 *
 * @author Pranit
 * @since 18th July 2020
 *
 */
public interface DiscountManager {
    public BigDecimal generateDiscountValue(BigDecimal amount) throws NegativeAmountException;
}
