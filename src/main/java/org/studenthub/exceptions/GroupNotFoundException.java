package org.studenthub.exceptions;

public class GroupNotFoundException extends Exception {
    private final String groupName;

    public GroupNotFoundException(String groupName) {
        super("Group not found with name: " + groupName);
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }
}
