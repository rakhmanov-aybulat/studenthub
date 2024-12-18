INSERT INTO GROUPS (group_name) VALUES ('Group A');
INSERT INTO GROUPS (group_name) VALUES ('Group B');
INSERT INTO GROUPS (group_name) VALUES ('Group C');

INSERT INTO STUDENTS (full_name, group_id) VALUES ('John Doe', 1);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Jane Smith', 1);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Alice Johnson', 2);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Bob Brown', 2);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Charlie Davis', 3);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Eva White', 3);

INSERT INTO DISCIPLINES (discipline_name) VALUES ('Mathematics');
INSERT INTO DISCIPLINES (discipline_name) VALUES ('Physics');
INSERT INTO DISCIPLINES (discipline_name) VALUES ('Chemistry');

INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (1, 1, '2023-10-01');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (1, 2, '2023-10-02');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (2, 1, '2023-10-03');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (2, 3, '2023-10-04');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (3, 2, '2023-10-05');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (3, 3, '2023-10-06');

INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (1, 1, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (2, 1, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (3, 3, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (4, 3, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (5, 5, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (6, 5, FALSE);

INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (1, 1, '2023-10-07', 85);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (2, 1, '2023-10-08', 90);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (3, 2, '2023-10-09', 78);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (4, 2, '2023-10-10', 88);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (5, 3, '2023-10-11', 92);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (6, 3, '2023-10-12', 80);

