package com.github.freenamu.parser.grammar.paragraph;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.paragraph.Paragraph;
import com.github.freenamu.node.singleline.Text;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LeveledParagraphGrammarTest {
    private LeveledParagraphGrammar leveledParagraphGrammar;

    @Test
    public void parseLeveledParagraphGrammarWithSameLevel() {
        // Given
        leveledParagraphGrammar = new LeveledParagraphGrammar(2);
        String rawText = "test1\n== test2 ==\ntest3\n==# test4 #==\ntest5";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Paragraph(2, false, "test2", new Text("test3")));
        expected.add(new Paragraph(2, true, "test4", new Text("test5")));

        // When
        List<Node> actual = leveledParagraphGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseLeveledParagraphGrammarHavingSubParagraph() {
        // Given
        leveledParagraphGrammar = new LeveledParagraphGrammar(2);
        String rawText = "test1\n== test2 ==\ntest3\n===# test4 #===\ntest5";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        List<Node> children = new ArrayList<>();
        children.add(new Text("test3"));
        children.add(new Paragraph(3, true, "test4", new Text("test5")));
        expected.add(new Paragraph(2, false, "test2", children));

        // When
        List<Node> actual = leveledParagraphGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
