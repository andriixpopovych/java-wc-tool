package com.xpopovych;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class WcService {
    private FileRepository fileRepository;

    public WcService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public long execute(String filename) throws IOException {
        return 0;
    }

    public long execute(String attribute, String filename) throws IOException {
        int bufferSize = 16 * 1024; // 16 KB
        InputStream rawStream = fileRepository.getFileStream(filename);
        try (InputStream bufStream = new BufferedInputStream(rawStream, bufferSize) ){
            return switch (attribute) {
                case "-c" -> countBytes(bufStream);
                case "-w" -> countWords(bufStream);
                case "-l" -> countLines(bufStream);
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

    private long countLines(InputStream iStream) throws IOException{
        int b;
        long countLines = 0;
        while ((b = iStream.read()) != -1){
            if (b == '\n') countLines++;
        }
        return countLines;
    }

}
