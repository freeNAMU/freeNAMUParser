package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Italic;
import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.*;

public class ItalicGrammarTest {
    private ItalicGrammar italicGrammar;

    @Before
    public void setUp() {
        italicGrammar = new ItalicGrammar();
    }

    @Test
    public void should_match_italic_grammar() {
        // Given
        String rawText = "test1''test2''test3";
        int expectedStart = 5;
        int expectedEnd = 14;

        // When
        boolean actualMatch = italicGrammar.match(rawText);
        int actualStart = italicGrammar.getStart();
        int actualEnd = italicGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_not_match_italic_grammar_with_line_feed() {
        // Given
        String rawText = "test1''te\nst2''test3";

        // When
        boolean actualMatch = italicGrammar.match(rawText);

        // Then
        assertFalse(actualMatch);
    }

    @Test
    public void should_parse_italic_grammar() {
        // Given
        String rawText = "''test''";
        List<Node> expected = new ArrayList<>();
        expected.add(new Italic(new Text("test")));

        // When
        italicGrammar.match(rawText);
        List<Node> actual = italicGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}
