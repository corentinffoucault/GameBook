package com.reader.adventure.story.export.pdf.block;

import com.reader.adventure.story.export.pdf.font.FontDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfBlock {
    private final List<PdfLine> lines;
    private final float maxWidth;

    public PdfBlock(float maxWidth) {
        this.lines = new ArrayList<>();
        this.maxWidth = maxWidth;
    }

    public List<PdfLine> getLines() {
        return lines;
    }

    public void initSubBlock(String firstWord, FontDetail font) throws IOException {
        this.lines.add(new PdfLine(firstWord, font));
    }

    public void addWord(String word, FontDetail font) throws IOException {
        PdfLine currentLine = lines.getLast();
        float wordWith = font.getWidthOfWord(word);
        if ((currentLine.getSize() + font.getSpaceWidth() + wordWith) > maxWidth) {
            lines.add(new PdfLine(word, font));
        } else {
            currentLine.addWord(word, font);
        }
    }
}
