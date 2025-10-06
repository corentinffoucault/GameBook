package com.reader.adventure.story.export;

import com.reader.adventure.story.model.node.INode;

import java.nio.file.Path;
import java.util.Map;

public interface IExporter {
     void print(Map<String, INode> nodes, Path exportPath) throws Exception;
}