package com.github.freenamu.parser.generator;

import java.util.HashSet;
import java.util.Set;

public class FootnoteAnchorGenerator {
    protected Set<String> labels = new HashSet<>();
    int candidateAnchor = 1;

    public void registerAnchor(String label) {
        labels.add(label);
    }

    public String getNextAnchor() {
        while (labels.contains(String.valueOf(candidateAnchor))) {
            candidateAnchor++;
        }
        return String.valueOf(candidateAnchor);
    }
}
