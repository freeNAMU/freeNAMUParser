package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.singleline.Footnote;
import com.github.freenamu.parser.node.singleline.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FootnoteGrammarTest {
    private FootnoteGrammar footnoteGrammar;

    @Before
    public void setUp() {
        footnoteGrammar = new FootnoteGrammar();
    }

    @Test
    public void matchFootnoteGrammar() {
        // Given
        String rawText = "test1[* test2]test3";
        Integer expected = 5;

        // When
        Integer actual = footnoteGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void matchFootnoteGrammarWithAnchor() {
        // Given
        String rawText = "test1[*test2 test3]test4";
        Integer expected = 5;

        // When
        Integer actual = footnoteGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void notMatchFootnoteGrammarWithLineFeed() {
        // Given
        String rawText = "test1[* te\nst2]test3";

        // When
        Integer actual = footnoteGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parseFootnoteGrammar() {
        // Given
        String rawText = "test1[* test2]test3";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Footnote(null, new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = footnoteGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseFootnoteGrammarWithAnchor() {
        // Given
        String rawText = "test1[*test2 test3]test4";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Footnote("test2", new Text("test3")));
        expected.add(new Text("test4"));

        // When
        List<Node> actual = footnoteGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseFootnoteGrammarOnly() {
        // Given
        String rawText = "[* test2]";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text(""));
        expected.add(new Footnote(null, new Text("test2")));
        expected.add(new Text(""));

        // When
        List<Node> actual = footnoteGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseFootnoteGrammarWithAnchorOnly() {
        // Given
        String rawText = "[*test2 test3]";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text(""));
        expected.add(new Footnote("test2", new Text("test3")));
        expected.add(new Text(""));

        // When
        List<Node> actual = footnoteGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}