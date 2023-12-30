package com.mytutor.server.Repository;

import com.mytutor.server.Models.Slot;
import com.mytutor.server.Models.Tutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TutorRepository extends CrudRepository<Tutor, String> {
    public List<Tutor> findAllByCourse_Id(String course);

    List<Tutor> findAllBySlotsContainingAndCourseId(Slot slot, String courseId);
}
