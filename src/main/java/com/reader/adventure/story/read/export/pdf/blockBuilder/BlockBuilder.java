package com.reader.adventure.story.read.export.pdf.blockBuilder;

import com.reader.adventure.story.read.export.pdf.block.PdfBlock;
import com.reader.adventure.story.read.export.pdf.block.PdfWord;
import com.reader.adventure.story.read.model.choice.DirectionChoice;
import com.reader.adventure.story.read.model.node.INode;

import java.io.IOException;

import static com.reader.adventure.story.read.export.pdf.font.Fonts.FONT_BODY;
import static com.reader.adventure.story.read.export.pdf.font.Fonts.FONT_DIRECTION;

public class BlockBuilder {

    public static void buildNodeBlock(PdfBlock block, INode node) throws IOException {
        for (String paragraph : node.getText().split("\n")) {
            wrapText(paragraph, block);
        }
    }

    public static void buildChoiceBlock(PdfBlock block, DirectionChoice dir) throws IOException {
        String choiceText = dir.text();
        String[] parts = choiceText.split("\n");
        for (int i = 0; i < parts.length - 1; i++) {
            wrapText(parts[i], block);
        }
        String lastPart = parts[parts.length - 1];
        wrapText(lastPart, block);
        addDirection(dir.nextNode(), block);
    }

    private static void wrapText(String text, PdfBlock block) throws IOException {
        block.createParagraph();
        String[] words = text.trim().split(" ");
        for (String word : words) {
            PdfWord pdfWord = new PdfWord(word, FONT_BODY);
            block.addWord(pdfWord, FONT_BODY);
        }
    }

    private static void addDirection(String nextNode, PdfBlock block) throws IOException {
        String[] words = ("Rendez vous en " + nextNode + ".").split(" ");
        for (String word : words) {
            PdfWord pdfWord = new PdfWord(word, FONT_DIRECTION);
            block.addWord(pdfWord, FONT_DIRECTION);
        }
    }
}
