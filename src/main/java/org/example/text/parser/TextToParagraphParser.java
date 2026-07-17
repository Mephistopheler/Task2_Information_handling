package org.example.text.parser;

import org.example.text.entity.TextComponent;
import org.example.text.entity.TextComponentType;
import org.example.text.entity.TextComposite;
import org.example.text.exception.TextProcessingException;
import java.util.logging.Logger;

public class TextToParagraphParser extends AbstractTextParser {

    private static final String PARAGRAPH_SPLIT_REGEX = "\\R+";
    private static final String LEXEME_SPLIT_REGEX = "\\s+";
    private static final Logger LOGGER = Logger.getLogger(TextToParagraphParser.class.getName());

    @Override
    public TextComponent parse(String source) throws TextProcessingException {
        if (source == null) {
            throw new TextProcessingException("Source text must not be null");
        }
        LOGGER.info("Parsing text into paragraphs");
        TextComposite text = new TextComposite(TextComponentType.TEXT);
        String[] paragraphs = source.strip().split(PARAGRAPH_SPLIT_REGEX);
        for (String paragraph : paragraphs) {
            String normalized = paragraph.trim().replaceAll(LEXEME_SPLIT_REGEX, " ");
            if (!normalized.isEmpty()) {
                text.add(parseNext(normalized));
            }
        }
        return text;
    }
}