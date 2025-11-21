package com.reader.adventure.story.read.export;

import java.nio.file.Path;

public interface IExporter {
     void print(Path exportPath) throws Exception;
}