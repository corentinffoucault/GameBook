package com.reader.adventure.story.edition.dao.h2.choice;

import com.reader.adventure.story.edition.dao.h2.condition.AConditionH2;
import com.reader.adventure.story.edition.dao.h2.node.NodeH2;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CONDITIONAL")
public class ChoiceConditionalH2 extends AChoiceH2 {
    @Column(columnDefinition="text")
    String success;
    @Column(columnDefinition="text")
    String fail;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "condition_id")
    AConditionH2 condition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_fail_id")
    private NodeH2 nextFail;

    public ChoiceConditionalH2() {
        super();
    }

    public ChoiceConditionalH2(String name, String text, String success, String fail, AConditionH2 condition) {
        super(name, text);
        this.success = success;
        this.fail = fail;
        this.condition = condition;
    }

    public NodeH2 getNextFail() {
        return nextFail;
    }

    public void setNextFail(NodeH2 nextFail) {
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