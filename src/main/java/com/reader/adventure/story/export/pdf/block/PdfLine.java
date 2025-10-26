package com.reader.adventure.story.export.pdf.block;

import com.reader.adventure.story.export.pdf.font.FontDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfLine {
    private final List<PdfPartLine> parts;
    private FontDetail lastUsedFont;

    public PdfLine(String firstWord, FontDetail font) throws IOException {
        this.parts = new ArrayList<>();
        this.parts.add(new PdfPartLine(firstWord, font));
        this.lastUsedFont = font;
    }

    public List<PdfPartLine> getParts() {
        return parts;
    }

    public FontDetail getCurrentFont() { return lastUsedFont; }


    public int getNbWord() {
        return parts.stream().mapToInt(part -> part.getText().size()).sum();
    }

    public void addWord(String word, FontDetail font) throws IOException {
        if (lastUsedFont != font) {
            lastUsedFont = font;
            parts.add(new PdfPartLine(word, font));
        } else {
            parts.getLast().addWord(word);
        }
    }

    public float getSize() {
        return (float) parts.stream()
                .mapToDouble(PdfPartLine::getSize)
                .sum();
    }

}

