package com.reader.adventure.story.export.pdf.block;

import com.reader.adventure.story.export.pdf.font.FontDetail;

import java.util.List;

public record JustifiedPartLine(float startingPosition,
                                FontDetail font,
                                List<Object> objectToWrite) {}
