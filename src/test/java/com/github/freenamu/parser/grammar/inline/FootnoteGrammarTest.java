package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Anchor;
import com.github.freenamu.node.Footnote;
import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.*;

public class FootnoteGrammarTest {
    private FootnoteGrammar footnoteGrammar;

    @Before
    public void setUp() {
        footnoteGrammar = new FootnoteGrammar();
    }

    @Test
    public void match_footnote_grammar() {
        // Given
        String rawText = "test1[* test2]test3";
        Integer expected = 5;

        // When
        Integer actual = footnoteGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void match_footnote_grammar_with_anchor() {
        // Given
        String rawText = "test1[*test2 test3]test4";
        Integer expected = 5;

        // When
        Integer actual = footnoteGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void not_match_footnote_grammar_with_line_feed() {
        // Given
        String rawText = "test1[* te\nst2]test3";

        // When
        Integer actual = footnoteGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parse_footnote_grammar() {
        // Given
        String rawText = "test1[* test2]test3";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Footnote(null, new Text("test2")));
        expected.add(new Text("test3"));
        footnoteGrammar.getFirstMatchStartIndex(rawText);

        // When
        List<Node> actual = footnoteGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_footnote_grammar_with_anchor() {
        // Given
        String rawText = "test1[*test2 test3]test4";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Footnote("test2", new Text("test3")));
        expected.add(new Text("test4"));
        footnoteGrammar.getFirstMatchStartIndex(rawText);

        // When
        List<Node> actual = footnoteGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_footnote_grammar_only() {
        // Given
        String rawText = "[* test1]";
        List<Node> expected = new ArrayList<>();
        expected.add(new Footnote(null, new Text("test1")));
        footnoteGrammar.getFirstMatchStartIndex(rawText);

        // When
        List<Node> actual = footnoteGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_footnote_grammar_with_anchor_only() {
        // Given
        String rawText = "[*test1 test2]";
        List<Node> expected = new ArrayList<>();
        expected.add(new Footnote("test1", new Text("test2")));
        footnoteGrammar.getFirstMatchStartIndex(rawText);

        // When
        List<Node> actual = footnoteGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_footnote_grammar_with_anchor_grammar() {
        // Given
        String rawText = "[* [[test1]]]";
        List<Node> expected = new ArrayList<>();
        expected.add(new Footnote(null, new Anchor("test1")));
        footnoteGrammar.getFirstMatchStartIndex(rawText);

        // When
        List<Node> actual = footnoteGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}
