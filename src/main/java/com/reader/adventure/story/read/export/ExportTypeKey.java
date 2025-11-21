package com.reader.adventure.story.read.export;

public enum ExportTypeKey {
    ODT("odt"),
    PDF("pdf");

    private final String extension;

    ExportTypeKey(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return extension;
    }
}

