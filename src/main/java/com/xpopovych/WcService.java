package com.xpopovych;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WcService {
    private final String ATTRIBUTE_C = "-c";
    private final String ATTRIBUTE_W = "-w";
    private final String ATTRIBUTE_L = "-l";
    private final String ATTRIBUTE_R = "-m";
    private final FileRepository fileRepository;

    public WcService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public long[] execute(String filename) throws IOException {
        long countBytes = 0, countWords = 0, countLines = 0, countCharacters = 0;
        int b;
        int prevByte = ' ';

        try (InputStream bufStream = new BufferedInputStream(fileRepository.getFileStream(filename), 16 * 1024)) {
            while ((b = bufStream.read()) != -1) {
                countBytes++;
                if (b == '\n') countLines++;
                if (Character.isWhitespace(prevByte) && !Character.isWhitespace(b)) countWords++;
                if ((b & 0xC0) != 0x80) countCharacters++;
                prevByte = b;
            }

            return new long[]{countBytes, countWords, countLines, countCharacters};
        }
    }

    public long execute(String attribute, String filename) throws IOException {
        int bufferSize = 16 * 1024; // 16 KB
        InputStream rawStream = fileRepository.getFileStream(filename);
        try (InputStream bufStream = new BufferedInputStream(rawStream, bufferSize)) {
            return switch (attribute) {
                case ATTRIBUTE_C -> countBytes(bufStream);
                case ATTRIBUTE_W -> countWords(bufStream);
                case ATTRIBUTE_L -> countLines(bufStream);
                case ATTRIBUTE_R -> countCharacters(bufStream);
                default -> throw new IllegalArgumentException("Invalid attribute: " + attribute);
            };
        }
    }

    private long countBytes(InputStream iStream) throws IOException {
        long countBytes = 0;
        while (iStream.read() != -1) {
            countBytes++;
        }
        return countBytes;
    }

    private long countWords(InputStream iStream) throws IOException {
        int prevByte = ' ';
        int currByte;
        long countWords = 0;

        while ((currByte = iStream.read()) != -1) {
            if (Character.isWhitespace(prevByte) && !Character.isWhitespace(currByte)) countWords++;
            prevByte = currByte;
        }

        return countWords;
    }

    private long countLines(InputStream iStream) throws IOException {
        int b;
        long countLines = 0;
        while ((b = iStream.read()) != -1) {
            if (b == '\n') countLines++;
        }
        return countLines;
    }

    private long countCharacters(InputStream bufStream) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(bufStream)) {
            long countCharacters = 0;
            while (reader.read() != -1) {
                countCharacters++;
            }
            return countCharacters;
        }
    }

}
