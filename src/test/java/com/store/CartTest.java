package com.store;

import com.store.error.NegativeAmountException;
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
        assertThrows(NegativeAmountException.class,()->systemUnderTest.generateInvoice(new RegularCustomer()));
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

    @Test
    @DisplayName("Get an exception if the amount is negative for premium customer.")
    public void generateBill_negativeAmountExpectExceptionForPremiumCustomer(){
        // Given
        systemUnderTest = Cart.getCart(BigDecimal.valueOf(-5));

        //When
        assertThrows(NegativeAmountException.class,()->systemUnderTest.generateInvoice(new PremiumCustomer()));
    }

    @Test
    @DisplayName("Get a valid discount if amount is below 4k for Premium customer.")
    public void generateBill_PremiumCustomerForBelow4K(){
        // Given
        systemUnderTest = Cart.getCart(BigDecimal.valueOf(4000));

        //When
        BigDecimal totalAmount = systemUnderTest.generateInvoice(new PremiumCustomer());

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount.doubleValue()).isEqualTo(Double.valueOf(3600));
    }

    @Test
    @DisplayName("Get a valid discount if amount is between 4k to 8k for Premium customer.")
    public void generateBill_PremiumCustomerForBetween4kTo8k(){
        // Given
        systemUnderTest = Cart.getCart(BigDecimal.valueOf(8000));

        //When
        BigDecimal totalAmount = systemUnderTest.generateInvoice(new PremiumCustomer());

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount.doubleValue()).isEqualTo(Double.valueOf(7000));
    }

    @Test
    @DisplayName("Get a valid discount if amount is between 8k to 12k for Premium customer.")
    public void generateBill_PremiumCustomerForBetween8kTo12k(){
        // Given
        systemUnderTest = Cart.getCart(BigDecimal.valueOf(12000));

        //When
        BigDecimal totalAmount = systemUnderTest.generateInvoice(new PremiumCustomer());

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount.doubleValue()).isEqualTo(Double.valueOf(10200));
    }

    @Test
    @DisplayName("Get a valid discount if amount is above 12k for Premium customer.")
    public void generateBill_PremiumCustomerForAbove12k(){
        // Given
        systemUnderTest = Cart.getCart(BigDecimal.valueOf(20000));

        //When
        BigDecimal totalAmount = systemUnderTest.generateInvoice(new PremiumCustomer());

        //Then
        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isNotNegative();
        assertThat(totalAmount.doubleValue()).isEqualTo(Double.valueOf(15800));
    }

}
