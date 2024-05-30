package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.h2.bnf.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    public List<RuleName> displayAllRuleNames (){
        return ruleNameRepository.findAll();
    }

    public RuleName saveRuleName (RuleName ruleName){
        return ruleNameRepository.save(ruleName);
    }

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

    public void deleteRating(Integer id){
        RuleName ruleNameFromDb = ruleNameRepository.findById(id).orElseThrow(()->new RuntimeException("Rating not found with Id: "+id));
        ruleNameRepository.delete(ruleNameFromDb);
    }
}
