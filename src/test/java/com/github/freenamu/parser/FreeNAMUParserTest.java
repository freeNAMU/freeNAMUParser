package com.github.freenamu.parser;

import com.github.freenamu.node.Anchor;
import com.github.freenamu.node.Node;
import com.github.freenamu.node.Paragraph;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;

public class FreeNAMUParserTest {
    private FreeNAMUParser freeNAMUParser;

    @Before
    public void setUp() {
        this.freeNAMUParser = new FreeNAMUParser();
    }

    @Test
    public void should_parse_article_not_having_paragraph() {
        // Given
        String rawText = "test1";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));

        // When
        List<Node> actual = freeNAMUParser.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void should_parse_article_having_paragraph() {
        // Given
        String rawText = "test1\n== test2 ==\ntest3\n===# test4 #===";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        List<Node> title1 = new ArrayList<>();
        title1.add(new Text("test2"));
        expected.add(new Paragraph(title1, 2, false, new Text("test3")));
        List<Node> title2 = new ArrayList<>();
        title2.add(new Text("test4"));
        expected.add(new Paragraph(title2, 3, true));

        // When
        List<Node> actual = freeNAMUParser.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void should_parse_article_having_inline_grammar_in_title() {
        // Given
        String rawText = "== [[test1]] ==\ntest2";
        List<Node> expected = new ArrayList<>();
        List<Node> title = new ArrayList<>();
        title.add(new Anchor("test1"));
        expected.add(new Paragraph(title, 2, false, new Text("test2")));

        // When
        List<Node> actual = freeNAMUParser.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}