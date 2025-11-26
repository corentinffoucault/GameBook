package com.reader.adventure.story.edition.dao.h2.choice;

import com.reader.adventure.story.edition.dao.h2.condition.AConditionH2;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CONDITIONAL")
public class ChoiceConditionalH2 extends AChoiceH2 {
    @Column(length = 10000)
    String nextFail;
    @Column(columnDefinition="text")
    String success;
    @Column(columnDefinition="text")
    String fail;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "condition_id")
    AConditionH2 condition;

    public ChoiceConditionalH2() {
        super();
    }

    public ChoiceConditionalH2(String name, String text, String next, String nextFail, String success, String fail, AConditionH2 condition) {
        super(name, text, next);
        this.nextFail = nextFail;
        this.success = success;
        this.fail = fail;
        this.condition = condition;
    }

    public String getNextFail() {
        return nextFail;
    }

    public void setNextFail(String nextFail) {
        this.nextFail = nextFail;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

    public AConditionH2 getCondition() {
        return condition;
    }

    public void setCondition(AConditionH2 condition) {
        this.condition = condition;
    }
}