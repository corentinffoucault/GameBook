package com.reader.adventure.story.export.pdf.block;

import com.reader.adventure.story.export.pdf.font.FontDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PdfPartLine {

    private final List<PdfWord> text;
    private final FontDetail font;
    private float size;

    public PdfPartLine(FontDetail font) {
        this.text = new ArrayList<>();
        this.font = font;
        this.size = 0;
    }

    public List<String> getText() {
        return text.stream().map(PdfWord::getWord).toList();
    }

    public FontDetail getFont() {
        return font;
    }

    public float getSize() {
        return size;
    }

    public void addWord(PdfWord word) {
        if (!text.isEmpty()) {
            size += font.getSpaceWidth();
        }
        text.add(word);
        size += word.getWidth();
    }

    public List<Object> generateJustifiedDetail(float expectedSpaceSize) {
        float offSet = (expectedSpaceSize - font.getSpaceWidth()) / font.getSize() * 1000f;
        return text.stream()
                .flatMap(t -> Stream.<Object>of(t.getWord(), " ", -offSet))
                .toList();
    }
}
