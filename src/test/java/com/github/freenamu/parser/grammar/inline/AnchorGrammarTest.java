package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Anchor;
import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.*;

public class AnchorGrammarTest {
    private AnchorGrammar anchorGrammar;

    @Before
    public void setUp() {
        anchorGrammar = new AnchorGrammar();
    }

    @Test
    public void should_match_anchor_grammar() {
        // Given
        String rawText = "test1[[test2]]test3";
        int expectedStart = 5;
        int expectedEnd = 14;

        // When
        boolean actualMatch = anchorGrammar.match(rawText);
        int actualStart = anchorGrammar.getStart();
        int actualEnd = anchorGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_match_anchor_grammar_with_display_text() {
        // Given
        String rawText = "test1[[test2|test3]]test4";
        int expectedStart = 5;
        int expectedEnd = 20;

        // When
        boolean actualMatch = anchorGrammar.match(rawText);
        int actualStart = anchorGrammar.getStart();
        int actualEnd = anchorGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_not_match_anchor_grammar_with_line_feed() {
        // Given
        String rawText = "test1[[te\nst2]]test3";

        // When
        boolean actualMatch = anchorGrammar.match(rawText);

        // Then
        assertFalse(actualMatch);
    }

    @Test
    public void should_not_match_anchor_grammar_with_display_text_and_line_feed() {
        // Given
        String rawText = "test1[[test2|te\nst3]]test4";

        // When
        boolean actualMatch = anchorGrammar.match(rawText);

        // Then
        assertFalse(actualMatch);
    }

    @Test
    public void should_parse_anchor_grammar() {
        // Given
        String rawText = "[[test]]";
        List<Node> expected = new ArrayList<>();
        expected.add(new Anchor("test"));

        // When
        anchorGrammar.match(rawText);
        List<Node> actual = anchorGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void should_parse_anchor_grammar_with_display_text() {
        // Given
        String rawText = "[[test1|test2]]";
        List<Node> expected = new ArrayList<>();
        expected.add(new Anchor("test1", new Text("test2")));

        // When
        anchorGrammar.match(rawText);
        List<Node> actual = anchorGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}