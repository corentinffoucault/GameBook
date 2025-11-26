package com.reader.adventure.story.read.export.odt;

import com.reader.adventure.story.read.dao.IStoryDao;
import com.reader.adventure.story.read.export.IExporter;
import com.reader.adventure.story.read.model.choice.DirectionChoice;
import com.reader.adventure.story.read.model.choice.IChoice;
import com.reader.adventure.story.read.model.node.INode;
import com.reader.adventure.story.read.model.story.Story;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.element.office.OfficeTextElement;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.odftoolkit.odfdom.dom.style.props.OdfParagraphProperties;
import org.odftoolkit.odfdom.dom.style.props.OdfTextProperties;
import org.odftoolkit.odfdom.incubator.doc.office.OdfOfficeStyles;
import org.odftoolkit.odfdom.incubator.doc.style.OdfStyle;
import org.odftoolkit.odfdom.incubator.doc.text.OdfTextParagraph;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

@Service("exporterOdt")
@Scope("singleton")
public class ExporterOdt implements IExporter {
    public IStoryDao storyDao;

    public ExporterOdt(IStoryDao storyDao) {
        this.storyDao  = storyDao;
    }

    public void print(Path exportPath) throws Exception {
        Story story = storyDao.getStory();
        OdfTextDocument document = OdfTextDocument.newTextDocument();
        OfficeTextElement root = document.getContentRoot();
        Node firstP = root.getElementsByTagName("text:p").item(0);
        root.removeChild(firstP);

        Map<String, OdfStyle> styleByName = createStyles(document);
        INode firstNode = story.nodes().get(story.firstNode());
        addNode(document, firstNode, styleByName);

        Set<String> keys = new HashSet<>(story.nodes().keySet());
        keys.remove(story.firstNode());
        for (String key : keys) {
            INode node = story.nodes().get(key);
            addNode(document, node, styleByName);
        }
        document.save(exportPath.toString());
    }

    public Map<String, OdfStyle> createStyles(OdfTextDocument document) throws IOException, SAXException {
        Map<String, OdfStyle>  styleMap = new HashMap<String, OdfStyle>();

        OdfOfficeStyles styles = document.getStylesDom().getOfficeStyles();

        OdfStyle titleStyle = styles.newStyle("titleStyle", OdfStyleFamily.Paragraph);
        titleStyle.setProperty(OdfParagraphProperties.TextAlign, "center");
        titleStyle.setProperty(OdfTextProperties.FontWeight, "bold");
        styleMap.put("titleStyle", titleStyle);

        OdfStyle bodyStyle = styles.newStyle("bodyStyle", OdfStyleFamily.Paragraph);
        bodyStyle.setProperty(OdfParagraphProperties.TextAlign, "justify");
        bodyStyle.setProperty(OdfParagraphProperties.TextAlignLast, "start");
        bodyStyle.setProperty(OdfParagraphProperties.JustifySingleWord, "false");
        styleMap.put("bodyStyle", bodyStyle);

        OdfStyle choiceStyle = styles.newStyle("choiceStyle", OdfStyleFamily.Paragraph);
        choiceStyle.setProperty(OdfParagraphProperties.TextAlign, "justify");
        styleMap.put("choiceStyle", choiceStyle);

        return styleMap;
    }

    public void addNode(OdfTextDocument document, INode node, Map<String, OdfStyle> styleByName) throws Exception {
        OdfTextParagraph title = document.newParagraph(node.id());
        title.setStyleName(styleByName.get("titleStyle").getStyleNameAttribute());

        for (String text : node.text().split("\n")) {
            OdfTextParagraph p = document.newParagraph();
            p.setStyleName(styleByName.get("bodyStyle").getStyleNameAttribute());
            p.addContent(text);
            OdfTextParagraph lineBreak = document.newParagraph();
            lineBreak.newTextLineBreakElement();
        }

        OdfTextParagraph lineBreak = document.newParagraph();
        lineBreak.newTextLineBreakElement();

        for (IChoice choice : node.choice()) {
            addChoice(document, choice, styleByName);
        }
    }

    public void addChoice(OdfTextDocument document, IChoice choice, Map<String, OdfStyle> styleByName) throws Exception {
        List<DirectionChoice> directions = choice.getAllDirection();
        for (DirectionChoice direction : directions) {
            addDirection(document, direction, styleByName);
            OdfTextParagraph lineBreak = document.newParagraph();
            lineBreak.newTextLineBreakElement();
        }
    }

    public void addDirection(OdfTextDocument document, DirectionChoice direction, Map<String, OdfStyle> styleByName) throws Exception {
        OdfTextParagraph p = document.newParagraph(direction.text());
        p.addContent(" Rendez vous en ");
        p.addStyledSpan("Strong Emphasis", direction.nextNode());
        p.addContent(".");
        p.setStyleName(styleByName.get("choiceStyle").getStyleNameAttribute());
    }
}