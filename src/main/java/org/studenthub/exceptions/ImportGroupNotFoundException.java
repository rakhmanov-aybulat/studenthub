package org.studenthub.exceptions;

public class ImportGroupNotFoundException extends Exception {
    private final String groupName;
    private final int lineNumber;
    private final String line;

    public ImportGroupNotFoundException(String groupName, int lineNumber, String line) {
        super("Group not found: " + groupName + " on line " + lineNumber + ": " + line);
        this.groupName = groupName;
        this.lineNumber = lineNumber;
        this.line = line;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getLine() {
        return line;
    }
}
