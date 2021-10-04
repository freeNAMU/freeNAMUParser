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
    public void match_superscript_grammar() {
        // Given
        String rawText = "test1^^test2^^test3";
        Integer expected = 5;

        // When
        Integer actual = superscriptGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void not_match_superscript_grammar_with_line_feed() {
        // Given
        String rawText = "test1^^te\nst2^^test3";

        // When
        Integer actual = superscriptGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parse_superscript_grammar() {
        // Given
        String rawText = "test1^^test2^^test3";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Superscript(new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = superscriptGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_superscript_grammar_only() {
        // Given
        String rawText = "^^test1^^";
        List<Node> expected = new ArrayList<>();
        expected.add(new Superscript(new Text("test1")));

        // When
        List<Node> actual = superscriptGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}