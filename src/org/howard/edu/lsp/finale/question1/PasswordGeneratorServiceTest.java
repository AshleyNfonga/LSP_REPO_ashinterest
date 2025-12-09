package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    @BeforeEach
    void setUp() {
        service = PasswordGeneratorService.getInstance();
        service.setAlgorithm(null); // clear algorithm before each test
    }

    @Test
    void checkInstanceNotNull() {
        assertNotNull(service, "Service instance should not be null");
    }

    @Test
    void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        assertSame(service, second, "Both instances should be the same object (singleton)");
    }

    @Test
    void generateWithoutSettingAlgorithmThrowsException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
            () -> service.generatePassword(5),
            "Expected exception if no algorithm is set");
        assertEquals("No algorithm selected", exception.getMessage());
    }

    @Test
    void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        assertEquals(10, p.length(), "Password length should match requested length");
        assertTrue(p.matches("\\d+"), "Password should contain digits only");
    }

    @Test
    void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        assertEquals(12, p.length(), "Password length should match requested length");
        assertTrue(p.matches("[A-Za-z0-9]+"), "Password should contain letters and digits only");
    }

    @Test
    void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        assertEquals(8, p.length(), "Password length should match requested length");
        assertTrue(p.matches("[A-Za-z]+"), "Password should contain letters only");
    }

    @Test
    void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(5);
        service.setAlgorithm("letters");
        String p2 = service.generatePassword(5);
        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(5);

        assertTrue(p1.matches("\\d+"), "Basic algorithm should generate digits only");
        assertTrue(p2.matches("[A-Za-z]+"), "Letters algorithm should generate letters only");
        assertTrue(p3.matches("[A-Za-z0-9]+"), "Enhanced algorithm should generate letters and digits");
    }
}
