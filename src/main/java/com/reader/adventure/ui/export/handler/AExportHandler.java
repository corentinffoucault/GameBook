package com.reader.adventure.ui.export.handler;

import com.reader.adventure.story.read.export.ExportTypeKey;
import com.reader.adventure.story.read.export.IExporter;

import java.nio.file.Path;

public abstract class AExportHandler {
    private final IExporter exporter;

    public AExportHandler(IExporter exporter) {
        this.exporter = exporter;
    }

    public void print(Path fileToSave) throws Exception {
        exporter.print(fileToSave);
    }

    public abstract ExportTypeKey getKey();
}
