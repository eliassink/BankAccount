package io.github.eliassink.bankaccount.model;

import java.util.Date;

public record Transaction(Cents amount, Date date) {
}
