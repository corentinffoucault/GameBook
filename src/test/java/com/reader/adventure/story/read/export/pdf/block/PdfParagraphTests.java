package com.reader.adventure.story.read.export.pdf.block;

import com.reader.adventure.story.read.export.pdf.font.Fonts;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PdfParagraphTests {
    static PdfWord hello_body;
    static PdfWord hello_dir;
    static PdfWord word;

    @BeforeAll
    static void init() throws IOException {
        hello_body = new PdfWord("hello", Fonts.FONT_BODY);
        hello_dir = new PdfWord("hello", Fonts.FONT_DIRECTION);
        word = new PdfWord("word", Fonts.FONT_BODY);
    }

    @Test
    void assertInitializeBlock() {
        PdfParagraph pdfParagraph = new PdfParagraph(100);
        assertEquals(0, pdfParagraph.getLines().size());
    }

    @Test
    void assertOneLineAfterAddingFirstWord() {
        assertDoesNotThrow(() -> {
            PdfParagraph pdfParagraph = new PdfParagraph(100);
            pdfParagraph.addWord(word, Fonts.FONT_BODY);
            assertEquals(1, pdfParagraph.getLines().size());

            PdfLine line = pdfParagraph.getLines().getFirst();
            assertEquals(1, line.getParts().size());
            assertEquals(26.004, line.getSize(), 0.001);
            assertEquals(1, line.getParts().getFirst().getText().size());
            assertEquals(word.getWord(), line.getParts().getFirst().getText().getFirst());
            assertEquals(Fonts.FONT_BODY, line.getParts().getFirst().getFont());
        });
    }

    @Test
    void assertOneLineAfterAddingTwoWords() {
        assertDoesNotThrow(() -> {
            PdfParagraph pdfParagraph = new PdfParagraph(100);
            pdfParagraph.addWord(word, Fonts.FONT_BODY);
            pdfParagraph.addWord(hello_body, Fonts.FONT_BODY);
            assertEquals(1, pdfParagraph.getLines().size());

            PdfLine line = pdfParagraph.getLines().getFirst();
            assertEquals(1, line.getParts().size());
            assertEquals(54.684, line.getSize(), 0.001);
            assertEquals(2, line.getParts().getFirst().getText().size());
            assertEquals(word.getWord(), line.getParts().getFirst().getText().getFirst());
            assertEquals(hello_body.getWord(), line.getParts().getFirst().getText().getLast());
            assertEquals(Fonts.FONT_BODY, line.getParts().getFirst().getFont());
        });
    }

    @Test
    void assertAddingOneWordAndLineTooShort() {
        assertDoesNotThrow(() -> {
            PdfParagraph pdfParagraph = new PdfParagraph(50);
            pdfParagraph.addWord(word, Fonts.FONT_BODY);
            pdfParagraph.addWord(hello_body, Fonts.FONT_BODY);
            assertEquals(2, pdfParagraph.getLines().size());

            PdfLine firstLine = pdfParagraph.getLines().getFirst();
            assertEquals(1, firstLine.getParts().size());
            assertEquals(26.004, firstLine.getSize(), 0.001);
            assertEquals(1, firstLine.getParts().getFirst().getText().size());
            assertEquals(word.getWord(), firstLine.getParts().getFirst().getText().getFirst());
            assertEquals(Fonts.FONT_BODY, firstLine.getParts().getFirst().getFont());

            PdfLine secondLine = pdfParagraph.getLines().getLast();
            assertEquals(1, secondLine.getParts().size());
            assertEquals(25.343, secondLine.getSize(), 0.001);
            assertEquals(1, secondLine.getParts().getFirst().getText().size());
            assertEquals(hello_body.getWord(), secondLine.getParts().getFirst().getText().getFirst());
            assertEquals(Fonts.FONT_BODY, secondLine.getParts().getFirst().getFont());
        });
    }
}
