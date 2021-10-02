package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.Superscript;
import com.github.freenamu.parser.node.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuperscriptGrammarTest {
    private SuperscriptGrammar superscriptGrammar;

    @BeforeEach
    public void setUp() {
        superscriptGrammar = new SuperscriptGrammar();
    }

    @Test
    public void matchSuperscriptGrammar() {
        // Given
        String rawText = "test1^^test2^^test3";
        int expected = 5;

        // When
        Integer actual = superscriptGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void notMatchSuperscriptGrammarWithLineFeed() {
        // Given
        String rawText = "test1^^te\nst2^^test3";

        // When
        Integer actual = superscriptGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parseSuperscriptGrammar() {
        // Given
        String rawText = "test1^^test2^^test3";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        Superscript superscript = new Superscript();
        superscript.add(new Text("test2"));
        expected.add(superscript);
        expected.add(new Text("test3"));

        // When
        List<Node> actual = superscriptGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseSuperscriptGrammarOnly() {
        // Given
        String rawText = "^^test2^^";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text(""));
        Superscript superscript = new Superscript();
        superscript.add(new Text("test2"));
        expected.add(superscript);
        expected.add(new Text(""));

        // When
        List<Node> actual = superscriptGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}