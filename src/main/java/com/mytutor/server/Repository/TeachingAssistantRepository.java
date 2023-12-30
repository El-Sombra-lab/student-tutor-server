package com.mytutor.server.Repository;
import com.mytutor.server.Models.TeachingAssistant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachingAssistantRepository extends JpaRepository<TeachingAssistant, String> {

}
