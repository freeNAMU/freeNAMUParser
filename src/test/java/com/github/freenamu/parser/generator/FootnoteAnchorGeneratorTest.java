package com.github.freenamu.parser.generator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FootnoteAnchorGeneratorTest {
    private FootnoteAnchorGenerator footnoteAnchorGenerator;

    @Before
    public void setUp() {
        footnoteAnchorGenerator = new FootnoteAnchorGenerator();
    }

    @Test
    public void should_create_1_when_called_first() {
        // Given
        String expected = "1";

        // When
        String actual = footnoteAnchorGenerator.getNextAnchor();

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void should_create_consecutive_numbers() {
        // Given
        int size = 10;
        List<String> expected = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            expected.add(String.valueOf(i));
        }

        // When
        List<String> actual = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            String label = footnoteAnchorGenerator.getNextAnchor();
            actual.add(label);
            footnoteAnchorGenerator.registerAnchor(label);
        }

        // Then
        for (int i = 0; i < size; i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void should_skip_registered_number() {
        // Given
        int size = 10;
        for (int i = 1; i <= size; i++) {
            footnoteAnchorGenerator.registerAnchor(String.valueOf(i));
        }
        String expected = "11";

        // When
        String actual = footnoteAnchorGenerator.getNextAnchor();

        // Then
        assertEquals(expected, actual);
    }
}
