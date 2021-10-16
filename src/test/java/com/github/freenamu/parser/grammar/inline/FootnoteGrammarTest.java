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
    public void should_match_footnote_grammar() {
        // Given
        String rawText = "test1[* test2]test3";
        int expectedStart = 5;
        int expectedEnd = 14;

        // When
        boolean actualMatch = footnoteGrammar.match(rawText);
        int actualStart = footnoteGrammar.getStart();
        int actualEnd = footnoteGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_match_footnote_grammar_with_anchor() {
        // Given
        String rawText = "test1[*test2 test3]test4";
        int expectedStart = 5;
        int expectedEnd = 19;

        // When
        boolean actualMatch = footnoteGrammar.match(rawText);
        int actualStart = footnoteGrammar.getStart();
        int actualEnd = footnoteGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_not_match_footnote_grammar_with_line_feed() {
        // Given
        String rawText = "test1[* te\nst2]test3";

        // When
        boolean actualMatch = footnoteGrammar.match(rawText);

        // Then
        assertFalse(actualMatch);
    }

    @Test
    public void should_parse_footnote_grammar() {
        // Given
        String rawText = "[* test]";
        List<Node> expected = new ArrayList<>();
        expected.add(new Footnote(null, new Text("test")));

        // When
        footnoteGrammar.match(rawText);
        List<Node> actual = footnoteGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void should_parse_footnote_grammar_with_anchor() {
        // Given
        String rawText = "[*test1 test2]";
        List<Node> expected = new ArrayList<>();
        expected.add(new Footnote("test1", new Text("test2")));

        // When
        footnoteGrammar.match(rawText);
        List<Node> actual = footnoteGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void should_parse_footnote_grammar_with_anchor_grammar() {
        // Given
        String rawText = "[* [[test]]]";
        footnoteGrammar.match(rawText);
        List<Node> expected = new ArrayList<>();
        expected.add(new Footnote(null, new Anchor("test")));

        // When
        List<Node> actual = footnoteGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}
