package com.xpopovych;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileRepository {
    public InputStream getFileStream(String filename) throws IOException {
        if (filename == null || filename.isEmpty()) throw new IllegalArgumentException("Filename cannot be null");

        Path path = Paths.get(filename);
        if (!Files.isRegularFile(path)) throw new NoSuchFileException("File does not exist: " + filename);

        return Files.newInputStream(path);
    }
}
