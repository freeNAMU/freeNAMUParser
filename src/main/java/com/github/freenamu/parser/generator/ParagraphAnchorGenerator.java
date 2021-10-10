package com.github.freenamu.parser.generator;

import java.util.ArrayList;
import java.util.List;

public class ParagraphAnchorGenerator {
    List<Integer> anchors;

    public ParagraphAnchorGenerator() {
        anchors = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            anchors.add(0);
        }
    }

    public String getNextAnchor(int level) {
        anchors.set(level, anchors.get(level) + 1);
        for (int i = level + 1; i <= 6; i++) {
            anchors.set(i, 0);
        }

        List<String> result = new ArrayList<>();
        for (Integer integer : anchors.subList(2, level + 1)) {
            result.add(String.valueOf(integer));
        }

        return String.join(".", result);
    }
}
