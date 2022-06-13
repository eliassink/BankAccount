package io.github.eliassink.bankaccount.model;

import java.time.ZonedDateTime;

public record Transaction(Cents amount, ZonedDateTime date) {
}
