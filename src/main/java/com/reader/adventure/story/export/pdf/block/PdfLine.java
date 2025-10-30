package com.reader.adventure.story.export.pdf.block;

import com.reader.adventure.story.export.pdf.font.FontDetail;

import java.util.ArrayList;
import java.util.List;

public class PdfLine {
    private final List<PdfPartLine> parts;
    private FontDetail lastUsedFont;

    public PdfLine() {
        this.parts = new ArrayList<>();
    }

    public List<PdfPartLine> getParts() {
        return parts;
    }

    public FontDetail getCurrentFont() { return lastUsedFont; }

    public int getNbWord() {
        return parts.stream().mapToInt(part -> part.getText().size()).sum();
    }

    public void addWord(PdfWord word, FontDetail font) {
        if (lastUsedFont != font || parts.isEmpty()) {
            lastUsedFont = font;
            parts.add(new PdfPartLine(font));
        }
        parts.getLast().addWord(word);
    }

    public float getSize() {
        return (float) parts.stream()
                .mapToDouble(PdfPartLine::getSize)
                .sum();
    }

}

