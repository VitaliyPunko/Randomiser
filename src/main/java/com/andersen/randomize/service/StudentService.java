package com.andersen.randomize.service;

import com.andersen.randomize.entity.Student;

public interface StudentService {

    void startGame();

    void askQuestion(Student askStudent, Student answerStudent);
}
