package com.reader.adventure.story.export.pdf.block;

import com.reader.adventure.story.export.pdf.font.FontDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PdfPartLine {

    private final List<PdfWord> text;
    private final FontDetail font;

    public PdfPartLine(FontDetail font) {
        this.text = new ArrayList<>();
        this.font = font;
    }

    public List<String> getText() {
        return text.stream().map(PdfWord::getWord).toList();
    }

    public FontDetail getFont() {
        return font;
    }

    public float getSize() {
        return (float) text.stream()
                .mapToDouble(PdfWord::getWidth)
                .sum() + (text.size()-1)*font.getSpaceWidth();
    }

    public void addWord(PdfWord word) {
        text.add(word);
    }

    public List<Object> generateJustifiedDetail(float expectedSpaceSize) {
        float offSet = (expectedSpaceSize - font.getSpaceWidth()) / font.getSize() * 1000f;
        return text.stream()
                .flatMap(t -> Stream.<Object>of(t.getWord(), " ", -offSet))
                .toList();
    }
}
