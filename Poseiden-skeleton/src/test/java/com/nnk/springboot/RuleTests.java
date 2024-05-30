package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.RuleNameService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class RuleTests {

    @Autowired
    RuleNameService ruleNameService;

    @Autowired
    RuleNameRepository ruleNameRepository;

    static int ruleNameId;

    @Test
    public  void saveAndUpdateRuleName(){

        RuleName ruleName = new RuleName();
        ruleName.setName("test");
        ruleName.setDescription("test");
        ruleName.setJson("test");
        ruleName.setTemplate("test");
        ruleName.setSqlStr("test");
        ruleName.setSqlPart("test");
        RuleName savedRuleName = ruleNameService.saveRuleName(ruleName);
        Assert.assertEquals(ruleName,savedRuleName);

        savedRuleName.setName("Updated for test");
        savedRuleName.setDescription("Updated for test");
        savedRuleName.setJson("Updated for test");
        savedRuleName.setTemplate("Updated for test");
        savedRuleName.setSqlStr("Updated for test");
        savedRuleName.setSqlPart("Updated for test");
        RuleName afterUpdate = ruleNameService.saveRuleName(savedRuleName);
        ruleNameId = afterUpdate.getId();
        Assert.assertEquals("Updated for test", afterUpdate.getName());
        Assert.assertEquals("Updated for test", afterUpdate.getJson());

    }
    @Test
    public void deleteRuleName(){
        Optional<RuleName> rating = ruleNameRepository.findById(ruleNameId);
        if(rating.isPresent()) {
            ruleNameService.deleteRating(ruleNameId);
            Assert.assertFalse(ruleNameRepository.findById(ruleNameId).isPresent());
        }
    }

}
