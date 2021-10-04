package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.singleline.Text;
import com.github.freenamu.node.singleline.Underline;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UnderlineGrammarTest {
    private UnderlineGrammar underlineGrammar;

    @Before
    public void setUp() {
        underlineGrammar = new UnderlineGrammar();
    }

    @Test
    public void matchUnderlineGrammar() {
        // Given
        String rawText = "test1__test2__test3";
        Integer expected = 5;

        // When
        Integer actual = underlineGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void notMatchUnderlineGrammarWithLineFeed() {
        // Given
        String rawText = "test1__te\nst2__test3";

        // When
        Integer actual = underlineGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parseUnderlineGrammar() {
        // Given
        String rawText = "test1__test2__test3";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Underline(new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = underlineGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseUnderlineGrammarOnly() {
        // Given
        String rawText = "__test2__";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text(""));
        expected.add(new Underline(new Text("test2")));
        expected.add(new Text(""));

        // When
        List<Node> actual = underlineGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}