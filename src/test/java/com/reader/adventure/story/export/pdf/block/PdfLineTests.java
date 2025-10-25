package com.reader.adventure.story.export.pdf.block;

import com.reader.adventure.story.export.pdf.font.Fonts;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PdfLineTests {
    @Test
    void checkEmptyLine() {
        assertDoesNotThrow(() -> {
            PdfLine pdfLine = new PdfLine("", Fonts.FONT_BODY);
            assertEquals(1, pdfLine.getParts().size());
            assertEquals(0, pdfLine.getSize());
            assertEquals(Fonts.FONT_BODY, pdfLine.getParts().getFirst().getFont());
        });
    }

    @Test
    void checkLineWithFirstWord() {
        assertDoesNotThrow(() -> {
            PdfLine pdfLine = new PdfLine("word", Fonts.FONT_BODY);
            assertEquals(1, pdfLine.getParts().size());
            assertEquals(26.004, pdfLine.getSize(), 0.001);
            assertEquals(1, pdfLine.getParts().getFirst().getText().size());
            assertEquals("word", pdfLine.getParts().getFirst().getText().getFirst());
            assertEquals(Fonts.FONT_BODY, pdfLine.getParts().getFirst().getFont());
        });
    }

    @Test
    void checkStateAfterAddingAnOtherWordWithSameFont() {
        assertDoesNotThrow(() -> {
            PdfLine pdfLine = new PdfLine("word", Fonts.FONT_BODY);
            pdfLine.addWord("hello");
            assertEquals(1, pdfLine.getParts().size());
            assertEquals(54.684, pdfLine.getSize(), 0.001);
            assertEquals(3, pdfLine.getParts().getFirst().getText().size());
            assertEquals("word", pdfLine.getParts().getFirst().getText().getFirst());
            assertEquals(" ", pdfLine.getParts().getFirst().getText().get(1));
            assertEquals("hello", pdfLine.getParts().getFirst().getText().getLast());
            assertEquals(Fonts.FONT_BODY, pdfLine.getParts().getFirst().getFont());
        });
    }
}

