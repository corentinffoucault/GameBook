package com.reader.adventure.story.edition.dao.h2.choice;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DIRECT")
public class ChoiceDirectH2 extends AChoiceH2 {
    ChoiceDirectH2() {
        super();
    }

    ChoiceDirectH2(String name, String text) {
        super(name, text);
    }
}