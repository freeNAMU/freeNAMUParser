package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.Superscript;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.*;

public class SuperscriptGrammarTest {
    private SuperscriptGrammar superscriptGrammar;

    @Before
    public void setUp() {
        superscriptGrammar = new SuperscriptGrammar();
    }

    @Test
    public void should_match_superscript_grammar() {
        // Given
        String rawText = "test1^^test2^^test3";
        int expectedStart = 5;
        int expectedEnd = 14;

        // When
        boolean actualMatch = superscriptGrammar.match(rawText);
        int actualStart = superscriptGrammar.getStart();
        int actualEnd = superscriptGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_not_match_superscript_grammar_with_line_feed() {
        // Given
        String rawText = "test1^^te\nst2^^test3";

        // When
        boolean actualMatch = superscriptGrammar.match(rawText);

        // Then
        assertFalse(actualMatch);
    }

    @Test
    public void should_parse_superscript_grammar() {
        // Given
        String rawText = "^^test^^";
        List<Node> expected = new ArrayList<>();
        expected.add(new Superscript(new Text("test")));

        // When
        List<Node> actual = superscriptGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}