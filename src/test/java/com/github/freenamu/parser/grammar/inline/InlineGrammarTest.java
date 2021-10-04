package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Bold;
import com.github.freenamu.node.Italic;
import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;

public class InlineGrammarTest {
    private InlineGrammar inlineGrammar;

    @Before
    public void setUp() {
        inlineGrammar = new InlineGrammar();
    }

    @Test
    public void parse_bold_before_italic() {
        // Given
        String rawText = "'''''test1'''''";
        List<Node> expected = new ArrayList<>();
        expected.add(new Bold(new Text("''test1")));
        expected.add(new Text("''"));

        // When
        List<Node> actual = inlineGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_bold_and_italic_when_separated_with_space() {
        // Given
        String rawText = "''' ''test1'' '''";
        List<Node> expected = new ArrayList<>();
        List<Node> children = new ArrayList<>();
        children.add(new Text(" "));
        children.add(new Italic(new Text("test1")));
        children.add(new Text(" "));
        expected.add(new Bold(children));

        // When
        List<Node> actual = inlineGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}