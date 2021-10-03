package com.github.freenamu.parser.grammar.multiline;

import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.multiline.Indent;
import com.github.freenamu.parser.node.singleline.LineFeed;
import com.github.freenamu.parser.node.singleline.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IndentGrammarTest {
    private IndentGrammar indentGrammar;

    @Before
    public void setUp() {
        indentGrammar = new IndentGrammar();
    }

    @Test
    public void match1LineIndentGrammar() {
        // Given
        String rawText = "test1\n test2\ntest3";
        int expected = 6;

        // When
        int actual = indentGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void match2LineIndentGrammar() {
        // Given
        String rawText = "test1\n test2\n test3\ntest4";
        int expected = 6;

        // When
        int actual = indentGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void parse1LineIndentGrammar() {
        // Given
        String rawText = "test1\n test2\ntest3";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Indent(new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = indentGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parse2LineIndentGrammar() {
        // Given
        String rawText = "test1\n test2\n test3\ntest4";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        ArrayList<Node> children = new ArrayList<>();
        children.add(new Text("test2"));
        children.add(new LineFeed());
        children.add(new Text("test3"));
        expected.add(new Indent(children));
        expected.add(new Text("test4"));

        // When
        List<Node> actual = indentGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseIndentGrammarInIndentGrammar() {
        // Given
        String rawText = "test1\n  test2\n  test3\ntest4";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        ArrayList<Node> childrenOfInnerIndent = new ArrayList<>();
        childrenOfInnerIndent.add(new Text("test2"));
        childrenOfInnerIndent.add(new LineFeed());
        childrenOfInnerIndent.add(new Text("test3"));
        ArrayList<Node> childrenOfOuterIndent = new ArrayList<>();
        childrenOfOuterIndent.add(new Text(""));
        childrenOfOuterIndent.add(new Indent(childrenOfInnerIndent));
        childrenOfOuterIndent.add(new Text(""));
        expected.add(new Indent(childrenOfOuterIndent));
        expected.add(new Text("test4"));

        // When
        List<Node> actual = indentGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}