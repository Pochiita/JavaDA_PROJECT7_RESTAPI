package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    /**

     Displays all the rule names stored in the database.
     @return a list of all rule names */
    public List<RuleName> displayAllRuleNames (){
        return ruleNameRepository.findAll();
    }

    /**

     Saves a new rule name to the database.
     @param ruleName the rule name to be saved
     @return the saved rule name */
    public RuleName saveRuleName (RuleName ruleName){
        return ruleNameRepository.save(ruleName);
    }

    /**

     Updates an existing rule name in the database.
     @param id the id of the rule name to be updated
     @param ruleName the updated rule name object */
    public void updateRuleName(Integer id, RuleName ruleName){
        RuleName ruleNameFromDb = ruleNameRepository.findById(id).orElseThrow(()->new RuntimeException("Rulename not found with Id: "+id));
        ruleNameFromDb.setName(ruleNameFromDb.getName());
        ruleNameFromDb.setDescription(ruleName.getDescription());
        ruleNameFromDb.setJson(ruleNameFromDb.getJson());
        ruleNameFromDb.setTemplate(ruleName.getTemplate());
        ruleNameFromDb.setSqlStr(ruleName.getSqlStr());
        ruleNameFromDb.setSqlPart(ruleName.getSqlPart());
        saveRuleName(ruleName);
    }


    /**

     Deletes a rule name from the database.
     @param id the id of the rule name to be deleted */
    public void deleteRating(Integer id){
        RuleName ruleNameFromDb = ruleNameRepository.findById(id).orElseThrow(()->new RuntimeException("Rating not found with Id: "+id));
        ruleNameRepository.delete(ruleNameFromDb);
    }
}
