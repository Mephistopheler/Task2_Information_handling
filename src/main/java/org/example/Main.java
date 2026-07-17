package org.example;

import java.nio.file.Path;
import java.util.logging.Logger;
import org.example.text.entity.TextComponent;
import org.example.text.exception.TextProcessingException;
import org.example.text.io.TextFileReader;
import org.example.text.parser.TextParser;
import org.example.text.parser.TextParserChainFactory;
import org.example.text.service.TextStatisticsService;
import org.example.text.service.TextTaskService;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar app.jar <text-file> [letter]");
            return;
        }
        char letter = args.length > 1 ? args[1].charAt(0) : 'e';
        try {
            String source = new TextFileReader().read(Path.of(args[0]));
            TextParser parser = new TextParserChainFactory().createParser();
            TextComponent text = parser.parse(source);
            TextStatisticsService statisticsService = new TextStatisticsService();
            TextTaskService taskService = new TextTaskService();
            System.out.println("Restored text:\n" + text.asText());
            System.out.println("Letters: " + statisticsService.countLetters(text));
            System.out.println("Symbols: " + statisticsService.countSymbols(text));
            System.out.println("Sentences with repeated words: " + taskService.countSentencesWithRepeatedWords(text));
            System.out.println("Sentences sorted by letter '" + letter + "':");
            taskService.sortSentencesByLetterCount(text, letter).forEach(sentence -> System.out.println(sentence.asText()));
            taskService.swapFirstAndLastLexemes(text);
            System.out.println("After lexeme swap:\n" + text.asText());
        } catch (TextProcessingException e) {
            LOGGER.severe("Text processing failed: " + e.getMessage());
        }
    }
}