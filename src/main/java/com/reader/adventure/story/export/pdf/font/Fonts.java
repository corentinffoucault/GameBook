package com.reader.adventure.story.export.pdf.font;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class Fonts {


    private static final float FONT_SIZE_TITLE = 16;
    public static final FontDetail FONT_TITLE = new FontDetail(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), FONT_SIZE_TITLE);

    private static final float FONT_SIZE_BODY = 12;
    public static final FontDetail FONT_BODY = new FontDetail(new PDType1Font(Standard14Fonts.FontName.HELVETICA), FONT_SIZE_BODY);
    public static final FontDetail FONT_DIRECTION = new FontDetail(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), FONT_SIZE_BODY);

}
