package com.reader.adventure.story.export.odt;

import com.reader.adventure.story.export.IExporter;
import com.reader.adventure.story.model.choice.DirectionChoice;
import com.reader.adventure.story.model.choice.IChoice;
import com.reader.adventure.story.model.node.INode;
import com.reader.adventure.story.model.story.IStory;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.element.office.OfficeTextElement;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.odftoolkit.odfdom.dom.style.props.OdfParagraphProperties;
import org.odftoolkit.odfdom.dom.style.props.OdfTextProperties;
import org.odftoolkit.odfdom.incubator.doc.office.OdfOfficeStyles;
import org.odftoolkit.odfdom.incubator.doc.style.OdfStyle;
import org.odftoolkit.odfdom.incubator.doc.text.OdfTextParagraph;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExporterOdt implements IExporter {
    public void print(IStory story, Path exportPath) throws Exception {
        OdfTextDocument document = OdfTextDocument.newTextDocument();
        OfficeTextElement root = document.getContentRoot();
        Node firstP = root.getElementsByTagName("text:p").item(0);
        root.removeChild(firstP);

        Map<String, OdfStyle> styleByName = createStyles(document);
        INode firstNode = story.getNodes().get(story.getFirstNode());
        addNode(document, firstNode, styleByName);
        for (Map.Entry<String, INode> entry : story.getNodes().entrySet()) {
            INode node = entry.getValue();
            if (!node.equals(firstNode)) {
                addNode(document, node, styleByName);
            }
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
        OdfTextParagraph title = document.newParagraph(node.getId());
        title.setStyleName(styleByName.get("titleStyle").getStyleNameAttribute());

        for (String text : node.getText().split("\n")) {
            OdfTextParagraph p = document.newParagraph();
            p.setStyleName(styleByName.get("bodyStyle").getStyleNameAttribute());
            p.addContent(text);
            OdfTextParagraph lineBreak = document.newParagraph();
            lineBreak.newTextLineBreakElement();
        }

        OdfTextParagraph lineBreak = document.newParagraph();
        lineBreak.newTextLineBreakElement();

        for (IChoice choice : node.getChoice()) {
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