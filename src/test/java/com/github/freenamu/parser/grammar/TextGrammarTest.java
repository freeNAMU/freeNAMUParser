package com.github.freenamu.parser.grammar;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;

public class TextGrammarTest {
    private TextGrammar textGrammar;

    @Before
    public void setUp() {
        textGrammar = new TextGrammar();
    }

    @Test
    public void should_return_list_having_one_text() {
        // Given
        String rawText = "test";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text(rawText));

        // When
        List<Node> actual = textGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void should_return_list_having_nothing_if_called_with_empty_string() {
        // Given
        String rawText = "";
        List<Node> expected = new ArrayList<>();

        // When
        List<Node> actual = textGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}
