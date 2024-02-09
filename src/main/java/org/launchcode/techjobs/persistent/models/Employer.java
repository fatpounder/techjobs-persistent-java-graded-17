package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Employer extends AbstractEntity {

    @OneToMany()
    @JoinColumn(name = "employer_id")
    //annotations declare the relationship between the data tables
    //name is the column that has the join on it in sql
    private final List<Job> jobs = new ArrayList<>();
    //this list represents the list of all items in a given job
    //One employer can know about many jobs, while a job knows about one employer

    @Size(min = 2, max = 80, message = "Location field must be between 2 and 80 characters.")
    @NotBlank(message = "Location must not be blank.")
    @NotNull
    public String location;
    //added validation annotations for location entries

    public Employer() {
    }
    //no-arg constructor for Hibernate to create an object


    public Employer(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}
