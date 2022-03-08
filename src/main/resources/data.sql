CREATE TABLE STUDENTS
(
    id      int generated always as identity primary key,
    name    varchar2(50) not null,
    surname varchar2(50) not null,
    points  DOUBLE PRECISION not null CHECK ( points >= 0 )
);

INSERT INTO STUDENTS (name, surname, points)
values ('Silvana', 'Windrunner', 0);
INSERT INTO STUDENTS (name, surname, points)
values ('Illidan', 'Stormrage', 0);
INSERT INTO STUDENTS (name, surname, points)
values ('Keltas', 'Sunstrider', 0);
INSERT INTO STUDENTS (name, surname, points)
values ('Malfurion', 'Stormrage', 0);
INSERT INTO STUDENTS (name, surname, points)
values ('Jaina', 'Proudmoore', 0);