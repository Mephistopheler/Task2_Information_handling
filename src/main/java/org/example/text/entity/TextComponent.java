package org.example.text.entity;

import java.util.List;

public interface TextComponent {
    TextComponentType getType();

    List<TextComponent> getChildren();

    String asText();
}