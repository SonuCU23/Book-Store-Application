package com.book.store.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotNull;

public enum Genre {
    PROGRAMMING(1, "PROGRAMMING"),
    ACADEMICS(2, "ACADEMICS"),
    SCIENCE(3, "SCIENCE");

    private  int value;
    private String name;

    Genre(final int value, final String name) {
        this.value = value;
        this.name = name;
    }

    public static Genre fromValue(int  value) {
        switch (value) {
            case 1:
                return Genre.PROGRAMMING;
            case 2:
                return Genre.ACADEMICS;
            case 3:
                return Genre.SCIENCE;
            default:
                return null;
        }
    }

    @JsonCreator
    public static Genre fromName(final String name) {
        switch (name) {
            case "NOVEL":
                return Genre.PROGRAMMING;
            case "ACADEMICS":
                return Genre.ACADEMICS;
            case "SCIENCE":
                return Genre.SCIENCE;
            default:
                return null;
        }
    }

    public int getValue() {return value;}

    @JsonValue
    public String getName() {return name;}
}
