package com.reader.adventure.story.export.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;

public class PdfWriter
{
    private float y;
    private final float lineHeight;
    private PDPageContentStream cs;
    private static final float MARGIN = 50;
    private static final float PAGE_HEIGHT = PDRectangle.A4.getHeight();
    private static final float PAGE_WIDTH = PDRectangle.A4.getWidth();

    public PdfWriter(float lineHeight) {
        this.y = PAGE_HEIGHT - MARGIN;
        this.lineHeight = lineHeight;
    }

    public void init(PDDocument doc) throws IOException {
        PDPage page = new PDPage(PDRectangle.A4);
        doc.addPage(page);
        cs = new PDPageContentStream(doc, page);
    }

    public float getPageWidth() {
        return PAGE_WIDTH - 2 * MARGIN;
    }

    public PDPageContentStream getCs() {
        return cs;
    }

    public void goToLine() {
        this.y -= this.lineHeight / 2;
    }

    public void jumpLine() {
        goToLine();
        this.y -= this.lineHeight;
    }

    public void assertTextInPage(PDDocument doc) throws IOException {
        if (y < MARGIN + this.lineHeight) {
            cs.close();
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);
            cs = new PDPageContentStream(doc, page);
            y = PAGE_HEIGHT - MARGIN;
        }
    }

    public void addText(FontDetail font, String text) throws IOException {
        cs.beginText();
        cs.setFont(font.font(), font.size());
        cs.newLineAtOffset(MARGIN, y);
        cs.showText(text);
        cs.endText();
    }
}
