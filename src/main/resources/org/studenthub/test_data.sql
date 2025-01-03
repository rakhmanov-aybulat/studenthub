INSERT INTO GROUPS (group_name) VALUES ('ИКБ-31');
INSERT INTO GROUPS (group_name) VALUES ('ИКБ-32');
INSERT INTO GROUPS (group_name) VALUES ('ИКБ-33');
INSERT INTO GROUPS (group_name) VALUES ('ИКБ-34');

INSERT INTO STUDENTS (full_name, group_id) VALUES ('Иванов Иван Иванович', 1);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Петров Петр Петрович', 1);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Сидоров Сидор Сидорович', 1);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Кузнецов Кузьма Кузьмич', 1);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Смирнов Сергей Сергеевич', 1);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Попов Павел Павлович', 1);

INSERT INTO STUDENTS (full_name, group_id) VALUES ('Михайлов Михаил Михайлович', 2);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Новиков Николай Николаевич', 2);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Федоров Федор Федорович', 2);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Кравцов Константин Константинович', 2);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Никитин Никита Никитич', 2);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Соколов Сергей Сергеевич', 2);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Яковлев Яков Яковлевич', 2);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Морозов Михаил Михайлович', 2);

INSERT INTO STUDENTS (full_name, group_id) VALUES ('Андреев Андрей Андреевич', 3);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Максимов Максим Максимович', 3);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Ильин Илья Ильич', 3);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Гусев Григорий Григорьевич', 3);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Тихонов Тихон Тихонович', 3);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Романов Роман Романович', 3);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Лебедев Леонид Леонидович', 3);

INSERT INTO STUDENTS (full_name, group_id) VALUES ('Козлов Константин Константинович', 4);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Орлов Олег Олегович', 4);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Ефимов Ефим Ефимович', 4);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Беляев Борис Борисович', 4);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Фролов Федор Федорович', 4);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Крылов Кирилл Кириллович', 4);
INSERT INTO STUDENTS (full_name, group_id) VALUES ('Тарасов Тарас Тарасович', 4);

INSERT INTO DISCIPLINES (discipline_name) VALUES ('Теория информации');
INSERT INTO DISCIPLINES (discipline_name) VALUES ('Электротехника');
INSERT INTO DISCIPLINES (discipline_name) VALUES ('Документоведение');
INSERT INTO DISCIPLINES (discipline_name) VALUES ('Разработка защищенных сетевых приложений');
INSERT INTO DISCIPLINES (discipline_name) VALUES ('Теория вероятностей и математическая статистика');
INSERT INTO DISCIPLINES (discipline_name) VALUES ('Информационные технологии');
INSERT INTO DISCIPLINES (discipline_name) VALUES ('Элективные дисциплины по физической культуре и спорту');

INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (1, 1, '2024-11-11');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (1, 2, '2024-11-11');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (1, 3, '2024-11-11');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (1, 4, '2024-11-11');

INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (2, 5, '2024-11-11');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (2, 6, '2024-11-11');

INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (3, 7, '2024-11-11');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (3, 1, '2024-11-11');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (3, 2, '2024-11-11');

INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (4, 3, '2024-11-11');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (4, 4, '2024-11-11');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (4, 5, '2024-11-11');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (4, 6, '2024-11-11');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (4, 7, '2024-11-11');

INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (1, 1, '2024-11-12');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (1, 2, '2024-11-12');

INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (2, 3, '2024-11-12');

INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (3, 4, '2024-11-12');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (3, 5, '2024-11-12');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (3, 6, '2024-11-12');

INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (4, 7, '2024-11-12');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (4, 1, '2024-11-12');
INSERT INTO SCHEDULE (group_id, discipline_id, date) VALUES (4, 2, '2024-11-12');

INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (1, 1, '2024-11-11', 4);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (2, 2, '2024-11-11', 5);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (3, 3, '2024-11-11', 3);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (4, 4, '2024-11-11', 4);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (5, 5, '2024-11-11', 5);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (6, 6, '2024-11-11', 3);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (7, 7, '2024-11-11', 4);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (8, 1, '2024-11-11', 5);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (9, 2, '2024-11-11', 3);
INSERT INTO PRACTICALWORKS (student_id, discipline_id, date, grade) VALUES (10, 3, '2024-11-11', 4);


INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (1, 4, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (1, 1, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (1, 3, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (1, 2, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (1, 15, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (1, 16, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (2, 4, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (2, 16, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (2, 3, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (2, 15, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (2, 1, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (2, 2, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (3, 15, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (3, 3, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (3, 4, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (3, 1, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (3, 2, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (3, 16, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (4, 15, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (4, 2, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (4, 4, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (4, 1, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (4, 3, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (4, 16, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (5, 1, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (5, 4, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (5, 3, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (5, 15, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (5, 16, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (5, 2, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (6, 1, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (6, 4, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (6, 2, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (6, 3, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (6, 15, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (6, 16, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (7, 5, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (7, 6, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (7, 17, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (8, 6, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (8, 5, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (8, 17, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (9, 6, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (9, 5, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (9, 17, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (10, 5, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (10, 6, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (10, 17, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (11, 17, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (11, 5, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (11, 6, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (12, 17, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (12, 5, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (12, 6, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (13, 6, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (13, 5, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (13, 17, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (14, 5, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (14, 17, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (14, 6, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (15, 7, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (15, 8, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (15, 19, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (15, 9, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (15, 18, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (15, 20, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (16, 19, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (16, 9, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (16, 7, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (16, 8, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (16, 18, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (16, 20, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (17, 19, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (17, 18, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (17, 9, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (17, 7, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (17, 8, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (17, 20, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (18, 18, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (18, 7, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (18, 8, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (18, 9, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (18, 19, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (18, 20, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (19, 19, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (19, 7, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (19, 8, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (19, 9, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (19, 18, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (19, 20, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (20, 19, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (20, 18, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (20, 9, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (20, 7, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (20, 8, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (20, 20, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (21, 20, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (21, 18, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (21, 7, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (21, 8, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (21, 9, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (21, 19, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (22, 21, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (22, 10, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (22, 11, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (22, 23, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (22, 14, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (22, 22, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (22, 12, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (22, 13, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (23, 12, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (23, 21, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (23, 23, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (23, 13, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (23, 14, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (23, 10, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (23, 11, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (23, 22, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (24, 12, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (24, 14, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (24, 13, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (24, 23, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (24, 10, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (24, 11, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (24, 21, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (24, 22, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (25, 22, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (25, 12, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (25, 13, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (25, 14, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (25, 11, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (25, 23, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (25, 21, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (25, 10, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (26, 22, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (26, 10, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (26, 13, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (26, 12, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (26, 21, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (26, 23, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (26, 11, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (26, 14, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (27, 14, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (27, 12, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (27, 10, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (27, 11, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (27, 13, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (27, 21, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (27, 22, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (27, 23, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (28, 22, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (28, 10, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (28, 14, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (28, 12, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (28, 13, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (28, 23, TRUE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (28, 11, FALSE);
INSERT INTO ATTENDANCE (student_id, schedule_id, present) VALUES (28, 21, FALSE);
