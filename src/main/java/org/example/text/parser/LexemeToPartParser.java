package org.example.text.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.example.text.entity.TextComponent;
import org.example.text.entity.TextComponentType;
import org.example.text.entity.TextComposite;
import org.example.text.entity.TextLeaf;

public class LexemeToPartParser implements TextParser {
    private static final String WORD_OR_SYMBOL_REGEX = "[\\p{L}]+|[^\\p{L}]";
    private static final Pattern WORD_OR_SYMBOL_PATTERN = Pattern.compile(WORD_OR_SYMBOL_REGEX);

    @Override
    public void setNext(TextParser nextParser) {
        throw new UnsupportedOperationException("Lexeme parser is the last parser in the chain");
    }

    @Override
    public TextComponent parse(String source) {
        TextComposite lexeme = new TextComposite(TextComponentType.LEXEME);
        Matcher matcher = WORD_OR_SYMBOL_PATTERN.matcher(source);
        while (matcher.find()) {
            String value = matcher.group();
            TextComponentType type = value.codePoints().allMatch(Character::isLetter)
                    ? TextComponentType.WORD
                    : TextComponentType.SYMBOL;
            lexeme.add(new TextLeaf(type, value));
        }
        return lexeme;
    }
}