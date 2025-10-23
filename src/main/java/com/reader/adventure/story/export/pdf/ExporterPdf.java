package com.reader.adventure.story.export.pdf;

import com.reader.adventure.story.export.IExporter;
import com.reader.adventure.story.model.choice.DirectionChoice;
import com.reader.adventure.story.model.choice.IChoice;
import com.reader.adventure.story.model.node.INode;
import com.reader.adventure.story.model.story.IStory;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts.*;
import org.apache.pdfbox.pdmodel.font.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class ExporterPdf implements IExporter {

    private static final float FONT_SIZE_TITLE = 16;
    private static final float FONT_SIZE_BODY = 12;
    private static final FontDetail fontTitle = new FontDetail(new PDType1Font(FontName.HELVETICA_BOLD), FONT_SIZE_TITLE);
    private static final FontDetail fontBody = new FontDetail(new PDType1Font(FontName.HELVETICA), FONT_SIZE_BODY);
    private static final FontDetail fontDirection = new FontDetail(new PDType1Font(FontName.HELVETICA_BOLD), FONT_SIZE_BODY);

    private static final float LINE_HEIGHT = 14;

    public void print(IStory story, Path exportPath) throws IOException {
        try (PDDocument doc = new PDDocument()) {
            PdfWriter pdfWriter = new PdfWriter(LINE_HEIGHT);
            pdfWriter.init(doc);

            INode firstNode = story.getNodes().get(story.getFirstNode());
            printNode(doc, pdfWriter, firstNode);
            pdfWriter.jumpLine();

            for (Map.Entry<String, INode> entry : story.getNodes().entrySet()) {
                INode node = entry.getValue();
                if (!node.equals(firstNode)) {
                    printNode(doc, pdfWriter, node);
                    pdfWriter.jumpLine();
                }
            }

            pdfWriter.getCs().close();
            doc.save(exportPath.toFile());
        }
    }

    private void printNode(PDDocument doc, PdfWriter pdfWriter, INode node) throws IOException {
        PdfBlock block = new PdfBlock(pdfWriter.getPageWidth());
        wrapText( node.getId(), fontTitle, block);
        for (String paragraph : node.getText().split("\n")) {
            wrapText(paragraph, fontBody, block);
        }
        pdfWriter.writeMultipleLines(doc, block);
        pdfWriter.jumpLine();
        for (IChoice choice : node.getChoice()) {
            printChoice(doc, pdfWriter, choice);
        }
    }

    private void printChoice(PDDocument doc, PdfWriter pdfWriter, IChoice choice) throws IOException {
        for (DirectionChoice dir : choice.getAllDirection()) {
            PdfBlock block = new PdfBlock(pdfWriter.getPageWidth());
            String choiceText = dir.text();
            String[] parts = choiceText.split("\n");
            for (int i = 0; i < parts.length - 1; i++) {
                wrapText(parts[i], fontBody, block);
            }
            String lastPart = parts[parts.length - 1];
            wrapText(lastPart, fontBody, block);
            wrapText(" Rendez vous en " + dir.nextNode() + ".", fontDirection, block);
            pdfWriter.writeMultipleLines(doc, block);
        }
    }

    private void wrapText(String text, FontDetail font, PdfBlock block) throws IOException {
        String trimed = text.trim();
        String[] words = trimed.split(" ");
        block.initSubBlock(words[0], font);
        for (int index = 1; index < words.length; index++) {
            block.addWord(words[index]);
        }
    }
}
