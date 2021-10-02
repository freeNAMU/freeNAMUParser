package com.github.freenamu.parser.node.single;

import com.github.freenamu.parser.node.Node;

public class Text extends Node {
    private final String text;

    public Text(String text) {
        super(Type.Text);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return text.equals(((Text) o).text);
    }
}