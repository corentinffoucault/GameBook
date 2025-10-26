package com.reader.adventure.story.export.pdf;

import com.reader.adventure.story.export.pdf.block.PdfBlock;
import com.reader.adventure.story.export.pdf.block.PdfLine;
import com.reader.adventure.story.export.pdf.block.PdfPartLine;
import com.reader.adventure.story.export.pdf.font.FontDetail;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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

    public void writeMultipleLines(PDDocument doc, PdfBlock block) throws IOException {
        List<PdfLine> lines = block.getLines();
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
        float spaceToComplete = PAGE_WIDTH_WITH_MARGIN - line.getSize();
        float offsetForPDF;

        float currentFontSpaceSize = line.getCurrentFont().getSpaceWidth();
        if (spaceToComplete < 100) {
            offsetForPDF = currentFontSpaceSize + spaceToComplete / (line.getNbWord() - 1);
        } else {
            offsetForPDF = currentFontSpaceSize;
        }
        float x = MARGIN;

        PdfPartLine firstPart = line.getParts().getFirst();
        List<Object> text = firstPart.generateJustifiedDetail(offsetForPDF);
        addTextJustified(x, firstPart.getFont(), text);

        x += firstPart.getSize() + calculateFullOffset(text, firstPart.getFont());

        for (int index  = 1; index < line.getParts().size(); index++) {
            PdfPartLine part = line.getParts().get(index);
            List<Object> tmpText = part.generateJustifiedDetail(offsetForPDF);
            addTextJustified(x, part.getFont(), tmpText);
            x += part.getSize() + calculateFullOffset(tmpText, part.getFont());
        }
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

    public void writeLine(PdfLine line) throws IOException {
        float x = MARGIN;

        PdfPartLine part = line.getParts().getFirst();
        addText(x, part.getFont(), String.join(" ", part.getText()));
        x += part.getSize();

        for (int index  = 1; index < line.getParts().size(); index++) {
            part = line.getParts().get(index);
            addText(x, part.getFont(), " " + String.join(" ", part.getText()));
            x += part.getSize();
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
