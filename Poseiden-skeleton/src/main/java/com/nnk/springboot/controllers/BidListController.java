package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@Controller
public class BidListController {

    @Autowired
    BidListService bidListService;

    @Autowired
    BidListRepository bidListRepository;

    /**
     * This method retrieves all the bids from the database and displays them in a table
     *
     * @param model The model object to which attributes are added for rendering data in the view
     * @return The template name "bidList/list" to display all the bids in a table
     */
    @RequestMapping("/bidList/list")
    public String home(Model model) {
        List<BidList> bidLists = bidListService.displayAllBids();
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user",currentUser);
        model.addAttribute("bidLists",bidLists);
        return "bidList/list";
    }

    /**
     * This method is used to display the form to add a new bid.
     * @param bid the BidList object to be added
     * @return the name of the HTML template to be displayed (bidList/add)
     **/
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid,Model model) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidList bidList, BindingResult result, Model model) {
        if(result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("bidList", bidList); // Re-add the form data to the model in case of validation errors
            return "bidList/add";
        }
        bidListService.saveBid(bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        BidList bid = bidListRepository.findById(id).orElseThrow(()->new RuntimeException("Bid list not found with Id: "+id));
        model.addAttribute("bidList",bid);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("bidList") BidList bidList,
                             BindingResult result, Model model) {
        if (result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("bidList",bidList);
            return "bidList/update";
        }

        bidListService.updateBid(id,bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteBid(id);
        return "redirect:/bidList/list";
    }
}
