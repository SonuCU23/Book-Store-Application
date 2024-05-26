package com.book.store.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionType {
    ISSUE(1, "ISSUE"),
    RETURN(2, "RETURN");

    private  int value;
    private String name;

    TransactionType(final int value, final String name) {
        this.value = value;
        this.name = name;
    }

    public static TransactionType fromValue(final int value) {
        switch (value) {
            case 1:
                return TransactionType.ISSUE;
            case 2:
                return TransactionType.RETURN;
            default:
                return null;
        }
    }

    @JsonCreator
    public static TransactionType fromName(final String name) {
        switch (name) {
            case "ISSUE":
                return TransactionType.ISSUE;
            case "RETURN":
                return TransactionType.RETURN;
            default:
                return null;
        }
    }

    public int getValue() {return value;}

    @JsonValue
    public String getName() {return name;}
}
