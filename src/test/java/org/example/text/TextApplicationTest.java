package org.example.text;

import java.util.List;
import org.example.text.entity.TextComponent;
import org.example.text.exception.TextProcessingException;
import org.example.text.parser.TextParser;
import org.example.text.parser.TextParserChainFactory;
import org.example.text.service.TextStatisticsService;
import org.example.text.service.TextTaskService;

public class TextApplicationTest {
    public static void main(String[] args) throws TextProcessingException {
        TextApplicationTest test = new TextApplicationTest();
        test.shouldRestoreTextWithNormalizedSpaces();
        test.shouldCountLettersAndSymbols();
        test.shouldCountSentencesWithRepeatedWords();
        test.shouldSortSentencesByLetterCount();
        test.shouldSwapFirstAndLastLexemesInEverySentence();
    }

    void shouldRestoreTextWithNormalizedSpaces() throws TextProcessingException {
        TextComponent text = parser().parse("Hello,   world!\nSecond\tline.");
        requireEquals("Hello, world!\nSecond line.", text.asText());
    }

    void shouldCountLettersAndSymbols() throws TextProcessingException {
        TextComponent text = parser().parse("Hi, мир!");
        TextStatisticsService service = new TextStatisticsService();
        requireEquals(5L, service.countLetters(text));
        requireEquals(2L, service.countSymbols(text));
    }

    void shouldCountSentencesWithRepeatedWords() throws TextProcessingException {
        TextComponent text = parser().parse("One two one. Unique words only! Test test?");
        requireEquals(2L, new TextTaskService().countSentencesWithRepeatedWords(text));
    }

    void shouldSortSentencesByLetterCount() throws TextProcessingException {
        TextComponent text = parser().parse("Aaa. Bb. Aba!");
        List<String> actual = new TextTaskService().sortSentencesByLetterCount(text, 'a')
                .stream()
                .map(TextComponent::asText)
                .toList();
        requireEquals(List.of("Bb.", "Aba!", "Aaa."), actual);
    }

    void shouldSwapFirstAndLastLexemesInEverySentence() throws TextProcessingException {
        TextComponent text = parser().parse("First middle last. One two!");
        new TextTaskService().swapFirstAndLastLexemes(text);
        requireEquals("last. middle First two! One", text.asText());
    }

    private TextParser parser() {
        return new TextParserChainFactory().createParser();
    }

    private void requireEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected <" + expected + "> but was <" + actual + ">");
        }
    }
}