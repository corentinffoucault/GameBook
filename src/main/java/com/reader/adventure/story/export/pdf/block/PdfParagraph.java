package com.reader.adventure.story.export.pdf.block;

import com.reader.adventure.story.export.pdf.font.FontDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfParagraph {
    private final List<PdfLine> lines;
    private final float maxWidth;

    public PdfParagraph(float maxWidth) {
        this.lines = new ArrayList<>();
        this.maxWidth = maxWidth;
    }

    public List<PdfLine> getLines() {
        return lines;
    }

    public void addWord(PdfWord word, FontDetail font) throws IOException {
        if (lines.isEmpty()) {
            lines.add(new PdfLine());
        }
        PdfLine currentLine = lines.getLast();
        if ((currentLine.getSize() + font.getSpaceWidth() + word.getWidth()) > maxWidth) {
            lines.add(new PdfLine());
            currentLine = lines.getLast();
        }
        currentLine.addWord(word, font);
    }
}
