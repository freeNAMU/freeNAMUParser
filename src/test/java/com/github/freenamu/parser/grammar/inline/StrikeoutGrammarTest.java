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
    public void match_strikeout_grammar_with_hyphen() {
        // Given
        String rawText = "test1--test2--test3";
        Integer expected = 5;

        // When
        Integer actual = strikeoutGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void match_strikeout_grammar_with_tilde() {
        // Given
        String rawText = "test1~~test2~~test3";
        Integer expected = 5;

        // When
        Integer actual = strikeoutGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void not_match_strikeout_grammar_with_hyphen_and_line_feed() {
        // Given
        String rawText = "test1--te\nst2--test3";

        // When
        Integer actual = strikeoutGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void not_match_strikeout_grammar_with_tilde_and_line_feed() {
        // Given
        String rawText = "test1~~te\nst2~~test3";

        // When
        Integer actual = strikeoutGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parse_strikeout_grammar_with_hyphen() {
        // Given
        String rawText = "test1--test2--test3";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Strikeout(new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = strikeoutGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_strikeout_grammar_with_tilde() {
        // Given
        String rawText = "test1~~test2~~test3";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Strikeout(new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = strikeoutGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_strikeout_grammar_with_hyphen_only() {
        // Given
        String rawText = "--test1--";
        List<Node> expected = new ArrayList<>();
        expected.add(new Strikeout(new Text("test1")));

        // When
        List<Node> actual = strikeoutGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_strikeout_grammar_with_tilde_only() {
        // Given
        String rawText = "~~test1~~";
        List<Node> expected = new ArrayList<>();
        expected.add(new Strikeout(new Text("test1")));

        // When
        List<Node> actual = strikeoutGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}