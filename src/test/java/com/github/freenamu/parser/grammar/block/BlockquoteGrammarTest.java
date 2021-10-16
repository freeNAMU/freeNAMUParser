package com.github.freenamu.parser.grammar.block;

import com.github.freenamu.node.Blockquote;
import com.github.freenamu.node.Break;
import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.addBreak;
import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BlockquoteGrammarTest {
    private BlockquoteGrammar blockquoteGrammar;

    @Before
    public void setUp() {
        blockquoteGrammar = new BlockquoteGrammar();
    }

    @Test
    public void should_match_1_line_blockquote_grammar() {
        // Given
        String rawText = "test1\n>test2\ntest3";
        int expectedStart = 5;
        int expectedEnd = 13;

        // When
        boolean actualMatch = blockquoteGrammar.match(rawText);
        int actualStart = blockquoteGrammar.getStart();
        int actualEnd = blockquoteGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_match_2_line_blockquote_grammar() {
        // Given
        String rawText = "test1\n>test2\n>test3\ntest4";
        int expectedStart = 5;
        int expectedEnd = 20;

        // When
        boolean actualMatch = blockquoteGrammar.match(rawText);
        int actualStart = blockquoteGrammar.getStart();
        int actualEnd = blockquoteGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_parse_1_line_blockquote_grammar() {
        // Given
        List<String> rawTexts = addBreak(">test");
        List<Node> expected = new ArrayList<>();
        expected.add(new Blockquote(new Text("test")));

        for (String rawText : rawTexts) {
            // When
            blockquoteGrammar.match(rawText);
            List<Node> actual = blockquoteGrammar.parse(rawText);

            // Then
            assertNodeListEquals(expected, actual);
        }
    }

    @Test
    public void should_parse_2_line_blockquote_grammar() {
        // Given
        List<String> rawTexts = addBreak(">test1\n>test2");
        List<Node> expected = new ArrayList<>();
        ArrayList<Node> children = new ArrayList<>();
        children.add(new Text("test1"));
        children.add(new Break());
        children.add(new Text("test2"));
        expected.add(new Blockquote(children));

        for (String rawText : rawTexts) {
            // When
            blockquoteGrammar.match(rawText);
            List<Node> actual = blockquoteGrammar.parse(rawText);

            // Then
            assertNodeListEquals(expected, actual);
        }
    }

    @Test
    public void should_parse_blockquote_grammar_in_blockquote_grammar() {
        // Given
        List<String> rawTexts = addBreak(">>test1\n>>test2");
        List<Node> expected = new ArrayList<>();
        ArrayList<Node> childrenOfInnerBlockquote = new ArrayList<>();
        childrenOfInnerBlockquote.add(new Text("test1"));
        childrenOfInnerBlockquote.add(new Break());
        childrenOfInnerBlockquote.add(new Text("test2"));
        expected.add(new Blockquote(new Blockquote(childrenOfInnerBlockquote)));

        for (String rawText : rawTexts) {
            // When
            blockquoteGrammar.match(rawText);
            List<Node> actual = blockquoteGrammar.parse(rawText);

            // Then
            assertNodeListEquals(expected, actual);
        }
    }

    @Test
    public void should_parse_blockquote_with_empty_line() {
        // Given
        List<String> rawTexts = addBreak(">test1\n>\n>test2");
        List<Node> expected = new ArrayList<>();
        ArrayList<Node> children = new ArrayList<>();
        children.add(new Text("test1"));
        children.add(new Break());
        children.add(new Break());
        children.add(new Text("test2"));
        expected.add(new Blockquote(children));

        for (String rawText : rawTexts) {
            // When
            blockquoteGrammar.match(rawText);
            List<Node> actual = blockquoteGrammar.parse(rawText);

            // Then
            assertNodeListEquals(expected, actual);
        }
    }

    @Test
    public void should_parse_blockquote_without_child() {
        // Given
        List<String> rawTexts = addBreak(">");
        List<Node> expected = new ArrayList<>();
        expected.add(new Blockquote(new ArrayList<>()));

        for (String rawText : rawTexts) {
            // When
            blockquoteGrammar.match(rawText);
            List<Node> actual = blockquoteGrammar.parse(rawText);

            // Then
            assertNodeListEquals(expected, actual);
        }
    }
}
