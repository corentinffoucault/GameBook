package com.reader.adventure.story.read.export.pdf.block;

import com.reader.adventure.story.read.export.pdf.block.JustifiedPartLine;
import com.reader.adventure.story.read.export.pdf.block.PdfLine;
import com.reader.adventure.story.read.export.pdf.block.PdfPartLine;
import com.reader.adventure.story.read.export.pdf.block.PdfWord;
import com.reader.adventure.story.read.export.pdf.font.Fonts;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PdfLineTests {
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
    void checkEmptyLine() {
        PdfLine pdfLine = new PdfLine();
        assertEquals(0, pdfLine.getParts().size());
        assertEquals(0, pdfLine.getSize());
        assertNull(pdfLine.getCurrentFont());
    }

    @Test
    void checkLineWithFirstWord() {
        PdfLine pdfLine = new PdfLine();
        pdfLine.addWord(word, Fonts.FONT_BODY);
        assertEquals(1, pdfLine.getParts().size());
        assertEquals(26.004, pdfLine.getSize(), 0.001);
        assertEquals(1, pdfLine.getParts().getFirst().getText().size());
        assertEquals(word.getWord(), pdfLine.getParts().getFirst().getText().getFirst());
        assertEquals(Fonts.FONT_BODY, pdfLine.getParts().getFirst().getFont());
    }

    @Test
    void checkStateAfterAddingAnOtherWordWithSameFont() {
        PdfLine pdfLine = new PdfLine();
        pdfLine.addWord(word, Fonts.FONT_BODY);
        pdfLine.addWord(hello_body, Fonts.FONT_BODY);
        assertEquals(1, pdfLine.getParts().size());
        assertEquals(54.68, pdfLine.getSize(), 0.01);
        assertEquals(2, pdfLine.getParts().getFirst().getText().size());
        assertEquals(word.getWord(), pdfLine.getParts().getFirst().getText().getFirst());
        assertEquals(hello_body.getWord(), pdfLine.getParts().getFirst().getText().getLast());
        assertEquals(Fonts.FONT_BODY, pdfLine.getParts().getFirst().getFont());
    }

    @Test
    void checkStateAfterAddingAnOtherWordWithOtherFont() {
        PdfLine pdfLine = new PdfLine();
        pdfLine.addWord(word, Fonts.FONT_BODY);
        pdfLine.addWord(hello_dir, Fonts.FONT_DIRECTION);
        assertEquals(2, pdfLine.getParts().size());
        assertEquals(54.01, pdfLine.getSize(), 0.01);

        PdfPartLine firstPart = pdfLine.getParts().getFirst();
        assertEquals(1, firstPart.getText().size());
        assertEquals(word.getWord(), firstPart.getText().getFirst());
        assertEquals(Fonts.FONT_BODY, firstPart.getFont());

        PdfPartLine secondPart = pdfLine.getParts().getLast();
        assertEquals(1, secondPart.getText().size());
        assertEquals(hello_dir.getWord(), secondPart.getText().getLast());
        assertEquals(Fonts.FONT_DIRECTION, secondPart.getFont());
    }

    @Test
    void checkGenerateJustifiedLineWithToPart() {
        PdfLine pdfLine = new PdfLine();
        pdfLine.addWord(word, Fonts.FONT_BODY);
        pdfLine.addWord(hello_dir, Fonts.FONT_DIRECTION);

        List<JustifiedPartLine> justifiedPartLines = pdfLine.generateJustifiedLine(65, 5);

        assertEquals(2, justifiedPartLines.size());

        JustifiedPartLine part = justifiedPartLines.getFirst();
        assertEquals(5, part.startingPosition());
        assertEquals(Fonts.FONT_BODY, part.font());
        assertEquals(3, part.objectToWrite().size());
        inspectJustifiedPart(part.objectToWrite(), 0, word.getWord(), -915);

        part = justifiedPartLines.getLast();
        assertEquals(42, part.startingPosition(), 0.1);
        assertEquals(Fonts.FONT_DIRECTION, part.font());
        assertEquals(3, part.objectToWrite().size());
        inspectJustifiedPart(part.objectToWrite(), 0, hello_dir.getWord(), -915);
    }

    @Test
    void checkGenerateJustifiedLineWitOnePart() {
        PdfLine pdfLine = new PdfLine();
        pdfLine.addWord(word, Fonts.FONT_BODY);
        pdfLine.addWord(hello_dir, Fonts.FONT_BODY);

        List<JustifiedPartLine> justifiedPartLines = pdfLine.generateJustifiedLine(65, 5);

        assertEquals(1, justifiedPartLines.size());

        JustifiedPartLine part = justifiedPartLines.getFirst();
        assertEquals(5, part.startingPosition());
        assertEquals(Fonts.FONT_BODY, part.font());
        assertEquals(6, part.objectToWrite().size());
        inspectJustifiedPart(part.objectToWrite(), 0, word.getWord(), -637);
        inspectJustifiedPart(part.objectToWrite(), 1, hello_dir.getWord(), -637);
    }

    void inspectJustifiedPart(List<Object> objectToWrite, int index, String word, float offSet) {
        int realIndex = index * 3;
        assertEquals(word, objectToWrite.get(realIndex));
        assertEquals(" ", objectToWrite.get(realIndex + 1));
        assertEquals(offSet, (Float) objectToWrite.get(realIndex + 2), 1);
    }
}

