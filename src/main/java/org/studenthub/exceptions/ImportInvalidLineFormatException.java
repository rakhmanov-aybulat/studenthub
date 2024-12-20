package org.studenthub.exceptions;

public class ImportInvalidLineFormatException extends Exception {
    int lineNumber;
    String line;
    public ImportInvalidLineFormatException(int lineNumber, String line) {
        super( "Invalid line " + lineNumber + " format: " + line);
        this.lineNumber = lineNumber;
        this.line = line;
    }
    public int getLineNumber() {
        return lineNumber;
    }

    public String getLine() {
        return line;
    }
}
