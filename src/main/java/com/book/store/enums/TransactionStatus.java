package com.book.store.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionStatus {

    PENDING(0, "PENDING"),
    FAILED(1, "FAILED"),
    SUCCESS(2, "SUCCESS");

    private int value;
    private String name;

    TransactionStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static TransactionStatus fromValue(final int value) {
        switch (value) {
            case 0:
                return TransactionStatus.PENDING;
            case 1:
                return TransactionStatus.FAILED;
             case 2:
                 return TransactionStatus.SUCCESS;
            default:
                return null;
        }
    }

    @JsonCreator
    public static TransactionStatus fromName(final String name) {
        switch (name) {
            case "PENDING":
                return TransactionStatus.PENDING;
            case "FAILED":
                return TransactionStatus.FAILED;
            case "SUCCESS":
                return TransactionStatus.SUCCESS;
            default:
                return null;
        }
    }

    public int getValue() {return value;}

    @JsonValue
    public String getName() {return name;}

}
