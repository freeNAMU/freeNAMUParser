package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.Strikeout;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.*;

public class StrikeoutGrammarTest {
    private StrikeoutGrammar strikeoutGrammar;

    @Before
    public void setUp() {
        strikeoutGrammar = new StrikeoutGrammar();
    }

    @Test
    public void should_match_strikeout_grammar_with_hyphen() {
        // Given
        String rawText = "test1--test2--test3";
        int expectedStart = 5;
        int expectedEnd = 14;

        // When
        boolean actualMatch = strikeoutGrammar.match(rawText);
        int actualStart = strikeoutGrammar.getStart();
        int actualEnd = strikeoutGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_match_strikeout_grammar_with_tilde() {
        // Given
        String rawText = "test1~~test2~~test3";
        int expectedStart = 5;
        int expectedEnd = 14;

        // When
        boolean actualMatch = strikeoutGrammar.match(rawText);
        int actualStart = strikeoutGrammar.getStart();
        int actualEnd = strikeoutGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_not_match_strikeout_grammar_with_hyphen_and_line_feed() {
        // Given
        String rawText = "test1--te\nst2--test3";

        // When
        boolean actualMatch = strikeoutGrammar.match(rawText);

        // Then
        assertFalse(actualMatch);
    }

    @Test
    public void should_not_match_strikeout_grammar_with_tilde_and_line_feed() {
        // Given
        String rawText = "test1~~te\nst2~~test3";

        // When
        boolean actualMatch = strikeoutGrammar.match(rawText);

        // Then
        assertFalse(actualMatch);
    }

    @Test
    public void should_parse_strikeout_grammar_with_hyphen() {
        // Given
        String rawText = "--test--";
        List<Node> expected = new ArrayList<>();
        expected.add(new Strikeout(new Text("test")));

        // When
        strikeoutGrammar.match(rawText);
        List<Node> actual = strikeoutGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void should_parse_strikeout_grammar_with_tilde() {
        // Given
        String rawText = "~~test~~";
        List<Node> expected = new ArrayList<>();
        expected.add(new Strikeout(new Text("test")));

        // When
        strikeoutGrammar.match(rawText);
        List<Node> actual = strikeoutGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}
