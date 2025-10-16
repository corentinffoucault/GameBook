package com.reader.adventure.story.export.pdf;

import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.dao.Jackson.StoryJsonDaoJackson;
import com.reader.adventure.story.model.node.INode;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import de.redsix.pdfcompare.PdfComparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExporterPdfTests {

    @Test
    void shouldExportPdfWithCorrectFormatting() throws Exception {
        ExporterPdf exporter = new ExporterPdf();

        IStoryDao storyDao = new StoryJsonDaoJackson();
        storyDao.loadNodes(loadFile("/nodes.json"));
        Map<String, INode> nodes = storyDao.getStory();

        Path tmpFile = Files.createTempFile("export-formatting-", ".pdf");
        exporter.print(nodes, tmpFile);

        Path expected = Path.of("src/test/resources/expectedPdf.pdf");
        boolean isEqual = new PdfComparator(expected, tmpFile).compare().isEqual();
        assertTrue(isEqual);
    }

    public static Reader loadFile(String resourceName) {
        InputStream in = ExporterPdfTests.class.getResourceAsStream(resourceName);
        if (in == null) {
            throw new RuntimeException(resourceName + " introuvable dans resources !");
        }
        return new InputStreamReader(in, StandardCharsets.UTF_8);
    }
}

