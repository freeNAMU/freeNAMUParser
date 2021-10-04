package com.github.freenamu.parser.grammar.block;

import com.github.freenamu.node.HorizontalRule;
import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.*;

public class HorizontalRuleGrammarTest {
    private HorizontalRuleGrammar horizontalRuleGrammar;

    @Before
    public void setUp() {
        horizontalRuleGrammar = new HorizontalRuleGrammar();
    }

    @Test
    public void match_horizontal_rule_grammar_with_4_hyphen() {
        // Given
        String rawText = "test1\n----\ntest2";
        Integer expected = 5;

        // When
        Integer actual = horizontalRuleGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void match_horizontal_rule_grammar_with_9_hyphen() {
        // Given
        String rawText = "test1\n---------\ntest2";
        Integer expected = 5;

        // When
        Integer actual = horizontalRuleGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void not_match_horizontal_rule_grammar_with_3_hyphen() {
        // Given
        String rawText = "test1\n---\ntest2";

        // When
        Integer actual = horizontalRuleGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void not_match_horizontal_rule_grammar_with_10_hyphen() {
        // Given
        String rawText = "test1\n----------\ntest2";

        // When
        Integer actual = horizontalRuleGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parse_horizontal_rule_grammar() {
        // Given
        String rawText = "test1\n----\ntest2";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new HorizontalRule());
        expected.add(new Text("test2"));

        // When
        List<Node> actual = horizontalRuleGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_horizontal_rule_grammar_only() {
        // Given
        String rawText = "----";
        List<Node> expected = new ArrayList<>();
        expected.add(new HorizontalRule());

        // When
        List<Node> actual = horizontalRuleGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}
