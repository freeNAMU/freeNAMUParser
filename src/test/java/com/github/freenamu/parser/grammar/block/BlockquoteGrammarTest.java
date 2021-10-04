package com.github.freenamu.parser.grammar.block;

import com.github.freenamu.node.Blockquote;
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

public class BlockquoteGrammarTest {
    private BlockquoteGrammar blockquoteGrammar;

    @Before
    public void setUp() {
        blockquoteGrammar = new BlockquoteGrammar();
    }

    @Test
    public void match_1_line_blockquote_grammar() {
        // Given
        String rawText = "test1\n>test2\ntest3";
        Integer expected = 5;

        // When
        Integer actual = blockquoteGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void match_2_line_blockquote_grammar() {
        // Given
        String rawText = "test1\n>test2\n>test3\ntest4";
        Integer expected = 5;

        // When
        Integer actual = blockquoteGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void parse_1_line_blockquote_grammar() {
        // Given
        String rawText = "test1\n>test2\ntest3";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Blockquote(new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = blockquoteGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_2_line_blockquote_grammar() {
        // Given
        String rawText = "test1\n>test2\n>test3\ntest4";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        ArrayList<Node> children = new ArrayList<>();
        children.add(new Text("test2"));
        children.add(new Break());
        children.add(new Text("test3"));
        expected.add(new Blockquote(children));
        expected.add(new Text("test4"));

        // When
        List<Node> actual = blockquoteGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_blockquote_grammar_in_blockquote_grammar() {
        // Given
        String rawText = "test1\n>>test2\n>>test3\ntest4";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        ArrayList<Node> childrenOfInnerBlockquote = new ArrayList<>();
        childrenOfInnerBlockquote.add(new Text("test2"));
        childrenOfInnerBlockquote.add(new Break());
        childrenOfInnerBlockquote.add(new Text("test3"));
        expected.add(new Blockquote(new Blockquote(childrenOfInnerBlockquote)));
        expected.add(new Text("test4"));

        // When
        List<Node> actual = blockquoteGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}