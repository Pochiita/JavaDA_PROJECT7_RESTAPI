package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
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

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        List<RuleName> ruleNameList = ruleNameService.displayAllRuleNames();
        model.addAttribute("ruleNames",ruleNameList);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleName ruleName, BindingResult result, Model model) {
        if(result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("ruleName", ruleName); // Re-add the form data to the model in case of validation errors
            return "ruleName/add";
        }
        ruleNameService.saveRuleName(ruleName);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(()->new RuntimeException("Bid list not found with Id: "+id));
        model.addAttribute("ruleName",ruleName);
        return "ruleName/update";
    }

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

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.deleteRating(id);
        return "redirect:/ruleName/list";
    }
}
