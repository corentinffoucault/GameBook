package com.reader.adventure.story.export.pdf;

import com.reader.adventure.story.export.IExporter;
import com.reader.adventure.story.export.pdf.block.PdfBlock;
import com.reader.adventure.story.export.pdf.blockBuilder.BlockBuilder;
import com.reader.adventure.story.model.choice.DirectionChoice;
import com.reader.adventure.story.model.choice.IChoice;
import com.reader.adventure.story.model.node.INode;
import com.reader.adventure.story.model.story.IStory;
import org.apache.pdfbox.pdmodel.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static com.reader.adventure.story.export.pdf.font.Fonts.FONT_TITLE;

public class ExporterPdf implements IExporter {
    private static final float LINE_HEIGHT = 14;

    public void print(IStory story, Path exportPath) throws IOException {
        try (PDDocument doc = new PDDocument()) {
            PdfWriter pdfWriter = new PdfWriter(LINE_HEIGHT);
            pdfWriter.init(doc);

            INode firstNode = story.getNodes().get(story.getFirstNode());
            printNode(doc, pdfWriter, firstNode);
            pdfWriter.jumpLine();

            Set<String> keys = new HashSet<>(story.getNodes().keySet());
            keys.remove(story.getFirstNode());
            for (String key : keys) {
                INode node = story.getNodes().get(key);
                printNode(doc, pdfWriter, node);
                pdfWriter.jumpLine();
            }

            pdfWriter.close();
            doc.save(exportPath.toFile());
        }
    }

    private void printNode(PDDocument doc, PdfWriter pdfWriter, INode node) throws IOException {
        pdfWriter.writeCentered(doc, node.getTitle(), FONT_TITLE);

        PdfBlock block = new PdfBlock(pdfWriter.getPageWidth());
        BlockBuilder.buildNodeBlock(block, node);
        pdfWriter.writeParagraph(doc, block);
        pdfWriter.jumpLine();
        for (IChoice choice : node.getChoice()) {
            printChoice(doc, pdfWriter, choice);
        }
    }

    private void printChoice(PDDocument doc, PdfWriter pdfWriter, IChoice choice) throws IOException {
        for (DirectionChoice dir : choice.getAllDirection()) {
            PdfBlock block = new PdfBlock(pdfWriter.getPageWidth());
            BlockBuilder.buildChoiceBlock(block, dir);
            pdfWriter.writeParagraph(doc, block);
        }
    }
}
