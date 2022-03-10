package com.andersen.randomize.controller;

import com.andersen.randomize.entity.Student;
import com.andersen.randomize.repository.StudentRepository;
import com.andersen.randomize.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Controller
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private List<Student> players;
    private double grade;

    @Autowired
    public Controller(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
        studentService.startGame();     //added in first page controller
    }

    @GetMapping("/findAll")
    String findAll(Model model) {
        List<Student> studentList = StreamSupport.stream(studentRepository.findAll().spliterator(), false).collect(Collectors.toList());
        model.addAttribute("studentList", studentList);
        return "list";
    }

    @GetMapping("/start")
    String startGame(Model askModel, Model answerModel) {
        players = studentService.findRandomPlayers();
        if (players == null) {
            return "redirect:/findAll";
        }
        askModel.addAttribute("ask", players.get(0));//вынести?
        answerModel.addAttribute("answer", players.get(1));//вынести?
        return "random";
    }

    @GetMapping("/nextQuestion")
    String nextQuestion() {
        studentService.nextQuestion(players, grade);
        return "redirect:/start";
    }

    @GetMapping("/correct")
    void correctAnswer() {
        LOGGER.debug("The answer is correct");
        grade = 1;

//        studentService.changeGrade(players, grade);
        //return "redirect:/start";
//        nextQuestion(grade);
    }

//    @GetMapping("/correct")
//    String correctAnswer() {
//        LOGGER.debug("The answer is correct");
//        double grade = 1;
//        studentService.changeGrade(players, grade);
//        return "redirect:/start";
//    }

    @PostMapping("/inaccurate")
    String inaccurateAnswer() {
        LOGGER.debug("The answer is correct");
        double grade = 0.5;
        studentService.changeGrade(players, grade);
        return "redirect:/start";
    }

    @GetMapping("/wrong")
    String wrongAnswer() {
        LOGGER.debug("The answer is wrong");
        double grade = 0;
        studentService.changeGrade(players, grade);
        return "redirect:/start";
    }


}
