package com.reader.adventure.story.read.export.pdf;

import com.reader.adventure.story.read.dao.IStoryDao;
import com.reader.adventure.story.read.dao.Jackson.StoryJsonDaoJackson;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import de.redsix.pdfcompare.PdfComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ExporterPdfTests {
    @Autowired
    private IStoryDao storyDao;

    @Autowired
    private ExporterPdf exporter;

    @Test
    void shouldExportPdfWithCorrectFormatting() throws Exception {
        IStoryDao storyDao = new StoryJsonDaoJackson();
        storyDao.loadNodes(loadFile("/nodes.json"));

        Path tmpFile = Files.createTempFile("export-formatting-", ".pdf");
        exporter.print(tmpFile);

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

