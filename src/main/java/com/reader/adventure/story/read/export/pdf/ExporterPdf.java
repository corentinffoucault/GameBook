package com.reader.adventure.story.read.export.pdf;

import com.reader.adventure.story.read.dao.IStoryDao;
import com.reader.adventure.story.read.export.IExporter;
import com.reader.adventure.story.read.export.pdf.block.PdfBlock;
import com.reader.adventure.story.read.export.pdf.blockBuilder.BlockBuilder;
import com.reader.adventure.story.read.model.choice.DirectionChoice;
import com.reader.adventure.story.read.model.choice.IChoice;
import com.reader.adventure.story.read.model.node.INode;
import com.reader.adventure.story.read.model.story.Story;
import org.apache.pdfbox.pdmodel.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static com.reader.adventure.story.read.export.pdf.font.Fonts.FONT_TITLE;

@Service("exporterPdf")
@Scope("singleton")
public class ExporterPdf implements IExporter {
    public IStoryDao storyDao;
    public PdfWriter pdfWriter;

    public ExporterPdf(IStoryDao storyDao, PdfWriter pdfWriter) {
        this.storyDao = storyDao;
        this.pdfWriter = pdfWriter;
    }

    public void print(Path exportPath) throws IOException {
        Story story = storyDao.getStory();
        try (PDDocument doc = new PDDocument()) {
            pdfWriter.init(doc);

            INode firstNode = story.nodes().get(story.firstNode());
            printNode(doc, pdfWriter, firstNode);
            pdfWriter.jumpLine();

            Set<String> keys = new HashSet<>(story.nodes().keySet());
            keys.remove(story.firstNode());
            for (String key : keys) {
                INode node = story.nodes().get(key);
                printNode(doc, pdfWriter, node);
                pdfWriter.jumpLine();
            }

            pdfWriter.close();
            doc.save(exportPath.toFile());
        }
    }

    private void printNode(PDDocument doc, PdfWriter pdfWriter, INode node) throws IOException {
        pdfWriter.writeCentered(doc, node.title(), FONT_TITLE);

        PdfBlock block = new PdfBlock(pdfWriter.getPageWidth());
        BlockBuilder.buildNodeBlock(block, node);
        pdfWriter.writeParagraph(doc, block);
        pdfWriter.jumpLine();
        for (IChoice choice : node.choice()) {
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
