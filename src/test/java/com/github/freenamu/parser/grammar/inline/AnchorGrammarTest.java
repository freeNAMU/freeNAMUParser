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
    public void match_anchor_grammar() {
        // Given
        String rawText = "test1[[test2]]test3";
        Integer expected = 5;

        // When
        Integer actual = anchorGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void match_anchor_grammar_with_display_text() {
        // Given
        String rawText = "test1[[test2|test3]]test4";
        Integer expected = 5;

        // When
        Integer actual = anchorGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void not_match_anchor_grammar_with_line_feed() {
        // Given
        String rawText = "test1[[te\nst2]]test3";

        // When
        Integer actual = anchorGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void not_match_anchor_grammar_with_display_text_and_line_feed() {
        // Given
        String rawText = "test1[[test2|te\nst3]]test4";

        // When
        Integer actual = anchorGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parse_anchor_grammar() {
        // Given
        String rawText = "test1[[test2]]test3";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Anchor("test2"));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = anchorGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_anchor_grammar_with_display_text() {
        // Given
        String rawText = "test1[[test2|test3]]test4";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Anchor("test2", new Text("test3")));
        expected.add(new Text("test4"));

        // When
        List<Node> actual = anchorGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_anchor_grammar_only() {
        // Given
        String rawText = "[[test1]]";
        List<Node> expected = new ArrayList<>();
        expected.add(new Anchor("test1"));

        // When
        List<Node> actual = anchorGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parseanchorGrammarWithDisplayTextOnly() {
        // Given
        String rawText = "[[test1|test2]]";
        List<Node> expected = new ArrayList<>();
        expected.add(new Anchor("test1", new Text("test2")));

        // When
        List<Node> actual = anchorGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}