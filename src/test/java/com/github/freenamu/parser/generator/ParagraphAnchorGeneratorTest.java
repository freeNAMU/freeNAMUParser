package com.github.freenamu.parser.generator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParagraphAnchorGeneratorTest {
    private ParagraphAnchorGenerator paragraphAnchorGenerator;

    @Before
    public void setUp() {
        paragraphAnchorGenerator = new ParagraphAnchorGenerator();
    }

    @Test
    public void should_create_1_when_called_first_with_level_2() {
        // Given
        String expected = "1";

        // When
        String actual = paragraphAnchorGenerator.getNextAnchor(2);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void should_create_2_when_called_second_with_level_2() {
        // Given
        paragraphAnchorGenerator.getNextAnchor(2);
        String expected = "2";

        // When
        String actual = paragraphAnchorGenerator.getNextAnchor(2);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void should_create_1_dot_1_when_called_with_level_3_after_level_2() {
        // Given
        paragraphAnchorGenerator.getNextAnchor(2);
        String expected = "1.1";

        // When
        String actual = paragraphAnchorGenerator.getNextAnchor(3);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void should_create_0_dot_1_when_called_with_level_3_only() {
        // Given
        String expected = "0.1";

        // When
        String actual = paragraphAnchorGenerator.getNextAnchor(3);

        // Then
        assertEquals(expected, actual);
    }
}