package com.store;

import java.math.BigDecimal;

public interface DiscountManager {
    public BigDecimal generateDiscountValue(BigDecimal amount);
}
