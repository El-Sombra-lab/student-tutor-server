package com.mytutor.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mytutor.server.Models.Application;
import com.mytutor.server.Models.Convenor;
import com.mytutor.server.Models.TeachingAssistant;
import com.mytutor.server.Models.Tutor;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "user_type")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String role;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Application application;// One application is associated with One User

    public User() {
    }
  // Constructor to initialize user properties
    public User(String id, String firstName, String lastName, String password, String role) {
        this.id = id; // student number
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;// Tutor, Convenor, TeachingAssistant, Convenor
    }
  // Constructor to create a user from an existing user
    public User(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    // Getters and setters for common fields


    public String getId() {
        return id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRole() {
        return role;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
