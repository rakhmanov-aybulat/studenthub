package org.studenthub.exceptions;

public class GroupAlreadyExistsException extends Exception {
    private final String groupName;
    public GroupAlreadyExistsException(String groupName) {
        super("Group with name: " + groupName + " already exists");
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }
}
