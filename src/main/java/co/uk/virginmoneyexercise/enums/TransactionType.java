package co.uk.virginmoneyexercise.enums;

public enum TransactionType {

    CARD("card"),
    DIRECT_DEBIT("direct debit"),
    INTERNET("internet");

    private final String description;

    TransactionType(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}