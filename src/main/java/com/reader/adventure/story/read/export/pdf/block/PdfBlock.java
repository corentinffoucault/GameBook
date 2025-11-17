package com.reader.adventure.story.read.export.pdf.block;

import com.reader.adventure.story.read.export.pdf.font.FontDetail;

import java.util.ArrayList;
import java.util.List;

public class PdfBlock {
    private final List<PdfParagraph> paragraphs;
    private final float pageWidth;

    public PdfBlock(float pageWidth) {
        this.paragraphs = new ArrayList<>();
        this.pageWidth = pageWidth;
    }

    public List<PdfParagraph> getParagraphs() {
        return paragraphs;
    }

    public void createParagraph() {
        paragraphs.add(new PdfParagraph(pageWidth));
    }

    public void addWord(PdfWord word, FontDetail font) {
        if (paragraphs.isEmpty()) {
            paragraphs.add(new PdfParagraph(pageWidth));
        }
        paragraphs.getLast().addWord(word, font);
    }
}
