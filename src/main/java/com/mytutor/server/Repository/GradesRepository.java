package com.mytutor.server.Repository;

import com.mytutor.server.Models.Grades;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GradesRepository extends CrudRepository<Grades, Integer> {
    List<Grades> findByCourse(String course_code);
}

