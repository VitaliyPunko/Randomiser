package com.andersen.randomize.controller;

import com.andersen.randomize.entity.Student;
import com.andersen.randomize.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("")
    Iterable<Student> getAll() {
        return studentRepository.findAll();
    }
}
