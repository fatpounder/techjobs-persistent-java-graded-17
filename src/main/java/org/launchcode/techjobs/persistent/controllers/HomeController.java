package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {


    @Autowired
    EmployerRepository employerRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    JobRepository jobRepository;



    @RequestMapping("/")
    public String index(Model model) {
        //use findAll() here for each of the repositories that are autowired so all data can go to main page
        model.addAttribute("title", "MyJobs");
        model.addAttribute("jobs", jobRepository.findAll());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());


        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	    model.addAttribute("title", "Add Job");
        //use findAll() here for each of the repositories on the Add Jobs page
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam(required = false) List<Integer> skills) {
        //new requestparam added to pass in skills list
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
            //checking for error first, then move on
        } else {
            Optional<Employer> result = employerRepository.findById(employerId);
            if (result.isPresent()) {
                Employer employer = result.get();
                newJob.setEmployer(employer);
                //get the employer results and set the newJob with it

            }
            //get skills data from list of ids using findAllById, then use setSkills()
            List<Skill> skillObjects = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjects);
            jobRepository.save(newJob);
            //select the employer object that has been chosen to be affiliated with the new job

            return "redirect:";
        }
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        //viewing job individually by its JobId
        Job job = jobRepository.findById(jobId).orElse(new Job());
        model.addAttribute("title", "Job");
        model.addAttribute("job", job);
            return "view";
    }

}
