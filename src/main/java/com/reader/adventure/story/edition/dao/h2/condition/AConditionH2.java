package com.reader.adventure.story.edition.dao.h2.condition;

import com.reader.adventure.game.ComparatorKey;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "condition_type")
@Table(name = "condition")
public abstract class AConditionH2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    ComparatorKey comparator;

    AConditionH2() {}
    AConditionH2(ComparatorKey comparator) {
        this.comparator = comparator;
    }

    public ComparatorKey getComparator() {
        return comparator;
    }

    public void setComparator(ComparatorKey comparator) {
        this.comparator = comparator;
    }
}
