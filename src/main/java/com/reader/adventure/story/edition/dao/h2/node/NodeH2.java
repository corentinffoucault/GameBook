package com.reader.adventure.story.edition.dao.h2.node;

import com.reader.adventure.story.edition.dao.h2.choice.AChoiceH2;
import com.reader.adventure.story.edition.dao.h2.story.StoryH2;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "node")
public class NodeH2 {
    @Id
    private String id;
    @Column(length = 10000)
    private String title;
    @Column(columnDefinition="text")
    private String text;
    @OneToMany(mappedBy = "node", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AChoiceH2> choice = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private StoryH2 story;

    public NodeH2() {}

    public NodeH2(String id, String title, String text, List<AChoiceH2> choice) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.choice = choice;
    }

    @PrePersist
    private void ensureId() {
        System.out.println("voici l'id");
        System.out.println(id);
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    public void setStory(StoryH2 story) {
        this.story = story;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<AChoiceH2> getChoice() { return choice; }
    public void setChoice(List<AChoiceH2> choice) { this.choice = choice; }
}