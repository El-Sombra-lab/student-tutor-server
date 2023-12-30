package com.mytutor.server.Repository;

import com.mytutor.server.Models.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,String> {
}
