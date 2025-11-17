package com.reader.adventure.story.read.export.pdf;

import com.reader.adventure.story.read.export.pdf.block.*;
import com.reader.adventure.story.read.export.pdf.font.FontDetail;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;
import java.util.List;

public class PdfWriter
{
    private float y;
    private final float lineHeight;
    private PDPageContentStream cs;
    private static final float MARGIN = 50;
    private static final float PAGE_HEIGHT = PDRectangle.A4.getHeight();
    private static final float PAGE_WIDTH = PDRectangle.A4.getWidth();
    private static final float PAGE_WIDTH_WITH_MARGIN = PAGE_WIDTH - 2 * MARGIN;

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
        return PAGE_WIDTH_WITH_MARGIN;
    }

    public void close() throws IOException {
        cs.close();
    }

    public void goToLine() {
        this.y -= this.lineHeight / 2;
    }

    public void jumpLine() {
        goToLine();
        this.y -= this.lineHeight;
    }

    public void writeCentered(PDDocument doc, String text, FontDetail font) throws IOException {
        assertTextInPage(doc);
        float textSize = font.getWidthOfWord(text);
        addText((PAGE_WIDTH - textSize)/2, font, text);
        jumpLine();
    }

    public void writeParagraph(PDDocument doc, PdfBlock block) throws IOException {
        List<PdfParagraph> paragraphs = block.getParagraphs();
        for (PdfParagraph paragraph : paragraphs) {
            writeMultipleLine(doc, paragraph);
        }
    }

    public void writeMultipleLine(PDDocument doc, PdfParagraph paragraph) throws IOException {
        List<PdfLine> lines = paragraph.getLines();
        for (int index  = 0; index < lines.size() - 1; index++) {
            assertTextInPage(doc);
            writeLineJustified(lines.get(index));
            jumpLine();
        }
        assertTextInPage(doc);
        writeLine(lines.getLast());
        jumpLine();
    }

    public void writeLineJustified(PdfLine line) throws IOException {
        List<JustifiedPartLine> justifiedPartLines = line.generateJustifiedLine(PAGE_WIDTH_WITH_MARGIN, MARGIN);
        for (JustifiedPartLine justifiedPartLine : justifiedPartLines) {
            addTextJustified(justifiedPartLine.startingPosition(), justifiedPartLine.font(), justifiedPartLine.objectToWrite());
        }
    }

    public void writeLine(PdfLine line) throws IOException {
        float x = MARGIN;

        for (PdfPartLine part : line.getParts()) {
            addText(x, part.getFont(), String.join(" ", part.getText()) + " ");
            x += part.getSize() + part.getFont().getSpaceWidth();
        }
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

    public void addText(float startingX, FontDetail font, String text) throws IOException {
        cs.beginText();
        cs.setFont(font.getFont(), font.getSize());
        cs.newLineAtOffset(startingX, y);
        cs.showText(text);
        cs.endText();
    }

    public void addTextJustified(float startingX, FontDetail font, List<Object> text) throws IOException {
        cs.beginText();
        cs.setFont(font.getFont(), font.getSize());
        cs.newLineAtOffset(startingX, y);
        cs.showTextWithPositioning(text.toArray());
        cs.endText();
    }
}
