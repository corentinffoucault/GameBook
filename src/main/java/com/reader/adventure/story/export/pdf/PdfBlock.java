package com.reader.adventure.story.export.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfBlock {
    private final List<PdfLine> lines;
    private final float maxWidth;

    public PdfBlock(String firstWord, FontDetail font, float maxWidth) throws IOException {
        this.lines = new ArrayList<>();
        this.lines.add(new PdfLine(firstWord, font));
        this.maxWidth = maxWidth;
    }

    public List<PdfLine> getLines() {
        return lines;
    }

    public void addWord(String word) throws IOException {
        PdfLine currentLine = lines.getLast();
        FontDetail font = currentLine.getCurrentFont();
        float wordWith = font.getSpaceWidthForWord(word);
        if ((currentLine.getSize() + font.getSpaceWidth() + wordWith) > maxWidth) {
            lines.add(new PdfLine(word, font));
        } else {
            currentLine.addWord(word);
        }
    }
}
