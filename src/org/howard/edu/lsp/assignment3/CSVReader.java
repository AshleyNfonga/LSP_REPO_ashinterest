package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads CSV files into rows of String arrays.
 */
public class CSVReader {
    public List<String[]> read(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));
        List<String[]> rows = new ArrayList<>();

        for (String line : lines) {
            if (line == null || line.strip().isEmpty()) continue;
            rows.add(line.split(","));
        }
        return rows;
    }
}
