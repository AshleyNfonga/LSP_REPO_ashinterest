package org.howard.edu.lsp.assignment3;

/**
 * Defines how a CSV row is transformed.
 */
public interface Transformer {
    /**
     * Transform a row into a new CSV row.
     * Return null if the row should be dropped.
     */
    String transform(String[] input);
}
