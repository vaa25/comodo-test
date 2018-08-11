package com.comodo.vaa25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class ResourceReader {

    public static String resourceAsString(final String path) throws IOException {
        final InputStreamReader reader = new InputStreamReader(ResourceReader.class.getResourceAsStream(path), "UTF-8");
        try (final BufferedReader stream = new BufferedReader(reader)) {
            final StringBuilder sb = new StringBuilder();
            String line = stream.readLine();
            while (line != null) {
                if (!line.isEmpty() && !line.startsWith("//")) {
                    sb.append(line);
                }
                line = stream.readLine();
            }
            return sb.toString();
        }
    }
}
