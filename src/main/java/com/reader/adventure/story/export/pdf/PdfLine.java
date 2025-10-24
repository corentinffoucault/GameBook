package com.reader.adventure.story.export.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfLine {
    private final List<PdfPartLine> parts;
    private final FontDetail lastUsedFont;
    private float size;

    public PdfLine(String firstWord, FontDetail font) throws IOException {
        this.parts = new ArrayList<>();
        this.parts.add(new PdfPartLine(firstWord, font));
        this.lastUsedFont = font;
        this.size = font.getWidthOfWord(firstWord);
    }

    public List<PdfPartLine> getParts() {
        return parts;
    }

    public void addWord(String word) throws IOException {
        parts.getLast().addWord(" ");
        size += lastUsedFont.getSpaceWidth();
        parts.getLast().addWord(word);
        size += lastUsedFont.getWidthOfWord(word);
    }

    public FontDetail getCurrentFont() {
        return lastUsedFont;
    }

    public float getSize() {
        return size;
    }
}

