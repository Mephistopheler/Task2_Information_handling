package org.example.text.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class TextComposite implements TextComponent {
    private final TextComponentType type;
    private final List<TextComponent> children = new ArrayList<>();

    public TextComposite(TextComponentType type) {
        this.type = type;
    }

    public void add(TextComponent component) {
        children.add(component);
    }

    public void set(int index, TextComponent component) {
        children.set(index, component);
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public String asText() {
        if (type == TextComponentType.TEXT) {
            return join("\n");
        }
        if (type == TextComponentType.PARAGRAPH || type == TextComponentType.SENTENCE) {
            return join(" ");
        }
        return join("");
    }

    private String join(String delimiter) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (TextComponent child : children) {
            joiner.add(child.asText());
        }
        return joiner.toString();
    }
}