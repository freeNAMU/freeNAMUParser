package com.github.freenamu.parser.grammar.block;

import com.github.freenamu.node.Break;
import com.github.freenamu.node.Indent;
import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IndentGrammarTest {
    private IndentGrammar indentGrammar;

    @Before
    public void setUp() {
        indentGrammar = new IndentGrammar();
    }

    @Test
    public void match_1_line_indent_grammar() {
        // Given
        String rawText = "test1\n test2\ntest3";
        Integer expected = 5;

        // When
        Integer actual = indentGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void match_2_line_indent_grammar() {
        // Given
        String rawText = "test1\n test2\n test3\ntest4";
        Integer expected = 5;

        // When
        Integer actual = indentGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void parse_1_line_indent_grammar() {
        // Given
        String rawText = "test1\n test2\ntest3";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Indent(new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = indentGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_2_line_indent_grammar() {
        // Given
        String rawText = "test1\n test2\n test3\ntest4";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        ArrayList<Node> children = new ArrayList<>();
        children.add(new Text("test2"));
        children.add(new Break());
        children.add(new Text("test3"));
        expected.add(new Indent(children));
        expected.add(new Text("test4"));

        // When
        List<Node> actual = indentGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_indent_grammar_in_indent_grammar() {
        // Given
        String rawText = "test1\n  test2\n  test3\ntest4";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        ArrayList<Node> childrenOfInnerIndent = new ArrayList<>();
        childrenOfInnerIndent.add(new Text("test2"));
        childrenOfInnerIndent.add(new Break());
        childrenOfInnerIndent.add(new Text("test3"));
        expected.add(new Indent(new Indent(childrenOfInnerIndent)));
        expected.add(new Text("test4"));

        // When
        List<Node> actual = indentGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}