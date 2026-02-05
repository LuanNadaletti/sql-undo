package com.sqlundo.functional.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The {@code TextFileReader} class provides utility methods for reading the
 * contents of a text file.
 *
 * <p>
 * The class includes a private constructor to prevent instantiation. It is
 * designed as a utility class with only static methods, so there is no need to
 * create instances of this class.
 * </p>
 *
 * @author Luan Nadaletti
 */
public final class TextFileReader {

    private static final int BUFFER_SIZE = 8192;

    private TextFileReader() {
    }

    /**
     * Reads the contents of a text file and returns it as a string.
     *
     * @param file The text file to read.
     * @return The contents of the text file as a string.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static String read(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(
                Files.newBufferedReader(Paths.get(file.getName())),
                BUFFER_SIZE)) {
            char[] buffer = new char[BUFFER_SIZE];

            int charsRead = bufferedReader.read(buffer, 0, BUFFER_SIZE);
            while (charsRead != -1) {
                stringBuilder.append(buffer, 0, charsRead);
                charsRead = bufferedReader.read(buffer, 0, BUFFER_SIZE);
            }
        }

        stringBuilder.setLength(stringBuilder.lastIndexOf(";") + 1);
        return stringBuilder.toString();
    }

}
