package com.store;

import com.store.error.NegativeAmountException;

import java.math.BigDecimal;

public interface DiscountManager {
    public BigDecimal generateDiscountValue(BigDecimal amount) throws NegativeAmountException;
}
