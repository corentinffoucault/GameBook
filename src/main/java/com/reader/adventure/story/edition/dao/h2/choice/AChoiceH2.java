package com.reader.adventure.story.edition.dao.h2.choice;

import com.reader.adventure.story.edition.dao.h2.node.NodeH2;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "choice_type")
@Table(name = "choice")
public abstract class AChoiceH2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 10000)
    String name;
    @Column(columnDefinition="text")
    String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "next_node_id")
    private NodeH2 nextNode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    private NodeH2 node;

    AChoiceH2() {}

    AChoiceH2(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NodeH2 getNextNode() { return nextNode; }
    public void setNextNode(NodeH2 nextNode) { this.nextNode = nextNode; }

    public NodeH2 getNode() { return node; }
    public void setNode(NodeH2 node) { this.node = node; }
}
