package com.andersen.randomize.service;

import com.andersen.randomize.entity.Student;

import java.util.List;

public interface StudentService {

    void startGame();

    List<Student> findRandomPlayers();

    void changeGrade(List<Student> players, double grade);

    void nextQuestion(List<Student> players, double grade);
}
