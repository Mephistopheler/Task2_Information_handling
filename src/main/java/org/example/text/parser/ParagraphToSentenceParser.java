package org.example.text.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.example.text.entity.TextComponent;
import org.example.text.entity.TextComponentType;
import org.example.text.entity.TextComposite;
import org.example.text.exception.TextProcessingException;

public class ParagraphToSentenceParser extends AbstractTextParser {

    private static final String SENTENCE_REGEX = "[^.!?]+[.!?]+(?:['\")\\]]+)?|[^.!?]+$";
    private static final Pattern SENTENCE_PATTERN = Pattern.compile(SENTENCE_REGEX);

    @Override
    public TextComponent parse(String source) throws TextProcessingException {
        TextComposite paragraph = new TextComposite(TextComponentType.PARAGRAPH);
        Matcher matcher = SENTENCE_PATTERN.matcher(source);
        while (matcher.find()) {
            String sentence = matcher.group().trim();
            if (!sentence.isEmpty()) {
                paragraph.add(parseNext(sentence));
            }
        }
        return paragraph;
    }
}