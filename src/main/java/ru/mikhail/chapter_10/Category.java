package ru.mikhail.chapter_10;

public enum Category {
    COMEDY,
    ACTION,
    DRAMA,
    HORROR;

    private static final Category[] values = values();

    public static Category[] getValues() {
        return values;
    }
}
