package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.singleline.Bold;
import com.github.freenamu.parser.node.singleline.Italic;
import com.github.freenamu.parser.node.singleline.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuoteGrammarTest {
    private QuoteGrammar quoteGrammar;

    @Before
    public void setUp() {
        quoteGrammar = new QuoteGrammar();
    }

    @Test
    public void parseBoldGrammarWhenAppearFirst() {
        // Given
        String rawText = "test1'''test2'''test3''test4''test5";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Bold(new Text("test2")));
        expected.add(new Text("test3"));
        expected.add(new Italic(new Text("test4")));
        expected.add(new Text("test5"));

        // When
        List<Node> actual = quoteGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseItalicGrammarWhenAppearFirst() {
        // Given
        String rawText = "test1''test2''test3'''test4'''test5";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Italic(new Text("test2")));
        expected.add(new Text("test3"));
        expected.add(new Bold(new Text("test4")));
        expected.add(new Text("test5"));

        // When
        List<Node> actual = quoteGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseBoldGrammarWhenFiveQuoteAppear() {
        // Given
        String rawText = "test1'''''test2'''''test3";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Bold(new Text("''test2")));
        expected.add(new Text("''test3"));

        // When
        List<Node> actual = quoteGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseBoldGrammarFirstWhenThreeAndTwoQuoteAppear() {
        // Given
        String rawText = "test1''' ''test2'' '''test3";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        ArrayList<Node> children = new ArrayList<>();
        children.add(new Text(" "));
        children.add(new Italic(new Text("test2")));
        children.add(new Text(" "));
        expected.add(new Bold(children));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = quoteGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
