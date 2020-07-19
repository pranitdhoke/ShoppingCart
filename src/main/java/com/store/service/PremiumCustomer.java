package com.store.service;

import com.store.error.NegativeAmountException;

import java.math.BigDecimal;

/**
 * This is PremiumCustomer class.
 *
 * @author Pranit
 * @since 19th July 2020
 * @see DiscountManager
 */
public class PremiumCustomer implements DiscountManager {

    @Override
    public BigDecimal generateDiscountValue(BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) == 0){
            return BigDecimal.ZERO;
        }
        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal calculatedDiscount = getDiscountFor0To4K(amount);
        discount = discount.add(calculatedDiscount);
        if (amount.compareTo(BigDecimal.valueOf(4000)) > 0) {
            calculatedDiscount = getDiscountFor4kTo8K(amount);
            discount = discount.add(calculatedDiscount);
        }
        if (amount.compareTo(BigDecimal.valueOf(8000)) > 0) {
            calculatedDiscount = getDiscountFor8kTo12k(amount);
            discount = discount.add(calculatedDiscount);
        }
        if (amount.compareTo(BigDecimal.valueOf(12000)) > 0) {
            calculatedDiscount = getDiscountForAbove12k(amount);
            discount = discount.add(calculatedDiscount);
        }

        return discount;

    }

    private BigDecimal getDiscountFor0To4K(final BigDecimal amount) {
        BigDecimal eligibleAmountForDiscount;
        if (amount.compareTo(BigDecimal.valueOf(4000)) <= 0) {
            eligibleAmountForDiscount = amount;
        } else {
            eligibleAmountForDiscount = BigDecimal.valueOf(4000);
        }
        return eligibleAmountForDiscount.multiply(BigDecimal.valueOf(10).divide(BigDecimal.valueOf(100)));
    }

    private BigDecimal getDiscountFor4kTo8K(BigDecimal amount) {
        BigDecimal eligibleAmountForDiscount;
        if (amount.compareTo(BigDecimal.valueOf(8000)) >= 0) {
            eligibleAmountForDiscount = BigDecimal.valueOf(4000);
        } else {
            eligibleAmountForDiscount = amount.subtract(BigDecimal.valueOf(4000));
        }

        return eligibleAmountForDiscount.multiply(BigDecimal.valueOf(15).divide(BigDecimal.valueOf(100)));
    }

    private BigDecimal getDiscountFor8kTo12k(BigDecimal amount) {
        BigDecimal eligibleAmountForDiscount;
        if (amount.compareTo(BigDecimal.valueOf(12000)) >= 0) {
            eligibleAmountForDiscount = BigDecimal.valueOf(4000);
        } else {
            eligibleAmountForDiscount = amount.subtract(BigDecimal.valueOf(8000));
        }

        return eligibleAmountForDiscount.multiply(BigDecimal.valueOf(20).divide(BigDecimal.valueOf(100)));
    }

    private BigDecimal getDiscountForAbove12k(BigDecimal amount) {
        BigDecimal eligibleAmountForDiscount = amount.subtract(BigDecimal.valueOf(12000));

        return eligibleAmountForDiscount.multiply(BigDecimal.valueOf(30)
                .divide(BigDecimal.valueOf(100)));
    }
}
