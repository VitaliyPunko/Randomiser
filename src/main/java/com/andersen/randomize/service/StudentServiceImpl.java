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

    @Autowired
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void startGame() {
        LOGGER.debug("The game started");
        Iterable<Student> studentIterable = studentRepository.findAll();
        List<Student> askList = StreamSupport.stream(studentIterable.spliterator(), false).collect(Collectors.toList());
        List<Student> answerList = new ArrayList<>(askList);
        findRandomPlayers(askList, answerList);
    }

    @Override
    public void askQuestion(Student askStudent, Student answerStudent) {
        LOGGER.debug("Student {} {} asks student {} {}", askStudent.getName(), askStudent.getSurname(), answerStudent.getName(), answerStudent.getSurname());
        //передать сюда значение из фронта с оценкой за вопрос:
        int grade = 1;
        askStudent.setPoints(askStudent.getPoints() + grade);       //increase points in askStudent
        answerStudent.setPoints(answerStudent.getPoints() + grade); //increase points in answerStudent
    }

    private void findRandomPlayers(List<Student> askList, List<Student> answerList) {
        //что делать если количество не четное и останется 1 студент без ответа или вопроса?
        while (!askList.isEmpty() && !answerList.isEmpty()) {
            int askNumber = (int) (Math.random() * askList.size()); //number of ask student from 0 to askList size not inclusiv
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
            askQuestion(askList.get(askNumber), answerList.get(answerNumber));
            askList.remove(askNumber);
            answerList.remove(answerNumber);
        }
    }

}
