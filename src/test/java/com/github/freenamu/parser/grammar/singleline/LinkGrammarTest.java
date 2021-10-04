package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.singleline.Link;
import com.github.freenamu.node.singleline.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LinkGrammarTest {
    private LinkGrammar linkGrammar;

    @Before
    public void setUp() {
        linkGrammar = new LinkGrammar();
    }

    @Test
    public void matchLinkGrammar() {
        // Given
        String rawText = "test1[[test2]]test3";
        Integer expected = 5;

        // When
        Integer actual = linkGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void matchLinkGrammarWithDisplayText() {
        // Given
        String rawText = "test1[[test2|test3]]test4";
        Integer expected = 5;

        // When
        Integer actual = linkGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void notMatchLinkGrammarWithLineFeed() {
        // Given
        String rawText = "test1[[te\nst2]]test3";

        // When
        Integer actual = linkGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void notMatchLinkGrammarWithDisplayTextAndLineFeed() {
        // Given
        String rawText = "test1[[test2|te\nst3]]test4";

        // When
        Integer actual = linkGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parseLinkGrammar() {
        // Given
        String rawText = "test1[[test2]]test3";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Link("test2"));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = linkGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseLinkGrammarWithDisplayText() {
        // Given
        String rawText = "test1[[test2|test3]]test4";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Link("test2", new Text("test3")));
        expected.add(new Text("test4"));

        // When
        List<Node> actual = linkGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseLinkGrammarOnly() {
        // Given
        String rawText = "[[test2]]";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text(""));
        expected.add(new Link("test2"));
        expected.add(new Text(""));

        // When
        List<Node> actual = linkGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseLinkGrammarWithDisplayTextOnly() {
        // Given
        String rawText = "[[test2|test3]]";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text(""));
        expected.add(new Link("test2", new Text("test3")));
        expected.add(new Text(""));

        // When
        List<Node> actual = linkGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}