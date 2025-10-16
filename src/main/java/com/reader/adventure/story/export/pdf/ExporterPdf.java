package com.reader.adventure.story.export.pdf;

import com.reader.adventure.story.export.IExporter;
import com.reader.adventure.story.model.choice.DirectionChoice;
import com.reader.adventure.story.model.choice.IChoice;
import com.reader.adventure.story.model.node.INode;
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

    public void print(Map<String, INode> nodes, Path exportPath) throws IOException {
        try (PDDocument doc = new PDDocument()) {
            PdfWriter pdfWriter = new PdfWriter(LINE_HEIGHT);
            pdfWriter.init(doc);

            for (Map.Entry<String, INode> entry : nodes.entrySet()) {
                INode node = entry.getValue();
                printNode(doc, pdfWriter, node);
                pdfWriter.jumpLine();
            }

            pdfWriter.getCs().close();
            doc.save(exportPath.toFile());
        }
    }

    private void printNode(PDDocument doc, PdfWriter pdfWriter, INode node) throws IOException {
        writeTextWithWrap(doc, pdfWriter, node.getId(), fontTitle);
        pdfWriter.jumpLine();
        for (String paragraph : node.getText().split("\n")) {
            writeTextWithWrap(doc, pdfWriter, paragraph, fontBody);
            pdfWriter.jumpLine();
        }
        pdfWriter.jumpLine();
        for (IChoice choice : node.getChoice()) {
            printChoice(doc, pdfWriter, choice);
        }
    }

    private void printChoice(PDDocument doc, PdfWriter pdfWriter, IChoice choice) throws IOException {
        for (DirectionChoice dir : choice.getAllDirection()) {
            String choiceText = dir.text();
            String[] parts = choiceText.split("\n");
            for (int i = 0; i < parts.length - 1; i++) {
                writeTextWithWrap(doc, pdfWriter, parts[i], fontBody);
                pdfWriter.jumpLine();
            }
            writeTextWithWrap(doc, pdfWriter, parts[parts.length - 1], fontBody);
            pdfWriter.jumpLine();
            writeTextWithWrap(doc, pdfWriter, " Rendez vous en " + dir.nextNode() + ".", fontDirection);
            pdfWriter.jumpLine();
        }
    }

    private void writeTextWithWrap(PDDocument doc,
                                   PdfWriter pdfWriter,
                                   String text,
                                   FontDetail font) throws IOException {
        float maxWidth = pdfWriter.getPageWidth();
        List<String> lines = wrapText(text, font, maxWidth);
        for (int i = 0; i < lines.size() - 1; i++) {
            pdfWriter.assertTextInPage(doc);
            pdfWriter.addText(font, lines.get(i));
            pdfWriter.jumpLine();
        }
        pdfWriter.assertTextInPage(doc);
        pdfWriter.addText(font, lines.getLast());
    }

    private List<String> wrapText(String text, FontDetail font, float maxWidth) throws IOException {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (String word : words) {
            String temp = line.isEmpty() ? word : line + " " + word;
            float width = font.font().getStringWidth(temp) / 1000 * font.size();
            if (width > maxWidth) {
                if (!line.isEmpty()) {
                    lines.add(line.toString());
                    line = new StringBuilder(word);
                } else {
                    lines.add(word); // mot trop long
                    line = new StringBuilder();
                }
            } else {
                line = new StringBuilder(temp);
            }
        }
        if (!line.isEmpty()) {
            lines.add(line.toString());
        }
        return lines;
    }
}
