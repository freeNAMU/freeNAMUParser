package com.github.freenamu.parser.grammar.multiline;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.multiline.Quotation;
import com.github.freenamu.node.singleline.LineFeed;
import com.github.freenamu.node.singleline.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuotationGrammarTest {
    private QuotationGrammar quotationGrammar;

    @Before
    public void setUp() {
        quotationGrammar = new QuotationGrammar();
    }

    @Test
    public void match1LineQuotationGrammar() {
        // Given
        String rawText = "test1\n>test2\ntest3";
        int expected = 6;

        // When
        int actual = quotationGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void match2LineQuotationGrammar() {
        // Given
        String rawText = "test1\n>test2\n>test3\ntest4";
        int expected = 6;

        // When
        int actual = quotationGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void parse1LineQuotationGrammar() {
        // Given
        String rawText = "test1\n>test2\ntest3";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Quotation(new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = quotationGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parse2LineQuotationGrammar() {
        // Given
        String rawText = "test1\n>test2\n>test3\ntest4";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        ArrayList<Node> children = new ArrayList<>();
        children.add(new Text("test2"));
        children.add(new LineFeed());
        children.add(new Text("test3"));
        expected.add(new Quotation(children));
        expected.add(new Text("test4"));

        // When
        List<Node> actual = quotationGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseQuotationGrammarInQuotationGrammar() {
        // Given
        String rawText = "test1\n>>test2\n>>test3\ntest4";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        ArrayList<Node> childrenOfInnerQuotation = new ArrayList<>();
        childrenOfInnerQuotation.add(new Text("test2"));
        childrenOfInnerQuotation.add(new LineFeed());
        childrenOfInnerQuotation.add(new Text("test3"));
        ArrayList<Node> childrenOfOuterQuotation = new ArrayList<>();
        childrenOfOuterQuotation.add(new Text(""));
        childrenOfOuterQuotation.add(new Quotation(childrenOfInnerQuotation));
        childrenOfOuterQuotation.add(new Text(""));
        expected.add(new Quotation(childrenOfOuterQuotation));
        expected.add(new Text("test4"));

        // When
        List<Node> actual = quotationGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}