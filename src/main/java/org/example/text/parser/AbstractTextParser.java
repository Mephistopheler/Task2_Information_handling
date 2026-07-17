package org.example.text.parser;

import org.example.text.entity.TextComponent;
import org.example.text.exception.TextProcessingException;

public abstract class AbstractTextParser implements TextParser {
    private TextParser nextParser;

    @Override
    public void setNext(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    protected TextComponent parseNext(String source) throws TextProcessingException {
        if (nextParser == null) {
            throw new TextProcessingException("Parser chain is not configured");
        }
        return nextParser.parse(source);
    }
}