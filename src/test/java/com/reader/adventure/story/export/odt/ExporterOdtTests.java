package com.reader.adventure.story.export.odt;

import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.dao.Jackson.StoryJsonDaoJackson;
import com.reader.adventure.story.model.choice.DirectionChoice;
import com.reader.adventure.story.model.choice.IChoice;
import com.reader.adventure.story.model.node.INode;
import com.reader.adventure.story.model.story.IStory;
import com.reader.adventure.ui.player.FileLoader;
import org.junit.jupiter.api.Test;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.element.office.OfficeTextElement;
import org.odftoolkit.odfdom.dom.element.text.TextLineBreakElement;
import org.odftoolkit.odfdom.dom.element.text.TextPElement;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.odftoolkit.odfdom.dom.style.props.OdfParagraphProperties;
import org.odftoolkit.odfdom.dom.style.props.OdfTextProperties;
import org.odftoolkit.odfdom.incubator.doc.style.OdfStyle;
import org.odftoolkit.odfdom.incubator.doc.text.OdfTextSpan;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class ExporterOdtTests {

    @Test
    void shouldExportOdtWithCorrectFormatting() throws Exception {
        ExporterOdt exporter = new ExporterOdt();

        IStoryDao storyDao = new StoryJsonDaoJackson();
        storyDao.loadNodes(FileLoader.loadFile("", "/nodes.json"));
        IStory story = storyDao.getStory();
        
        Path tmpFile = Files.createTempFile("export-formatting-", ".odt");
        exporter.print(story, tmpFile);

        try (OdfTextDocument doc = OdfTextDocument.loadDocument(tmpFile.toFile())) {
            assertStyle(doc);

            OfficeTextElement root = doc.getContentRoot();
            NodeList paragraphs = root.getElementsByTagName("text:p");
            int index = 0;
            INode firstNode = story.getNodes().get(story.getFirstNode());
            index = assertNode(paragraphs, firstNode, index);

            Set<String> keys = new HashSet<>(story.getNodes().keySet());
            keys.remove(story.getFirstNode());
            for (String key : keys) {
                INode node = story.getNodes().get(key);
                index = assertNode(paragraphs, node, index);
            }

        }
    }

    void assertStyle(OdfTextDocument doc) throws IOException, SAXException {
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
    }

    int assertNode(NodeList paragraphs, INode node, int index) {
        assertTitle(paragraphs, index++, node.getId());
        index = assertNode(paragraphs, index, node.getText());
        assertJumpLine(paragraphs, index++);

        for (IChoice choice : node.getChoice()) {
            index = assertChoice(paragraphs, choice, index);
        }

        return index;
    }

    void assertTitle(NodeList paragraphs, int index, String textContent) {
        TextPElement titleParagraph = (TextPElement) paragraphs.item(index);
        assertEquals("titleStyle", titleParagraph.getStyleName());
        assertEquals(textContent, titleParagraph.item(0).getTextContent());
    }

    int assertNode(NodeList paragraphs, int index, String textContent) {
        for (String text : textContent.split("\n")) {
            TextPElement titleParagraph = (TextPElement) paragraphs.item(index++);
            assertEquals("bodyStyle", titleParagraph.getStyleName());
            assertEquals(text, titleParagraph.item(0).getTextContent());
            assertJumpLine(paragraphs, index++);
        }
        return index;
    }

    void assertJumpLine(NodeList paragraphs, int index) {
        Node lineBreakParagraph = paragraphs.item(index);
        assertEquals(1, lineBreakParagraph.getChildNodes().getLength());
        assertInstanceOf(TextLineBreakElement.class, lineBreakParagraph.getFirstChild());
    }

    int assertChoice(NodeList paragraphs, IChoice choice, int index) {
        List<DirectionChoice> directions = choice.getAllDirection();

        for (DirectionChoice direction : directions) {
            TextPElement choiceParagraph = (TextPElement) paragraphs.item(index++);
            assertEquals("choiceStyle", choiceParagraph.getStyleName());
            assertEquals(direction.text() + " Rendez vous en ", choiceParagraph.item(0).getTextContent());
            assertEquals(direction.nextNode(), choiceParagraph.item(1).getTextContent());
            assertEquals("Strong Emphasis", ((OdfTextSpan) choiceParagraph.item(1)).getStyleName());
            assertEquals(".", choiceParagraph.item(2).getTextContent());
            assertJumpLine(paragraphs, index++);
        }

        return index;
    }
}

