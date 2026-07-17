package org.example.text.parser;

public class TextParserChainFactory {
    public TextParser createParser() {
        TextParser textParser = new TextToParagraphParser();
        TextParser paragraphParser = new ParagraphToSentenceParser();
        TextParser sentenceParser = new SentenceToLexemeParser();
        TextParser lexemeParser = new LexemeToPartParser();
        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        return textParser;
    }
}