CREATE TABLE GROUPS (
    group_id INTEGER PRIMARY KEY,
    group_name TEXT NOT NULL
);

CREATE TABLE STUDENTS (
    student_id INTEGER PRIMARY KEY,
    full_name TEXT NOT NULL,
    group_id INTEGER,
    FOREIGN KEY (group_id) REFERENCES GROUPS(group_id)
);

CREATE TABLE DISCIPLINES (
    discipline_id INTEGER PRIMARY KEY,
    discipline_name TEXT NOT NULL
);

CREATE TABLE SCHEDULE (
    schedule_id INTEGER PRIMARY KEY,
    group_id INTEGER,
    discipline_id INTEGER,
    date DATE,
    FOREIGN KEY (group_id) REFERENCES GROUPS(group_id),
    FOREIGN KEY (discipline_id) REFERENCES DISCIPLINES(discipline_id)
);

CREATE TABLE ATTENDANCE (
    attendance_id INTEGER PRIMARY KEY,
    student_id INTEGER,
    schedule_id INTEGER,
    present BOOLEAN,
    FOREIGN KEY (student_id) REFERENCES STUDENTS(student_id),
    FOREIGN KEY (schedule_id) REFERENCES SCHEDULE(schedule_id)
);

CREATE TABLE PRACTICALWORKS (
    practical_work_id INTEGER PRIMARY KEY,
    student_id INTEGER,
    discipline_id INTEGER,
    date DATE,
    grade INTEGER,
    FOREIGN KEY (student_id) REFERENCES STUDENTS(student_id),
    FOREIGN KEY (discipline_id) REFERENCES DISCIPLINES(discipline_id)
);
