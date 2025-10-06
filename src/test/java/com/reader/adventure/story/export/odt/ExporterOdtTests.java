package com.reader.adventure.story.export.odt;

import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.dao.Jackson.StoryJsonDaoJackson;
import com.reader.adventure.ui.player.FileLoader;
import org.junit.jupiter.api.Test;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.element.text.TextLineBreakElement;
import org.odftoolkit.odfdom.dom.element.text.TextPElement;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.odftoolkit.odfdom.dom.style.props.OdfParagraphProperties;
import org.odftoolkit.odfdom.dom.style.props.OdfTextProperties;
import org.odftoolkit.odfdom.incubator.doc.office.OdfOfficeStyles;
import org.odftoolkit.odfdom.incubator.doc.style.OdfStyle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ExporterOdtTests {

    @Test
    void apply_choice_direct() throws Exception {
        ExporterOdt example = new ExporterOdt();
        IStoryDao storyDao = new StoryJsonDaoJackson(FileLoader.loadFile("", "/nodes.json"));
        example.print(storyDao.getStory(), Paths.get("example.odt"));

    }

    @Test
    void shouldExportOdtWithCorrectFormatting() throws Exception {
        ExporterOdt exporter = new ExporterOdt();

        IStoryDao storyDao = new StoryJsonDaoJackson(FileLoader.loadFile("", "/nodes.json"));

        Path tmpFile = Files.createTempFile("export-formatting-", ".odt");

        exporter.print(storyDao.getStory(), tmpFile);

        try (OdfTextDocument doc = OdfTextDocument.loadDocument(tmpFile.toFile())) {

            OdfStyle titleStyle = doc.getStylesDom().getOfficeStyles().getStyle("titleStyle", OdfStyleFamily.Paragraph);
            assertEquals("titleStyle", titleStyle.getStyleNameAttribute());
            assertEquals("center", titleStyle.getProperty(OdfParagraphProperties.TextAlign));
            assertEquals("bold", titleStyle.getProperty(OdfTextProperties.FontWeight));

            OdfStyle bodyStyle = doc.getStylesDom().getOfficeStyles().getStyle("bodyStyle", OdfStyleFamily.Paragraph);
            assertEquals("justify", bodyStyle.getProperty(OdfParagraphProperties.TextAlign));
            assertEquals("start", bodyStyle.getProperty(OdfParagraphProperties.TextAlignLast));
            assertEquals("false", bodyStyle.getProperty(OdfParagraphProperties.JustifySingleWord));

            OdfStyle choiceStyle = doc.getStylesDom().getOfficeStyles().getStyle("choiceStyle", OdfStyleFamily.Paragraph);
            assertEquals("justify", choiceStyle.getProperty(OdfParagraphProperties.TextAlign));

            var root = doc.getContentRoot();
            var paragraphs = root.getElementsByTagName("text:p");

            TextPElement titleParagraph = (TextPElement) paragraphs.item(0);
            assertEquals("titleStyle", titleParagraph.getStyleName());
            assertEquals("Noeud 1", titleParagraph.getTextContent());

            TextPElement textParagraph = (TextPElement) paragraphs.item(1);
            assertEquals("Arrivé du héros par le sud, il fait froid, la neige a déployé son manteau sur la ville.", textParagraph.getTextContent());

            TextPElement lineBreakParagraph = (TextPElement) paragraphs.item(2);
            assertEquals(1, lineBreakParagraph.getChildNodes().getLength(), "Le paragraphe doit contenir un seul élément");
            assertTrue(lineBreakParagraph.getFirstChild() instanceof TextLineBreakElement,
                    "Le paragraphe doit contenir un TextLineBreakElement");

            TextPElement lineBreakParagraph2 = (TextPElement) paragraphs.item(3);
            assertEquals(1, lineBreakParagraph2.getChildNodes().getLength(), "Le paragraphe doit contenir un seul élément");
            assertTrue(lineBreakParagraph2.getFirstChild() instanceof TextLineBreakElement,
                    "Le paragraphe doit contenir un TextLineBreakElement");

            TextPElement choiceParagraph = (TextPElement) paragraphs.item(4);
            assertEquals("Vous avez trop envie de vous mettre au chaud à la taverne et manger une bonne soupe chaude avec un morceau de boudin. Vous dirigez donc vos pas vers le nord en empruntant *** Rendez vous en Noeud 1.1.", choiceParagraph.getTextContent());
            var spans = choiceParagraph.getElementsByTagName("text:span");
            boolean hasBoldSpan = false;
            for (int j = 0; j < spans.getLength(); j++) {
                var span = spans.item(j);
                var styleAttr = span.getAttributes().getNamedItem("text:style-name");
                if (styleAttr != null && styleAttr.getNodeValue().toLowerCase().contains("strong")) {
                    hasBoldSpan = true;
                    break;
                }
            }
            assertTrue(hasBoldSpan, "Le span de direction doit être en gras");

        }
    }
}

