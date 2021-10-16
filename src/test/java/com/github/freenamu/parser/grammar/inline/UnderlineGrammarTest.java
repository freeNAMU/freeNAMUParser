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
        int expectedStart = 5;
        int expectedEnd = 14;

        // When
        boolean actualMatch = underlineGrammar.match(rawText);
        int actualStart = underlineGrammar.getStart();
        int actualEnd = underlineGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void not_match_underline_grammar_with_line_feed() {
        // Given
        String rawText = "test1__te\nst2__test3";

        // When
        boolean actualMatch = underlineGrammar.match(rawText);

        // Then
        assertFalse(actualMatch);
    }

    @Test
    public void parse_underline_grammar() {
        // Given
        String rawText = "__test__";
        List<Node> expected = new ArrayList<>();
        expected.add(new Underline(new Text("test")));

        // When
        List<Node> actual = underlineGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}
