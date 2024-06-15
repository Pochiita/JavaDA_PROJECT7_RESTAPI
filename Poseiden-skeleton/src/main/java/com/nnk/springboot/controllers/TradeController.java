package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TradeController {

    @Autowired
    TradeService tradeService;

    @Autowired
    TradeRepository tradeRepository;

    /**

     This method is used to display the list of trades.
     @param model the Model object to add attributes to
     @return the name of the HTML template to be displayed (trade/list) **/
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        List<Trade> tradesList = tradeService.displayAllTrades();
        model.addAttribute("trades",tradesList);
        return "trade/list";
    }

    /**

     This method is used to display the form to add a new trade.
     @param trade the Trade object to be added
     @return the name of the HTML template to be displayed (trade/add) **/
    @GetMapping("/trade/add")
    public String addUser(Trade trade) {
        return "trade/add";
    }

    /**

     This method is used to validate the trade data before saving it.
     @param trade the Trade object to be validated
     @param result the BindingResult object to hold any validation errors
     @param model the Model object to add attributes to for rendering
     @return the redirection to the trade list page if no validation errors, or the add trade form if there are errors **/
    @PostMapping("/trade/validate")
    public String validate(@Valid @ModelAttribute("trade") Trade trade, BindingResult result, Model model) {
        if(result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("trade", trade); // Re-add the form data to the model in case of validation errors
            return "trade/add";
        }
        tradeService.saveTrade(trade);
        return "redirect:/trade/list";
    }

    /**

     This method is used to display the form to update an existing trade.
     @param id the id of the trade to be updated
     @param model the Model object to add attributes to for rendering
     @return the name of the HTML template to be displayed (trade/update) **/
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeRepository.findById(id).orElseThrow(()->new RuntimeException("Bid list not found with Id: "+id));
        model.addAttribute("trade",trade);
        return "trade/update";
    }

    /**
     This method is used to update an existing trade with new information.
     @param id the id of the trade to be updated
     @param trade the updated Trade object
     @param result the BindingResult object to hold any validation errors
     @param model the Model object to add attributes to for rendering
     @return the redirection to the trade list page if no validation errors, or the update trade form if there are errors **/
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid @ModelAttribute("trade") Trade trade,
                             BindingResult result, Model model) {
        if (result.hasErrors() || result.hasFieldErrors()){
            model.addAttribute("trade",trade);
            return "trade/update";
        }

        tradeService.updateTrade(id,trade);
        return "redirect:/trade/list";
    }

    /**

     This method is used to delete a trade based on its id.
     @param id the id of the trade to be deleted
     @param model the Model object to add attributes to for rendering
     @return the redirection to the trade list page **/
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.deleteTrade(id);
        return "redirect:/trade/list";
    }
}
