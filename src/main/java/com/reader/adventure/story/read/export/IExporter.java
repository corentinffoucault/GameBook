package com.reader.adventure.story.read.export;

import com.reader.adventure.story.read.model.story.IStory;

import java.nio.file.Path;

public interface IExporter {
     void print(IStory story, Path exportPath) throws Exception;
}