package com.reader.adventure.story.export;

import com.reader.adventure.story.model.story.IStory;

import java.nio.file.Path;

public interface IExporter {
     void print(IStory story, Path exportPath) throws Exception;
}