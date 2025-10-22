package com.reader.adventure.story.export.pdf;

import org.apache.pdfbox.pdmodel.font.PDFont;

import java.io.IOException;


public class FontDetail {

    private final PDFont font;
    private final float size;
    private final float spaceWidth;

    private static final int THOUSANDS = 1000;

    FontDetail(PDFont font, float size) {
        this.font = font;
        this.size = size;
        float tmpSpaceWidth;
        try {
            tmpSpaceWidth = font.getStringWidth(" ") / THOUSANDS * size;
        } catch (IOException e) {
            tmpSpaceWidth = size * 0.25f;
        }
        this.spaceWidth = tmpSpaceWidth;
    }

    public float getSize() {
        return size;
    }

    public PDFont getFont() {
        return font;
    }

    public float getSpaceWidth() {
        return spaceWidth;
    }

    public float getSpaceWidthForWord(String word) throws IOException {
        return font.getStringWidth(word) / THOUSANDS * size;
    }
}
