package com.andersen.randomize.service;

import com.andersen.randomize.entity.Student;
import com.andersen.randomize.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private List<Student> askList;
    private List<Student> answerList;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void startGame() {
        LOGGER.debug("The game started");
        Iterable<Student> studentIterable = studentRepository.findAll();
        askList = StreamSupport.stream(studentIterable.spliterator(), false).collect(Collectors.toList());
        answerList = new ArrayList<>(askList);
    }

    @Override
    public List<Student> findRandomPlayers() {
        if (askList.size() == 0 && answerList.size() == 0) {
            return null;
        }
        int askNumber = (int) (Math.random() * askList.size()); //number of ask student from 0 to askList size not inclusive
        int answerNumber = (int) (Math.random() * answerList.size());     //number of ask student from 0 to askList size not inclusive
        Student ask = askList.get(askNumber);
        Student answer = answerList.get(answerNumber);
        while (ask.getId() == answer.getId()) {                     //проверяем не совпадают ли студенты
            if (askList.size() == 1 && answerList.size() == 1) {  //если размеры обоих листов = 1, то осталось по одному студенту
                break;
            }
            answerNumber = (int) (Math.random() * answerList.size());
            answer = answerList.get(answerNumber);
        }
        List<Student> outputTwoStudents = new ArrayList<>(); //return two students to front
        outputTwoStudents.add(ask);
        outputTwoStudents.add(answer);
        LOGGER.debug("Student {} asks student {}", ask.getFullName(), answer.getFullName());

        askList.remove(askNumber);                  //delete them from parameters
        answerList.remove(answerNumber);

        return outputTwoStudents;
    }

    @Override
    public void changeGrade(List<Student> players, double grade) {
        addOnePointToAsk(players.get(0));
        players.get(1).setPoints(players.get(1).getPoints() + grade); //increase points in answerStudent
    }

    @Override
    public void nextQuestion(List<Student> players, double grade) {
        changeGrade(players, grade);
        studentRepository.saveAll(players);
    }

    /*
        all students get one point for question
         */
    private void addOnePointToAsk(Student askStudent) {
        askStudent.setPoints(askStudent.getPoints() + 1);
    }

}
