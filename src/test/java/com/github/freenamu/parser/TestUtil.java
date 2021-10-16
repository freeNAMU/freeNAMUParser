package com.github.freenamu.parser;

import com.github.freenamu.node.Node;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestUtil {
    public static void assertNodeListEquals(List<Node> expected, List<Node> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    public static List<String> addBreak(String rawText) {
        List<String> rawTexts = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                String preBreak = i == 0 ? "" : "\n";
                String postBreak = j == 0 ? "" : "\n";
                rawTexts.add(preBreak + rawText + postBreak);
            }
        }
        return rawTexts;
    }
}
