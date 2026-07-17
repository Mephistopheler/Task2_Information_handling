package org.example.text.parser;

import org.example.text.entity.TextComponent;
import org.example.text.entity.TextComponentType;
import org.example.text.entity.TextComposite;
import org.example.text.exception.TextProcessingException;

public class SentenceToLexemeParser extends AbstractTextParser {

    private static final String LEXEME_SPLIT_REGEX = "\\s+";

    @Override
    public TextComponent parse(String source) throws TextProcessingException {
        TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);
        String[] lexemes = source.split(LEXEME_SPLIT_REGEX);
        for (String lexeme : lexemes) {
            if (!lexeme.isEmpty()) {
                sentence.add(parseNext(lexeme));
            }
        }
        return sentence;
    }
}