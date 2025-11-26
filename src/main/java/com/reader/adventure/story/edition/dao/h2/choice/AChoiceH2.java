package com.reader.adventure.story.edition.dao.h2.choice;

import com.reader.adventure.story.edition.dao.h2.node.NodeH2;
import com.reader.adventure.story.edition.dao.h2.story.StoryH2;
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
    @Column(length = 10000)
    String next;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    private NodeH2 node;

    AChoiceH2() {}

    AChoiceH2(String name, String text, String next) {
        this.name = name;
        this.text = text;
        this.next = next;
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

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
