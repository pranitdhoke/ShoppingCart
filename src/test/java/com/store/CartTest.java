package com.store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartTest {

    private Cart systemUnderTest;

    @Test
    @DisplayName("Get a valid discount for a non negative amount.")
    public void getDiscount_validDiscount(){
        // Given
        systemUnderTest = Cart.getCart(BigDecimal.valueOf(1000));

        //When
        BigDecimal discount = systemUnderTest.generateInvoice(new RegularCustomer());

        //Then
        assertThat(discount).isNotNull();
    }

    @Test
    @DisplayName("Get an exception if the amount is negative.")
    public void generateBill_negativeAmountExpectException(){
        // Given
        systemUnderTest = Cart.getCart(BigDecimal.valueOf(-1000));

        //When
        assertThrows(IllegalArgumentException.class,()->systemUnderTest.generateInvoice(new RegularCustomer()));
    }

    @Test
    @DisplayName("Get a valid discount for a non negative amount below 5000.")
    public void generateBill_regularCustomerBelow5000(){
        // Given
        BigDecimal amount = BigDecimal.valueOf(2500);
        systemUnderTest = Cart.getCart(amount);

        //When
        BigDecimal totalAmount = systemUnderTest.generateInvoice(new RegularCustomer());

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount).isEqualTo(amount);
    }

    @Test
    @DisplayName("Get a valid discount if amount is between 5000 - 10000.")
    public void generateBill_regularCustomerFrom5000To10000(){
        // Given
        systemUnderTest = Cart.getCart(BigDecimal.valueOf(8000));

        //When
        BigDecimal totalAmount = systemUnderTest.generateInvoice(new RegularCustomer());

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount.doubleValue()).isEqualTo(Double.valueOf(7700));
    }



    @Test
    @DisplayName("Get a valid discount if amount is above 10k.")
    public void generateBill_regularCustomerForAbove10K(){
        // Given
        systemUnderTest = Cart.getCart(BigDecimal.valueOf(15000));

        //When
        BigDecimal totalAmount = systemUnderTest.generateInvoice(new RegularCustomer());

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount.doubleValue()).isEqualTo(Double.valueOf(13500));
    }

}
