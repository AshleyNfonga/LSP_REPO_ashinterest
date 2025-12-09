package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;
import java.util.Random;

/**
 * PasswordGeneratorService: singleton + strategy (compact implementation).
 *
 * DESIGN PATTERN:
 * - Singleton: single shared instance via getInstance().
 * - Strategy: runtime-swappable algorithms via internal Alg implementations.
 */
public class PasswordGeneratorService {
    private static final PasswordGeneratorService INSTANCE = new PasswordGeneratorService();

    private interface Alg { String gen(int len); }
    private Alg alg;

    private PasswordGeneratorService() {}

    public static PasswordGeneratorService getInstance() {
        return INSTANCE;
    }

    /**
     * Set the algorithm to generate passwords.
     * @param name "basic", "enhanced", "letters" (case-insensitive)
     * @throws IllegalArgumentException if algorithm is unsupported
     */
    public void setAlgorithm(String name) {
        if (name == null) {
            alg = null; // allow clearing the algorithm
            return;
        }
        switch (name.toLowerCase().trim()) {
            case "basic":
                alg = (n) -> pick("0123456789", new Random(), n);
                break;
            case "enhanced":
                alg = (n) -> pick("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789", new SecureRandom(), n);
                break;
            case "letters":
                alg = (n) -> pick("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", new Random(), n);
                break;
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + name);
        }
    }

    /**
     * Generate a password using the selected algorithm.
     * @param length number of characters
     * @return generated password
     * @throws IllegalStateException if no algorithm is selected
     * @throws IllegalArgumentException if length < 0
     */
    public String generatePassword(int length) {
        if (alg == null) {
            throw new IllegalStateException("No algorithm selected");
        }
        if (length < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        return alg.gen(length);
    }

    /** Helper method to pick random characters */
    private static String pick(String chars, Random rnd, int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
