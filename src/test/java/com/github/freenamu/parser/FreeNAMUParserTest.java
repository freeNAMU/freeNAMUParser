package com.github.freenamu.parser;

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
    public void parse_article_not_having_paragraph() {
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
    public void parse_article_having_paragraph() {
        // Given
        String rawText = "test1\n== test2 ==\ntest3\n===# test4 #===";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Paragraph("test2", 2, false, new Text("test3")));
        expected.add(new Paragraph("test4", 3, true));

        // When
        List<Node> actual = freeNAMUParser.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}