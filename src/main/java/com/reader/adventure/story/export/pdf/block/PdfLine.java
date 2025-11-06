package com.reader.adventure.story.export.pdf.block;

import com.reader.adventure.story.export.pdf.font.FontDetail;

import java.util.ArrayList;
import java.util.List;

public class PdfLine {
    private final List<PdfPartLine> parts;
    private FontDetail lastUsedFont;

    public PdfLine() {
        this.parts = new ArrayList<>();
    }

    public List<PdfPartLine> getParts() { return parts; }

    public FontDetail getCurrentFont() { return lastUsedFont; }

    public int getNbWord() {
        return parts.stream().mapToInt(part -> part.getText().size()).sum();
    }

    public void addWord(PdfWord word, FontDetail font) {
        if (lastUsedFont != font || parts.isEmpty()) {
            lastUsedFont = font;
            parts.add(new PdfPartLine(font));
        }
        parts.getLast().addWord(word);
    }

    public float getSize() {
        return (float) parts.stream()
                .mapToDouble(PdfPartLine::getSize)
                .sum();
    }

    public List<JustifiedPartLine> generateJustifiedLine(float maxsize, float startingX) {
        float spaceToComplete = maxsize - getSize();

        float realSpaceSize = spaceToComplete / (getNbWord() - 1);
        float x = startingX;

        List<JustifiedPartLine> justifiedPartLines = new ArrayList<>();
        for (PdfPartLine part: getParts()) {
            List<Object> tmpText = part.generateJustifiedDetail(realSpaceSize);
            justifiedPartLines.add(new JustifiedPartLine(x, part.getFont(), tmpText));
            x += part.getSize() + calculateFullOffset(tmpText, part.getFont());
        }
        return justifiedPartLines;
    }

    public float calculateFullOffset(List<Object> text, FontDetail font) {
        float totalOffset = 0;
        float ratio =  font.getSize() / 1000f;
        for (Object o : text) {
            if (o instanceof Number) {
                totalOffset += -((Number) o).floatValue() * ratio;
            }
        }
        return totalOffset;
    }
}

