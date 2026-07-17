package org.example.text.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.example.text.exception.TextProcessingException;

public class TextFileReader {
    public String read(Path path) throws TextProcessingException {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new TextProcessingException("Cannot read text file: " + path, e);
        }
    }
}