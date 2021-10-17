package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.addBreak;
import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommentGrammarTest {
    private CommentGrammar commentGrammar;

    @Before
    public void setUp() {
        commentGrammar = new CommentGrammar();
    }

    @Test
    public void should_match_comment_grammar() {
        // Given
        String rawText = "test1\n##test2\ntest3";
        int expectedStart = 5;
        int expectedEnd = 14;

        // When
        boolean actualMatch = commentGrammar.match(rawText);
        int actualStart = commentGrammar.getStart();
        int actualEnd = commentGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_parse_comment_grammar() {
        // Given
        List<String> rawTexts = addBreak("##test");
        List<Node> expected = new ArrayList<>();

        for (String rawText : rawTexts) {
            // When
            commentGrammar.match(rawText);
            List<Node> actual = commentGrammar.parse(rawText);

            // Then
            assertNodeListEquals(expected, actual);
        }
    }
}
