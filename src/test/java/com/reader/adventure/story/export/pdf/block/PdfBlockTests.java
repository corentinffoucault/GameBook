package com.reader.adventure.story.export.pdf.block;

import com.reader.adventure.story.export.pdf.font.Fonts;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.Line;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class PdfBlockTests {
    @Test
    void assertInitializeBlock() {
        PdfBlock pdfBlock = new PdfBlock(100);
        assertEquals(0, pdfBlock.getLines().size());
    }

    @Test
    void assertThrowIfAddingWordWhenNoLine() {
        // TODO: this throw would be remove whend font can change in line
        assertThrows(NoSuchElementException.class, () -> {
            PdfBlock pdfBlock = new PdfBlock(100);
            pdfBlock.addWord("word");
        });
    }

    @Test
    void assertOneLineAfterAddingFirstWord() {
        assertDoesNotThrow(() -> {
            PdfBlock pdfBlock = new PdfBlock(100);
            pdfBlock.initSubBlock("word", Fonts.FONT_BODY);
            assertEquals(1, pdfBlock.getLines().size());

            PdfLine line = pdfBlock.getLines().getFirst();
            assertEquals(1, line.getParts().size());
            assertEquals(26.004, line.getSize(), 0.001);
            assertEquals(1, line.getParts().getFirst().getText().size());
            assertEquals("word", line.getParts().getFirst().getText().getFirst());
            assertEquals(Fonts.FONT_BODY, line.getParts().getFirst().getFont());
        });
    }

    @Test
    void assertOneLineAfterAddingTwoWords() {
        assertDoesNotThrow(() -> {
            PdfBlock pdfBlock = new PdfBlock(100);
            pdfBlock.initSubBlock("word", Fonts.FONT_BODY);
            pdfBlock.addWord("hello");
            assertEquals(1, pdfBlock.getLines().size());

            PdfLine line = pdfBlock.getLines().getFirst();
            assertEquals(1, line.getParts().size());
            assertEquals(54.684, line.getSize(), 0.001);
            assertEquals(3, line.getParts().getFirst().getText().size());
            assertEquals("word", line.getParts().getFirst().getText().getFirst());
            assertEquals(" ", line.getParts().getFirst().getText().get(1));
            assertEquals("hello", line.getParts().getFirst().getText().getLast());
            assertEquals(Fonts.FONT_BODY, line.getParts().getFirst().getFont());
        });
    }

    @Test
    void assertAddingOneWordAndLineTooShort() {
        assertDoesNotThrow(() -> {
            PdfBlock pdfBlock = new PdfBlock(50);
            pdfBlock.initSubBlock("word", Fonts.FONT_BODY);
            pdfBlock.addWord("hello");
            assertEquals(2, pdfBlock.getLines().size());

            PdfLine firstLine = pdfBlock.getLines().getFirst();
            assertEquals(1, firstLine.getParts().size());
            assertEquals(26.004, firstLine.getSize(), 0.001);
            assertEquals(1, firstLine.getParts().getFirst().getText().size());
            assertEquals("word", firstLine.getParts().getFirst().getText().getFirst());
            assertEquals(Fonts.FONT_BODY, firstLine.getParts().getFirst().getFont());

            PdfLine secondLine = pdfBlock.getLines().getLast();
            assertEquals(1, secondLine.getParts().size());
            assertEquals(25.343, secondLine.getSize(), 0.001);
            assertEquals(1, secondLine.getParts().getFirst().getText().size());
            assertEquals("hello", secondLine.getParts().getFirst().getText().getFirst());
            assertEquals(Fonts.FONT_BODY, secondLine.getParts().getFirst().getFont());
        });
    }

    @Test
    void assertAddingNewLineAfterNewInit() {
        assertDoesNotThrow(() -> {
            PdfBlock pdfBlock = new PdfBlock(50);
            pdfBlock.initSubBlock("word", Fonts.FONT_BODY);
            pdfBlock.initSubBlock("hello", Fonts.FONT_BODY);
            assertEquals(2, pdfBlock.getLines().size());

            PdfLine firstLine = pdfBlock.getLines().getFirst();
            assertEquals(1, firstLine.getParts().size());
            assertEquals(26.004, firstLine.getSize(), 0.001);
            assertEquals(1, firstLine.getParts().getFirst().getText().size());
            assertEquals("word", firstLine.getParts().getFirst().getText().getFirst());
            assertEquals(Fonts.FONT_BODY, firstLine.getParts().getFirst().getFont());

            PdfLine secondLine = pdfBlock.getLines().getLast();
            assertEquals(1, secondLine.getParts().size());
            assertEquals(25.343, secondLine.getSize(), 0.001);
            assertEquals(1, secondLine.getParts().getFirst().getText().size());
            assertEquals("hello", secondLine.getParts().getFirst().getText().getFirst());
            assertEquals(Fonts.FONT_BODY, secondLine.getParts().getFirst().getFont());
        });
    }
}
