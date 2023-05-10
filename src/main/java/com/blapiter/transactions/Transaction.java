package com.blapiter.transactions;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record Transaction(
        @NotNull @Size(min = 26, max = 26) String debitAccount,
        @NotNull @Size(min = 26, max = 26) String creditAccount,
        @NotNull Double amount) {
}