package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    public static void main(String[] args) {
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputPath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));

            String header = reader.readLine();
            if (header == null) {
                System.out.println("Input file is empty. Creating output with header only.");
                writer.write("ProductID,Name,Price,Category,PriceRange\n");
                writer.close();
                reader.close();
                return;
            }

            writer.write(header + ",PriceRange\n"); // write output header

            String line;
            int rowsRead = 0;
            int rowsTransformed = 0;

            while ((line = reader.readLine()) != null) {
                rowsRead++;
                String[] fields = line.split(",");
                if (fields.length != 4) continue; // skip invalid rows

                String productID = fields[0];
                String name = fields[1].toUpperCase();
                double price = Double.parseDouble(fields[2]);
                String category = fields[3];

                // Apply discount
                if (category.equals("Electronics")) {
                    price = round(price * 0.9, 2);
                }

                // Recategorize
                if (category.equals("Electronics") && price > 500) {
                    category = "Premium Electronics";
                }

                // Determine PriceRange
                String priceRange = getPriceRange(price);

                // Write transformed row
                writer.write(productID + "," + name + "," + price + "," + category + "," + priceRange + "\n");
                rowsTransformed++;
            }

            reader.close();
            writer.close();

            System.out.println("Rows read: " + rowsRead);
            System.out.println("Rows transformed: " + rowsTransformed);
            System.out.println("Output written to: " + outputPath);

        } catch (FileNotFoundException e) {
            System.out.println("Error: Input file not found at " + inputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double round(double value, int places) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static String getPriceRange(double price) {
        if (price >= 0 && price <= 10) return "Low";
        if (price > 10 && price <= 100) return "Medium";
        if (price > 100 && price <= 500) return "High";
        return "Premium";
    }
}

