package com.reader.adventure.story.read.export.pdf.blockBuilder;

import com.reader.adventure.story.read.dao.IStoryDao;
import com.reader.adventure.story.read.dao.Jackson.StoryJsonDaoJackson;
import com.reader.adventure.story.read.export.pdf.block.PdfBlock;
import com.reader.adventure.story.read.export.pdf.block.PdfParagraph;
import com.reader.adventure.story.read.export.pdf.block.PdfLine;
import com.reader.adventure.story.read.export.pdf.block.PdfPartLine;
import com.reader.adventure.story.read.export.pdf.font.Fonts;
import com.reader.adventure.story.read.model.choice.DirectionChoice;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockBuilderTests {

    @Test
    void shouldBuildEmptyCondition() {
        assertDoesNotThrow(() -> {
            DirectionChoice directionChoice = new DirectionChoice("", "", "");
            PdfBlock block = new PdfBlock(110);
            BlockBuilder.buildChoiceBlock(block, directionChoice);
            assertEquals(1, block.getParagraphs().size());

            PdfParagraph paragraph = block.getParagraphs().getFirst();
            assertEquals(1, paragraph.getLines().size());

            PdfLine firstLine = paragraph.getLines().getFirst();
            assertEquals(98.02, firstLine.getSize(), 0.01);
            assertEquals(Fonts.FONT_DIRECTION, firstLine.getCurrentFont());
            assertEquals(2, firstLine.getParts().size());

            PdfPartLine firstPartLine = firstLine.getParts().getFirst();
            assertEquals("[]", firstPartLine.getText().toString());
            assertEquals(Fonts.FONT_BODY, firstPartLine.getFont());

            PdfPartLine lastPartLine = firstLine.getParts().getLast();
            assertEquals("[Rendez, vous, en, .]", lastPartLine.getText().toString());
            assertEquals(Fonts.FONT_DIRECTION, lastPartLine.getFont());
        });
    }

    @Test
    void shouldBuildARealConditionWithUserJumpLine() {
        assertDoesNotThrow(() -> {
            IStoryDao storyDao = new StoryJsonDaoJackson();
            storyDao.loadNodes(loadFile("/nodes.json"));
            PdfBlock block = new PdfBlock(PDRectangle.A4.getWidth());

            DirectionChoice directionChoice = storyDao.getNodeById("Noeud 3.16").choice().get(1).getAllDirection().getFirst();
            BlockBuilder.buildChoiceBlock(block, directionChoice);
            assertEquals(2, block.getParagraphs().size());

            PdfParagraph paragraph = block.getParagraphs().getFirst();
            assertEquals(2, paragraph.getLines().size());

            PdfLine firstLine = paragraph.getLines().getFirst();
            assertEquals(556.57, firstLine.getSize(), 0.01);
            assertEquals(1, firstLine.getParts().size());
            assertEquals("[Vous, avez, déjà, visité, d'autres, lieux, avant, de, venir, voir, le, seigneur., vous, avez, donc, peut, être, une, idée, du]", firstLine.getParts().getFirst().getText().toString());
            assertEquals(Fonts.FONT_BODY, firstLine.getCurrentFont());

            PdfLine secondLine = paragraph.getLines().get(1);
            assertEquals(156.08, secondLine.getSize(), 0.01);
            assertEquals(1, secondLine.getParts().size());
            assertEquals("[coupable, et, de, sa, motivation.]", secondLine.getParts().getFirst().getText().toString());
            assertEquals(Fonts.FONT_BODY, secondLine.getCurrentFont());

            PdfParagraph secondParagraph = block.getParagraphs().getLast();
            assertEquals(2, secondParagraph.getLines().size());

            PdfLine thirdLine = secondParagraph.getLines().getFirst();
            assertEquals(590.91, thirdLine.getSize(), 0.01);
            assertEquals(2, thirdLine.getParts().size());

            PdfPartLine firstPartLine = thirdLine.getParts().getFirst();
            assertEquals("[Deux, mots, spécifiques, donc., Notez, le, nombre, de, lettre, de, chaque, mot., Multipliez, les, et, entrez, ce, code.]", firstPartLine.getText().toString());
            assertEquals(Fonts.FONT_BODY, firstPartLine.getFont());

            PdfPartLine lastPartLine = thirdLine.getParts().getLast();
            assertEquals("[Rendez]", lastPartLine.getText().toString());
            assertEquals(Fonts.FONT_DIRECTION, lastPartLine.getFont());

            PdfLine directionLine = secondParagraph.getLines().getLast();
            assertEquals(106.03, directionLine.getSize(), 0.01);
            assertEquals(1, directionLine.getParts().size());
            assertEquals("[vous, en, Noeud, 64.]", directionLine.getParts().getFirst().getText().toString());
            assertEquals(Fonts.FONT_DIRECTION, directionLine.getCurrentFont());
        });
    }

    @Test
    void shouldBuildARealConditionWithoutUserJumpLine() {
        assertDoesNotThrow(() -> {
            IStoryDao storyDao = new StoryJsonDaoJackson();
            storyDao.loadNodes(loadFile("/nodes.json"));
            PdfBlock block = new PdfBlock(PDRectangle.A4.getWidth());

            DirectionChoice directionChoice = storyDao.getNodeById(storyDao.getFirstNodeId()).choice().getFirst().getAllDirection().getFirst();
            BlockBuilder.buildChoiceBlock(block, directionChoice);
            assertEquals(1, block.getParagraphs().size());

            PdfParagraph paragraph = block.getParagraphs().getFirst();
            assertEquals(2, paragraph.getLines().size());

            PdfLine firstLine = paragraph.getLines().getFirst();
            assertEquals(554.987, firstLine.getSize(), 0.01);
            assertEquals(1, firstLine.getParts().size());
            assertEquals("[Vous, avez, trop, envie, de, vous, mettre, au, chaud, à, la, taverne, et, manger, une, bonne, soupe, chaude, avec, un]", firstLine.getParts().getFirst().getText().toString());
            assertEquals(Fonts.FONT_BODY, firstLine.getCurrentFont());

            PdfLine secondLine = paragraph.getLines().get(1);
            assertEquals(568.90, secondLine.getSize(), 0.01);
            assertEquals(2, secondLine.getParts().size());

            PdfPartLine firstPartLine = secondLine.getParts().getFirst();
            assertEquals("[morceau, de, boudin., Vous, dirigez, donc, vos, pas, vers, le, nord, en, empruntant, ***]", firstPartLine.getText().toString());
            assertEquals(Fonts.FONT_BODY, firstPartLine.getFont());

            PdfPartLine lastPartLine = secondLine.getParts().getLast();
            assertEquals("[Rendez, vous, en, Noeud, 1.1.]", lastPartLine.getText().toString());
            assertEquals(Fonts.FONT_DIRECTION, lastPartLine.getFont());
        });
    }

    public static Reader loadFile(String resourceName) {
        InputStream in = BlockBuilderTests.class.getResourceAsStream(resourceName);
        if (in == null) {
            throw new RuntimeException(resourceName + " introuvable dans resources !");
        }
        return new InputStreamReader(in, StandardCharsets.UTF_8);
    }
}

