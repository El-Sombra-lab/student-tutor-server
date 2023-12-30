package com.mytutor.server.Repository;

import com.mytutor.server.Models.Convenor;
import org.springframework.data.repository.CrudRepository;

public interface ConvenorRepository extends CrudRepository<Convenor, String> {
    public Convenor findConvenorByCourse_Id(String course_id);
}
