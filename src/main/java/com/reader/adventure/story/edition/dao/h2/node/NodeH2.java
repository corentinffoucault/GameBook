package com.reader.adventure.story.edition.dao.h2.node;

import com.reader.adventure.story.edition.dao.h2.choice.AChoiceH2;
import com.reader.adventure.story.edition.dao.h2.story.StoryH2;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "node")
public class NodeH2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
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

    public NodeH2(long id, String name, String title, String text, List<AChoiceH2> choice) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.text = text;
        this.choice = choice;
    }

    public void setStory(StoryH2 story) {
        this.story = story;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<AChoiceH2> getChoice() { return choice; }
    public void setChoice(List<AChoiceH2> choice) { this.choice = choice; }
}