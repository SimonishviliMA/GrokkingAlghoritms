package ru.mikhail.chapter_10;

import java.util.Map;

public record Person(String name, Map<Category, Integer> rates) {}
