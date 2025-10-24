package com.reader.adventure.story.export.pdf;

import java.util.ArrayList;
import java.util.List;

public class PdfPartLine {

    private final List<String> text;
    private final FontDetail font;
    
    public PdfPartLine(String word, FontDetail font) {
        this.text = new ArrayList<>();
        this.text.add(word);
        this.font = font;
    }

    public List<String> getText() {
        return text;
    }

    public FontDetail getFont() {
        return font;
    }

    public void addWord(String word) {
        text.add(word);
    }
    
}
