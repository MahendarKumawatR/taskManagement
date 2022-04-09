package com.example.taskManagement.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Label {

    @Column(name = "LABELNAME")
    private String labelName;

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public String toString() {
        return "Label{" +
                "labelName='" + labelName + '\'' +
                '}';
    }
}
