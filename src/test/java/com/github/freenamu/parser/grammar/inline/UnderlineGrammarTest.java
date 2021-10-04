package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import com.github.freenamu.node.Underline;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.*;

public class UnderlineGrammarTest {
    private UnderlineGrammar underlineGrammar;

    @Before
    public void setUp() {
        underlineGrammar = new UnderlineGrammar();
    }

    @Test
    public void match_underline_grammar() {
        // Given
        String rawText = "test1__test2__test3";
        Integer expected = 5;

        // When
        Integer actual = underlineGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void not_match_underline_grammar_with_line_feed() {
        // Given
        String rawText = "test1__te\nst2__test3";

        // When
        Integer actual = underlineGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parse_underline_grammar() {
        // Given
        String rawText = "test1__test2__test3";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Underline(new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = underlineGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_underline_grammar_only() {
        // Given
        String rawText = "__test1__";
        List<Node> expected = new ArrayList<>();
        expected.add(new Underline(new Text("test1")));

        // When
        List<Node> actual = underlineGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}