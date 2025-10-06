package com.reader.adventure.story.export;

import com.reader.adventure.story.export.odt.ExporterOdt;

public enum ExportTypeKey {
    ODT("odt", new ExporterOdt());

    private final String extension;
    private final IExporter exporter;

    ExportTypeKey(String extension, IExporter exporter) {
        this.extension = extension;
        this.exporter = exporter;
    }

    public IExporter getExporter() {
        return exporter;
    }

    @Override
    public String toString() {
        return extension;
    }
}

