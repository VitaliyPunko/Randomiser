package com.andersen.randomize.repository;

import com.andersen.randomize.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
