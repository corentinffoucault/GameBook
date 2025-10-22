package com.reader.adventure.story.export.pdf;

import java.io.IOException;

public class PdfPartLine {

    private final StringBuilder text;
    private final FontDetail font;
    
    public PdfPartLine(String word, FontDetail font) throws IOException {
        this.text = new StringBuilder(word);
        this.font = font;
    }

    public StringBuilder getText() {
        return text;
    }

    public FontDetail getFont() {
        return font;
    }

    public void addWord(String word) throws IOException {
        text.append(word);
    }
    
}
