package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Break;
import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BreakGrammarTest {
    private BreakGrammar breakGrammar;

    @Before
    public void setUp() {
        breakGrammar = new BreakGrammar();
    }

    @Test
    public void match_break_grammar() {
        // Given
        String rawText = "test1\ntest2";
        Integer expected = 5;

        // When
        Integer actual = breakGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void parse_break_grammar() {
        // Given
        String rawText = "test1\ntest2";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Break());
        expected.add(new Text("test2"));

        // When
        List<Node> actual = breakGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_break_grammar_only() {
        // Given
        String rawText = "\n";
        List<Node> expected = new ArrayList<>();
        expected.add(new Break());

        // When
        List<Node> actual = breakGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}
