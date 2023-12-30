package com.mytutor.server.Repository;

import com.mytutor.server.Models.Course;
import com.mytutor.server.Models.Slot;
import com.mytutor.server.Models.Tutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface SlotRepository extends CrudRepository<Slot, Integer> {

    public List<Slot> findAllByCourseAndTypeAndAllocationsGreaterThan(Course course, String Type, Integer allocationsMinLimit);

    public List<Slot> findAllByCourseIdAndType(String courseId, String type);

    public List<Slot> findAllByTutorsContaining(Tutor tutor);
}
