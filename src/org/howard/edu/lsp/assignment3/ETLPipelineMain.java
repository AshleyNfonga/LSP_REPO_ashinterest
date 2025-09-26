package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main driver for the ETL pipeline.
 */
public class ETLPipelineMain {
    public static final String INPUT = "data/products.csv";
    public static final String OUTPUT = "data/transformed_products.csv";

    public static void main(String[] args) {
        CSVReader reader = new CSVReader();
        CSVWriter writer = new CSVWriter();
        Transformer transformer = new SimpleTransformer();

        try {
            List<String[]> rows = reader.read(INPUT);

            if (rows.isEmpty()) {
                System.err.println("Input file is empty!");
                writer.write(OUTPUT, null, new ArrayList<>());
                return;
            }

            String header = String.join(",", rows.get(0));
            List<String> outRows = new ArrayList<>();

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                String out = transformer.transform(row);
                if (out != null) outRows.add(out);
            }

            writer.write(OUTPUT, header, outRows);
            System.out.println("Wrote " + outRows.size() + " rows to " + OUTPUT);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

