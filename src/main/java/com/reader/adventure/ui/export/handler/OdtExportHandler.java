package com.reader.adventure.ui.export.handler;

import com.reader.adventure.story.read.export.ExportTypeKey;
import com.reader.adventure.story.read.export.IExporter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OdtExportHandler extends AExportHandler {
    public OdtExportHandler(@Qualifier("exporterOdt")IExporter exporter) {
        super(exporter);
    }

    public ExportTypeKey getKey() {
        return ExportTypeKey.ODT;
    }
}

