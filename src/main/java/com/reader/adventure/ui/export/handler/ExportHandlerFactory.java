package com.reader.adventure.ui.export.handler;

import com.reader.adventure.story.read.export.ExportTypeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExportHandlerFactory {

    private final Map<ExportTypeKey, AExportHandler> handlers;

    @Autowired
    public ExportHandlerFactory(List<AExportHandler> handlerBeans) {
        this.handlers = handlerBeans.stream()
                .collect(Collectors.toMap(AExportHandler::getKey, h -> h));
    }

    public AExportHandler getHandler(ExportTypeKey key) {
        AExportHandler handler = handlers.get(key);
        if (handler == null) {
            throw new IllegalArgumentException("No export handler for key " + key);
        }
        return handler;
    }
}
