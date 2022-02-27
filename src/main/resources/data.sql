CREATE TABLE STUDENTS
(
    id      int generated always as identity primary key ,
    name    varchar2(50) not null ,
    surname varchar2(50) not null ,
    points  int not null CHECK ( points >= 0 )
);

INSERT INTO STUDENTS (name, surname, points) values ('Silvana', 'Windrunner', 6);
INSERT INTO STUDENTS (name, surname, points) values ('Illidan', 'Stormrage', 4);
INSERT INTO STUDENTS (name, surname, points) values ('Keltas', 'Sunstrider', 5);
INSERT INTO STUDENTS (name, surname, points) values ('Malfurion', 'Stormrage', 4);
INSERT INTO STUDENTS (name, surname, points) values ('Jaina', 'Proudmoore', 6);