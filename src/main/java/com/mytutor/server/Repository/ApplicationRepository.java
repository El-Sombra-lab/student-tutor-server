package com.mytutor.server.Repository;

import com.mytutor.server.Models.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {
    public Optional<Application> findByUserId(String id);
}
