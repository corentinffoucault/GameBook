package com.reader.adventure.story.export.pdf.blockBuilder;

import com.reader.adventure.story.export.pdf.font.FontDetail;
import com.reader.adventure.story.export.pdf.block.PdfBlock;
import com.reader.adventure.story.model.choice.DirectionChoice;
import com.reader.adventure.story.model.node.INode;

import java.io.IOException;

import static com.reader.adventure.story.export.pdf.font.Fonts.FONT_BODY;
import static com.reader.adventure.story.export.pdf.font.Fonts.FONT_DIRECTION;

public class BlockBuilder {

    public static void buildNodeBlock(PdfBlock block, INode node) throws IOException {
        for (String paragraph : node.getText().split("\n")) {
            wrapText(paragraph, FONT_BODY, block);
        }
    }

    public static void buildChoiceBlock(PdfBlock block, DirectionChoice dir) throws IOException {
        String choiceText = dir.text();
        String[] parts = choiceText.split("\n");
        for (int i = 0; i < parts.length - 1; i++) {
            wrapText(parts[i], FONT_BODY, block);
        }
        String lastPart = parts[parts.length - 1];
        wrapText(lastPart, FONT_BODY, block);
        wrapText(" Rendez vous en " + dir.nextNode() + ".", FONT_DIRECTION, block);
    }

    private static void wrapText(String text, FontDetail font, PdfBlock block) throws IOException {
        String trimed = text.trim();
        String[] words = trimed.split(" ");
        block.initSubBlock(words[0], font);
        for (int index = 1; index < words.length; index++) {
            block.addWord(words[index]);
        }
    }
}
