package com.store;

import java.math.BigDecimal;

public class RegularCustomer implements DiscountManager {
    @Override
    public BigDecimal generateDiscountValue(BigDecimal amount) {
        BigDecimal discount = BigDecimal.ZERO;
        if (amount.signum() == -1) {
            throw new IllegalArgumentException("Invalid amount value. Amount should be non-negative.");
        }
        if (amount.compareTo(BigDecimal.valueOf(5000)) <= 0) {
            return BigDecimal.ZERO;
        }
        if (amount.compareTo(BigDecimal.valueOf(5000)) > 0) {
            BigDecimal calculatedDiscount = getDiscountFor5kTo10KSlab(amount);
            discount = discount.add(calculatedDiscount);
        }
        if (amount.compareTo(BigDecimal.valueOf(10000)) > 0) {
            BigDecimal calculatedDiscount = getDiscountForAbove10KSlab(amount);
            discount = discount.add(calculatedDiscount);
        }
        return discount;
    }

    private BigDecimal getDiscountForAbove10KSlab(BigDecimal amount) {
        BigDecimal eligibleAmountForDiscount = amount.subtract(BigDecimal.valueOf(10000));

        BigDecimal discount = eligibleAmountForDiscount.multiply(BigDecimal.valueOf(20)
                .divide(BigDecimal.valueOf(100)));
        return discount;
    }


    private BigDecimal getDiscountFor5kTo10KSlab(final BigDecimal amount) {
        BigDecimal eligibleAmountForDiscount = BigDecimal.ZERO;
        if (amount.compareTo(BigDecimal.valueOf(10000)) >= 0) {
            eligibleAmountForDiscount = BigDecimal.valueOf(5000);
        } else {
            eligibleAmountForDiscount = amount.subtract(BigDecimal.valueOf(5000));
        }

        BigDecimal discount = eligibleAmountForDiscount.multiply(BigDecimal.valueOf(10)
                .divide(BigDecimal.valueOf(100)));
        return discount;
    }

}
