package org.launchcode.techjobs.persistent.models.data;


import org.launchcode.techjobs.persistent.models.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {
}
//maps the Skill class to the techjobs database using the CrudRepository