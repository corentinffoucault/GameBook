package com.reader.adventure.story.export.pdf.block;

import com.reader.adventure.story.export.pdf.font.FontDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PdfPartLine {

    private final List<String> text;
    private final FontDetail font;
    private float size;

    public PdfPartLine(String word, FontDetail font) throws IOException {
        this.text = new ArrayList<>();
        this.text.add(word);
        size = font.getWidthOfWord(word);
        this.font = font;
    }

    public List<String> getText() {
        return text;
    }

    public FontDetail getFont() {
        return font;
    }

    public float getSize() {
        return size;
    }

    public void addWord(String word) throws IOException {
        text.add(word);
        size += font.getWidthOfWord(word);
        size += font.getSpaceWidth();
    }

    public List<Object> generateJustifiedDetail(float expectedSpaceSize) {
        float offSet = (expectedSpaceSize - font.getSpaceWidth()) / font.getSize() * 1000f;
        return text.stream()
                .flatMap(t -> Stream.<Object>of(t, " ", -offSet))
                .toList();
    }
}
