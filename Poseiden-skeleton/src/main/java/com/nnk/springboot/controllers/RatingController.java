package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RatingController {

    @Autowired
    RatingService ratingService;

    @Autowired
    RatingRepository ratingRepository;

    /**

     This method is used to display the list of all ratings.
     @param model the Model object to add attributes to
     @return the name of the HTML template to be displayed (rating/list) **/
    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        List<Rating> listRating = ratingService.displayAllRatings();
        model.addAttribute("ratings",listRating);
        return "rating/list";
    }

    /**

     This method is used to display the form to add a new rating.
     @param rating the Rating object to be added
     @return the name of the HTML template to be displayed (rating/add) **/
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    /**

     This method is used to validate the rating form data.
     @param rating the Rating object to be validated
     @param result the BindingResult object to handle validation errors
     @param model the Model object to add attributes to
     @return the name of the HTML template to be displayed (rating/add or redirect to rating/list) **/
    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute("rating") Rating rating, BindingResult result, Model model) {
        if(result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("rating", rating); // Re-add the form data to the model in case of validation errors
            return "rating/add";
        }
        ratingService.saveRating(rating);
        return "redirect:/rating/list";
    }

    /**

     This method is used to display the form to update an existing rating.
     @param id the id of the rating to be updated
     @param model the Model object to add attributes to
     @return the name of the HTML template to be displayed (rating/update) **/
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingRepository.findById(id).orElseThrow(()->new RuntimeException("Rating not found with Id: "+id));
        model.addAttribute("rating",rating);
        return "rating/update";
    }

    /**

     This method is used to update an existing rating.
     @param id the id of the rating to be updated
     @param rating the Rating object to be updated
     @param result the BindingResult object to handle validation errors
     @param model the Model object to add attributes to
     @return the name of the HTML template to be displayed (rating/update or redirect to rating/list) **/
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute("rating") Rating rating,
                             BindingResult result, Model model) {
        if (result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("rating",rating);
            return "rating/update";
        }
        ratingService.updateCurvePoint(id,rating);
        return "redirect:/rating/list";
    }

    /**

     This method is used to delete a rating based on its id.
     @param id the id of the rating to be deleted
     @param model the Model object to add attributes to
     @return the name of the HTML template to be displayed (redirect to rating/list) **/
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.deleteRating(id);
        return "redirect:/rating/list";
    }
}
