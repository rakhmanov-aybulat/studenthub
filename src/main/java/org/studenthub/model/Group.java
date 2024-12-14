package org.studenthub.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Group {
    private SimpleIntegerProperty groupId;
    private SimpleStringProperty groupName;

    public Group(int groupId, String groupName) {
        this.groupId = new SimpleIntegerProperty(groupId);
        this.groupName = new SimpleStringProperty(groupName);
    }

    public int getGroupId() {
        return groupId.get();
    }

    public void setGroupId(int groupId) {
        this.groupId.set(groupId);
    }

    public String getGroupName() {
        return groupName.get();
    }

    public void setGroupName(String groupName) {
        this.groupName.set(groupName);
    }
}
