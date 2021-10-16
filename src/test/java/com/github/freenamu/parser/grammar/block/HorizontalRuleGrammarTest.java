package com.github.freenamu.parser.grammar.block;

import com.github.freenamu.node.HorizontalRule;
import com.github.freenamu.node.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.addBreak;
import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.*;

public class HorizontalRuleGrammarTest {
    private HorizontalRuleGrammar horizontalRuleGrammar;

    @Before
    public void setUp() {
        horizontalRuleGrammar = new HorizontalRuleGrammar();
    }

    @Test
    public void should_match_horizontal_rule_grammar_with_4_to_9_hyphen() {
        for (int i = 4; i <= 9; i++) {
            // Given
            String rawText = "test1\n" + "-".repeat(i) + "\ntest2";
            int expectedStart = 5;
            int expectedEnd = expectedStart + i + 2;

            // When
            boolean actualMatch = horizontalRuleGrammar.match(rawText);
            int actualStart = horizontalRuleGrammar.getStart();
            int actualEnd = horizontalRuleGrammar.getEnd();

            // Then
            assertTrue(actualMatch);
            assertEquals(expectedStart, actualStart);
            assertEquals(expectedEnd, actualEnd);
        }
    }

    @Test
    public void should_not_match_horizontal_rule_grammar_with_3_hyphen() {
        // Given
        String rawText = "test1\n" + "-".repeat(3) + "\ntest2";

        // When
        boolean actualMatch = horizontalRuleGrammar.match(rawText);

        // Then
        assertFalse(actualMatch);
    }

    @Test
    public void should_not_match_horizontal_rule_grammar_with_10_hyphen() {
        // Given
        String rawText = "test1\n" + "-".repeat(10) + "\ntest2";

        // When
        boolean actualMatch = horizontalRuleGrammar.match(rawText);

        // Then
        assertFalse(actualMatch);
    }

    @Test
    public void should_parse_horizontal_rule_grammar() {
        for (int i = 4; i <= 9; i++) {
            // Given
            List<String> rawTexts = addBreak("-".repeat(i));
            List<Node> expected = new ArrayList<>();
            expected.add(new HorizontalRule());

            for (String rawText : rawTexts) {
                // When
                horizontalRuleGrammar.match(rawText);
                List<Node> actual = horizontalRuleGrammar.parse(rawText);

                // Then
                assertNodeListEquals(expected, actual);
            }
        }
    }
}
