package com.mytutor.server.Repository;
import com.mytutor.server.Models.Convenor;
import com.mytutor.server.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
        // can define custome query here if needed'
        @Query(value = "SELECT * FROM user WHERE role = :role", nativeQuery = true)
        List<User> findUsersByRole(String role);
}
