package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Bold;
import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.*;

public class BoldGrammarTest {
    private BoldGrammar boldGrammar;

    @Before
    public void setUp() {
        boldGrammar = new BoldGrammar();
    }

    @Test
    public void should_match_bold_grammar() {
        // Given
        String rawText = "test1'''test2'''test3";
        int expectedStart = 5;
        int expectedEnd = 16;

        // When
        boolean actualMatch = boldGrammar.match(rawText);
        int actualStart = boldGrammar.getStart();
        int actualEnd = boldGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_not_match_bold_grammar_with_line_feed() {
        // Given
        String rawText = "test1'''te\nst2'''test3";

        // When
        boolean actualMatch = boldGrammar.match(rawText);

        // Then
        assertFalse(actualMatch);
    }

    @Test
    public void should_parse_bold_grammar() {
        // Given
        String rawText = "'''test'''";
        List<Node> expected = new ArrayList<>();
        expected.add(new Bold(new Text("test")));

        // When
        boldGrammar.match(rawText);
        List<Node> actual = boldGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}
