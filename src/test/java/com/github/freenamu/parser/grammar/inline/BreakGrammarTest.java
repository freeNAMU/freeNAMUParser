package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Break;
import com.github.freenamu.node.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BreakGrammarTest {
    private BreakGrammar breakGrammar;

    @Before
    public void setUp() {
        breakGrammar = new BreakGrammar();
    }

    @Test
    public void should_match_break_grammar() {
        // Given
        String rawText = "test1\ntest2";
        int expectedStart = 5;
        int expectedEnd = 6;

        // When
        boolean actualMatch = breakGrammar.match(rawText);
        int actualStart = breakGrammar.getStart();
        int actualEnd = breakGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_parse_break_grammar() {
        // Given
        String rawText = "\n";
        List<Node> expected = new ArrayList<>();
        expected.add(new Break());

        // When
        breakGrammar.match(rawText);
        List<Node> actual = breakGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}
