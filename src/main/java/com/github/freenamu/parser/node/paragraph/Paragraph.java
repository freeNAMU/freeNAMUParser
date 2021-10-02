package com.github.freenamu.parser.node.paragraph;

import com.github.freenamu.parser.node.Node;

import java.util.List;
import java.util.Objects;

public class Paragraph extends Node {
    private final int level;
    private final boolean fold;
    private final String title;

    public Paragraph(int level, boolean fold, String title, Node child) {
        super(Type.Paragraph, child);
        this.level = level;
        this.fold = fold;
        this.title = title;
    }

    public Paragraph(int level, boolean fold, String title, List<Node> children) {
        super(Type.Paragraph, children);
        this.level = level;
        this.fold = fold;
        this.title = title;
    }

    public int getLevel() {
        return level;
    }

    public boolean isFold() {
        return fold;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Paragraph paragraph = (Paragraph) o;
        return level == paragraph.level && fold == paragraph.fold && Objects.equals(title, paragraph.title);
    }
}
