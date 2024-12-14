package org.studenthub.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Discipline {
    private SimpleIntegerProperty disciplineId;
    private SimpleStringProperty disciplineName;

    public Discipline(int disciplineId, String disciplineName) {
        this.disciplineId = new SimpleIntegerProperty(disciplineId);
        this.disciplineName = new SimpleStringProperty(disciplineName);
    }

    // Getters and Setters
    public int getDisciplineId() {
        return disciplineId.get();
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId.set(disciplineId);
    }

    public String getDisciplineName() {
        return disciplineName.get();
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName.set(disciplineName);
    }
}
