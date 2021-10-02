package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.node.Footnote;
import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FootnoteGrammarTest {
    private FootnoteGrammar footnoteGrammar;

    @BeforeEach
    public void setUp() {
        footnoteGrammar = new FootnoteGrammar();
    }

    @Test
    public void matchFootnoteGrammar() {
        // Given
        String rawText = "test1[* test2]test3";
        int expected = 5;

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
        int expected = 5;

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
        Footnote footnote = new Footnote(null);
        footnote.add(new Text("test2"));
        expected.add(footnote);
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
        Footnote footnote = new Footnote("test2");
        footnote.add(new Text("test3"));
        expected.add(footnote);
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
        Footnote footnote = new Footnote(null);
        footnote.add(new Text("test2"));
        expected.add(footnote);
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
        Footnote footnote = new Footnote("test2");
        footnote.add(new Text("test3"));
        expected.add(footnote);
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