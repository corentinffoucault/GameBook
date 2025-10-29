package com.reader.adventure.story.export.pdf.block;

import com.reader.adventure.story.export.pdf.font.FontDetail;

import java.io.IOException;

public class PdfWord {

    private final String word;
    private final float width;

    public float getWidth() { return width; }
    public String getWord() { return word; }

    public PdfWord(String word, FontDetail font) throws IOException {
        this.word = word;
        this.width = font.getWidthOfWord(word);
    }
}
