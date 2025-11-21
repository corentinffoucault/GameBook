package com.reader.adventure.ui.export;

import com.reader.adventure.story.read.export.ExportTypeKey;
import java.nio.file.Path;

public record ExportOption(ExportTypeKey extension, Path fileToSave) {
}
