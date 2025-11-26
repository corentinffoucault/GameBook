package com.reader.adventure.story.edition.dao.h2.story;

import com.reader.adventure.story.edition.dao.h2.node.NodeH2;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "story")
public class StoryH2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String author;
    String firstNode;
    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<NodeH2> nodes = new ArrayList<>();;

    public StoryH2() {
    }

    public long getId() {
        return id;
    }

    public StoryH2(String author, String firstNode, List<NodeH2> nodes) {
        this.author = author;
        this.firstNode = firstNode;
        this.nodes = nodes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(String firstNode) {
        this.firstNode = firstNode;
    }

    public List<NodeH2> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeH2> nodes) {
        this.nodes = nodes;
    }
}