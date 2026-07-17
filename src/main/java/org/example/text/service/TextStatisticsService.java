package org.example.text.service;

import org.example.text.entity.TextComponent;
import org.example.text.entity.TextComponentType;

public class TextStatisticsService {
    public long countLetters(TextComponent component) {
        if (component.getType() == TextComponentType.WORD) {
            return component.asText().codePoints().filter(Character::isLetter).count();
        }
        return component.getChildren().stream().mapToLong(this::countLetters).sum();
    }

    public long countSymbols(TextComponent component) {
        if (component.getType() == TextComponentType.SYMBOL) {
            return component.asText().codePointCount(0, component.asText().length());
        }
        return component.getChildren().stream().mapToLong(this::countSymbols).sum();
    }
}