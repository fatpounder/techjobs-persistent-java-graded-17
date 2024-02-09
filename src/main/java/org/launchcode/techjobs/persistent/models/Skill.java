package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {


    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs = new ArrayList<>();
    //this list represents the list of all items in a given job
    //many skills can know about many jobs, while many jobs can know about many skills
    //sql table job_skills will contain the corresponding skills_id and job_id

    @Size(min = 5, max = 200, message = "Description field must be between 5 and 200 characters.")
    @NotBlank(message = "Description must not be blank.")
    @NotNull
    public String description;
    //added validation annotation for constraints on description entries


    public Skill() {

    }
    //no-arg constructor for Hibernate to create an object

    public Skill(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
