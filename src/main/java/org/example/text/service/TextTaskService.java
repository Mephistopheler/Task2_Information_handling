package org.example.text.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.example.text.entity.TextComponent;
import org.example.text.entity.TextComponentType;
import org.example.text.entity.TextComposite;

public class TextTaskService {
    public long countSentencesWithRepeatedWords(TextComponent text) {
        return sentences(text).stream().filter(this::containsRepeatedWord).count();
    }

    public List<TextComponent> sortSentencesByLetterCount(TextComponent text, char letter) {
        String expected = String.valueOf(letter).toLowerCase(Locale.ROOT);
        return sentences(text).stream()
                .sorted(Comparator.comparingLong(sentence -> countLetter(sentence, expected)))
                .toList();
    }

    public void swapFirstAndLastLexemes(TextComponent text) {
        for (TextComponent sentence : sentences(text)) {
            List<TextComponent> lexemes = sentence.getChildren();
            if (sentence instanceof TextComposite composite && lexemes.size() > 1) {
                TextComponent first = lexemes.get(0);
                TextComponent last = lexemes.get(lexemes.size() - 1);
                composite.set(0, last);
                composite.set(lexemes.size() - 1, first);
            }
        }
    }

    public List<TextComponent> sentences(TextComponent component) {
        List<TextComponent> result = new ArrayList<>();
        collectSentences(component, result);
        return result;
    }

    private void collectSentences(TextComponent component, List<TextComponent> result) {
        if (component.getType() == TextComponentType.SENTENCE) {
            result.add(component);
            return;
        }
        for (TextComponent child : component.getChildren()) {
            collectSentences(child, result);
        }
    }

    private boolean containsRepeatedWord(TextComponent sentence) {
        Set<String> words = new HashSet<>();
        for (TextComponent lexeme : sentence.getChildren()) {
            for (TextComponent part : lexeme.getChildren()) {
                if (part.getType() == TextComponentType.WORD) {
                    String word = part.asText().toLowerCase(Locale.ROOT);
                    if (!words.add(word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private long countLetter(TextComponent sentence, String expected) {
        return sentence.asText().toLowerCase(Locale.ROOT).codePoints()
                .mapToObj(Character::toString)
                .filter(expected::equals)
                .count();
    }
}