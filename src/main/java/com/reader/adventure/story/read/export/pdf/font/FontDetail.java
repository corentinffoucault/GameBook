package com.reader.adventure.story.read.export.pdf.font;

import org.apache.pdfbox.pdmodel.font.PDFont;

import java.io.IOException;

public class FontDetail {

    private final PDFont font;
    private final float size;
    private final float spaceWidth;
    private final float spaceWidthInPoint;

    private static final int THOUSANDS = 1000;
    private static final int TWO_HUNDRED_FIFTY = 250;

    public FontDetail(PDFont font, float size) {
        this.font = font;
        this.size = size;
        float tmpSpaceWidthInPoint = 0;
        try {
            tmpSpaceWidthInPoint = font.getStringWidth(" ") * size;
            if (tmpSpaceWidthInPoint < 0) {
                tmpSpaceWidthInPoint = size * TWO_HUNDRED_FIFTY;
            }

        } catch (IOException _) {
            tmpSpaceWidthInPoint = size * TWO_HUNDRED_FIFTY;
        }
        this.spaceWidthInPoint = tmpSpaceWidthInPoint;
        this.spaceWidth = tmpSpaceWidthInPoint / THOUSANDS;
    }

    public float getSize() {
        return size;
    }

    public PDFont getFont() {
        return font;
    }

    public float getSpaceWidth() { return spaceWidth; }

    public float getSpaceWidthInPoint() { return spaceWidthInPoint; }

    public float getWidthOfWord(String word) throws IOException {
        return font.getStringWidth(word) / THOUSANDS * size;
    }
}
