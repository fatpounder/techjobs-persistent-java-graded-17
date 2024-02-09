package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity {



    @ManyToOne
    private Employer employer;
    //other side of the relationship of job-employer

    @ManyToMany
    private List<Skill> skills = new ArrayList<>();
    //list of skills for a job
    //many jobs and many skills can know about each other



    public Job() {
    }

    //constructor refactored
    public Job(Employer anEmployer, List<Skill> skills) {
        super();
        this.employer = anEmployer;
        this.skills = skills;
    }


    //new getters and setters for refactoring
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

}