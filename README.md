# StudentHub
Программа с графическим интерфейсом позволяющая импортировать список студентов,
вести учет посещаемости занятий, защиты практических работ и подсчет статистики
по успеваемости студента, группы по дисциплине.

## Схема базы данных
```mermaid
erDiagram
    STUDENTS {
        INT student_id PK
        VARCHAR full_name
        INT group_id FK
    }

    GROUPS {
        INT group_id PK
        VARCHAR group_name
    }

    DISCIPLINES {
        INT discipline_id PK
        VARCHAR discipline_name
    }

    SCHEDULE {
        INT schedule_id PK
        INT group_id FK
        INT discipline_id FK
        DATE date
    }

    ATTENDANCE {
        INT attendance_id PK
        INT student_id FK
        INT schedule_id FK
        BOOLEAN present
    }

    PRACTICALWORKS {
        INT practical_work_id PK
        INT student_id FK
        INT discipline_id FK
        DATE date
        INT grade
    }

    STUDENTS ||--|| GROUPS : belongs_to
    STUDENTS ||--|| ATTENDANCE : has
    STUDENTS ||--|| PRACTICALWORKS : has
    GROUPS ||--|| SCHEDULE : has
    DISCIPLINES ||--|| SCHEDULE : has
    SCHEDULE ||--|| ATTENDANCE : has
```

