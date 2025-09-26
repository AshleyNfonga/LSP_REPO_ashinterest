package org.howard.edu.lsp.assignment3;

/**
 * Example transformer — replace with your Assignment 2 logic!
 */
public class SimpleTransformer implements Transformer {
    @Override
    public String transform(String[] input) {
        if (input == null || input.length == 0) return null;

        String id = input[0].trim();
        String name = input[1].trim();
        String priceStr = input[2].trim();

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            return null; // drop invalid rows
        }

        // TODO: replace this with your Assignment 2 transformation rules
        return String.format("%s,%s,%.2f", id, name, price);
    }
}
