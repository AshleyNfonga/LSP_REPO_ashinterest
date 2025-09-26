package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Writes rows to a CSV file.
 */
public class CSVWriter {
    public void write(String filePath, String header, List<String> rows) throws IOException {
        Path p = Path.of(filePath);
        if (p.getParent() != null) {
            Files.createDirectories(p.getParent());
        }

        List<String> output = new java.util.ArrayList<>();
        if (header != null && !header.isEmpty()) {
            output.add(header);
        }
        output.addAll(rows);

        Files.write(p, output);
    }
}
