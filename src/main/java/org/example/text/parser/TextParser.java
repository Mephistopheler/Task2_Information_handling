package org.example.text.parser;

import org.example.text.entity.TextComponent;
import org.example.text.exception.TextProcessingException;

public interface TextParser {
    void setNext(TextParser nextParser);

    TextComponent parse(String source) throws TextProcessingException;
}