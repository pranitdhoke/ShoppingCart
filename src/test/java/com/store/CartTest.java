package com.store;

import com.store.error.NegativeAmountException;
import com.store.model.Cart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This is a test class for Cart.
 *
 * @author Pranit
 * @since 18th July 2020
 */
public class CartTest {

    private Cart systemUnderTest;

    @Test
    @DisplayName("Create a cart with some valid amount.")
    public void createCart_validCartWithAmount(){
        //Given
        BigDecimal amount = BigDecimal.valueOf(500);
        //When
        systemUnderTest = Cart.getInstance(amount);

        //Then
        assertThat(systemUnderTest).isNotNull();
        assertThat(systemUnderTest.getAmount()).isNotNull();
        assertThat(systemUnderTest.getAmount()).isNotNegative();
        assertThat(systemUnderTest.getAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("Create a cart with negative amount value.")
    public void createCart_negativeAmountExpectException(){
        //Given
        BigDecimal amount = BigDecimal.valueOf(-500);
        //When-Then
        assertThrows(NegativeAmountException.class, () -> Cart.getInstance(amount));
    }



}
