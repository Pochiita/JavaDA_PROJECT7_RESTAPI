package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CurveController {

    @Autowired
    CurvePointService curvePointService;

    @Autowired
    CurvePointRepository curvePointRepository;


    /**

     This method is used to display the list of curve points.
     @param model the data model to be passed to the HTML template
     @return the name of the HTML template to be displayed (curvePoint/list) */
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        List<CurvePoint> listCurve = curvePointService.displayAllCurvePoint();
        model.addAttribute("curvePoints",listCurve);
        return "curvePoint/list";
    }

    /**

     This method is used to display the form to add a new curve point.
     @param bid the CurvePoint object to be added
     @return the name of the HTML template to be displayed (curvePoint/add) */
    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    /**

     This method is used to validate the data of the curve point form.
     @param curvePoint the CurvePoint object to be validated
     @param result the validation result
     @param model the data model to be passed to the HTML template
     @return the name of the HTML template to be displayed (curvePoint/add) if there are validation errors, or redirect to the list of curve points */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePoint curvePoint, BindingResult result, Model model) {
        if(result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("curvePoint", curvePoint); // Re-add the form data to the model in case of validation errors
            return "curvePoint/add";
        }
        curvePointService.saveCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**

     This method is used to display the form to update a curve point.
     @param id the ID of the curve point to be updated
     @param model the data model to be passed to the HTML template
     @return the name of the HTML template to be displayed (curvePoint/update) */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curve = curvePointRepository.findById(id).orElseThrow(()->new RuntimeException("Bid list not found with Id: "+id));
        model.addAttribute("curvePoint",curve);
        return "curvePoint/update";
    }

    /**

     This method is used to update a curve point.
     @param id the ID of the curve point to be updated
     @param curvePoint the CurvePoint object with updated data
     @param result the validation result
     @param model the data model to be passed to the HTML template
     @return the name of the HTML template to be displayed (curvePoint/update) if there are validation errors, or redirect to the list of curve points */
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("curvePoint") CurvePoint curvePoint,
                             BindingResult result, Model model) {
        if (result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("curvePoint",curvePoint);
            return "curvePoint/update";
        }
        curvePointService.updateCurvePoint(id,curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**

     This method is used to delete a curve point.
     @param id the ID of the curve point to be deleted
     @param model the data model to be passed to the HTML template
     @return redirect to the list of curve points */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        curvePointService.deleteBid(id);
        return "redirect:/curvePoint/list";
    }
}
