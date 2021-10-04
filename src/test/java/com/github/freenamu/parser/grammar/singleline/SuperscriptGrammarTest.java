package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.singleline.Superscript;
import com.github.freenamu.node.singleline.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SuperscriptGrammarTest {
    private SuperscriptGrammar superscriptGrammar;

    @Before
    public void setUp() {
        superscriptGrammar = new SuperscriptGrammar();
    }

    @Test
    public void matchSuperscriptGrammar() {
        // Given
        String rawText = "test1^^test2^^test3";
        Integer expected = 5;

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
        expected.add(new Superscript(new Text("test2")));
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
        expected.add(new Superscript(new Text("test2")));
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