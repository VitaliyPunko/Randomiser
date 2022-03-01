package com.andersen.randomize.controller;

import com.andersen.randomize.entity.Student;
import com.andersen.randomize.repository.StudentRepository;
import com.andersen.randomize.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @Autowired
    public Controller(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("")
    Iterable<Student> getAll() {
        System.out.println("Before game " + studentRepository.findAll());
        studentService.startGame();
        return studentRepository.findAll();
    }


}
