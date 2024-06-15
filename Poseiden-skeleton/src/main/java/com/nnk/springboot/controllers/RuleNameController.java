package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RuleNameController {

    @Autowired
    RuleNameService ruleNameService;

    @Autowired
    RuleNameRepository ruleNameRepository;

    /**
     This method is used to display the list of all rule names.
     @param model the Model object to add attributes for rendering
     @return the name of the HTML template to be displayed (ruleName/list) **/
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        List<RuleName> ruleNameList = ruleNameService.displayAllRuleNames();
        model.addAttribute("ruleNames",ruleNameList);
        return "ruleName/list";
    }

    /**

     This method is used to display the form to add a new rule name.
     @param ruleName the RuleName object to be added
     @return the name of the HTML template to be displayed (ruleName/add) **/
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        return "ruleName/add";
    }

    /**

     This method is used to validate the rule name before saving it.
     @param ruleName the RuleName object to be validated
     @param result the BindingResult object to store validation errors
     @param model the Model object to add attributes
     @return the redirection to the ruleName/list page if validation is successful, otherwise returns to the ruleName/add page **/
    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleName ruleName, BindingResult result, Model model) {
        if(result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("ruleName", ruleName); // Re-add the form data to the model in case of validation errors
            return "ruleName/add";
        }
        ruleNameService.saveRuleName(ruleName);
        return "redirect:/ruleName/list";
    }

    /**

     This method is used to display the form to update an existing rule name.
     @param id the id of the rule name to be updated
     @param model the Model object to add attributes
     @return the name of the HTML template to be displayed (ruleName/update) **/
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(()->new RuntimeException("Bid list not found with Id: "+id));
        model.addAttribute("ruleName",ruleName);
        return "ruleName/update";
    }

    /**

     This method is used to update an existing rule name.
     @param id the id of the rule name to be updated
     @param ruleName the RuleName object with updated information
     @param result the BindingResult object to store validation errors
     @param model the Model object to add attributes
     @return the redirection to the ruleName/list page if update is successful, otherwise returns to the ruleName/update page **/
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid @ModelAttribute("ruleName") RuleName ruleName,
                             BindingResult result, Model model) {
        if (result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("ruleName",ruleName);
            return "ruleName/update";
        }

        ruleNameService.updateRuleName(id,ruleName);
        return "redirect:/ruleName/list";
    }

    /**

     This method is used to delete a rule name.
     @param id the id of the rule name to be deleted
     @param model the Model object to add attributes
     @return the redirection to the ruleName/list page after deletion **/
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.deleteRating(id);
        return "redirect:/ruleName/list";
    }
}
