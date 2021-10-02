package com.github.freenamu.parser.grammar.paragraph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParagraphGrammarTest {
    private ParagraphGrammar paragraphGrammar;

    @BeforeEach
    public void setUp() {
        paragraphGrammar = new ParagraphGrammar();
    }

    @Test
    public void matchLevel2UnfoldParagraphGrammar() {
        // Given
        String rawText = "test1\n== test2 ==\ntest3";
        int expected = 6;

        // When
        int actual = paragraphGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void matchLevel2FoldParagraphGrammar() {
        // Given
        String rawText = "test1\n==# test2 #==\ntest3";
        int expected = 6;

        // When
        int actual = paragraphGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void notMatchLevel1UnFoldParagraphGrammar() {
        // Given
        String rawText = "test1\n= test2 =\ntest3";

        // When
        Integer actual = paragraphGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void notMatchLevel1FoldParagraphGrammar() {
        // Given
        String rawText = "test1\n=# test2 #=\ntest3";

        // When
        Integer actual = paragraphGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void notMatchLevel3UnFoldParagraphGrammar() {
        // Given
        String rawText = "test1\n=== test2 ===\ntest3";

        // When
        Integer actual = paragraphGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void notMatchLevel3FoldParagraphGrammar() {
        // Given
        String rawText = "test1\n===# test2 #===\ntest3";

        // When
        Integer actual = paragraphGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }
}
