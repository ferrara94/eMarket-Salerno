package com.emarket.payment_service.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "FIRST NAME IS REQUIRED")
        String firstname,
        @NotNull(message = "LAST NAME IS REQUIRED")
        String lastname,
        @NotNull(message = "EMAIL IS REQUIRED")
        @Email(message = "THE CUSTOMER EMAIL IS NOT CORRECTLY FORMATTED")
        String email
) {
}
