package org.example.text.entity;

import java.util.Collections;
import java.util.List;

public class TextLeaf implements TextComponent {
    private final TextComponentType type;
    private final String value;

    public TextLeaf(TextComponentType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public String asText() {
        return value;
    }
}