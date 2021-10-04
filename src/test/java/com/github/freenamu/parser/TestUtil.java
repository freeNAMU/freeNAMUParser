package com.github.freenamu.parser;

import com.github.freenamu.node.Node;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestUtil {
    public static void assertNodeListEquals(List<Node> expected, List<Node> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
