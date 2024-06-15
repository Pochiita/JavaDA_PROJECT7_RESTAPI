package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    /**

     This method returns a list of all ratings in the database.
     @return List of Rating objects */
    public List<Rating> displayAllRatings (){
        return ratingRepository.findAll();
    }

    /**

     This method saves a new rating to the database.
     @param rating The Rating object to be saved
     @return The saved Rating object */
    public Rating saveRating (Rating rating){
        return ratingRepository.save(rating);
    }

    /**

     This method updates the curve point for a specific rating in the database.
     @param id The ID of the rating to be updated
     @param rating The updated Rating object */
    public void updateCurvePoint(Integer id, Rating rating){
        Rating ratingFromDb = ratingRepository.findById(id).orElseThrow(()->new RuntimeException("Rating not found with Id: "+id));
        ratingFromDb.setMoodysRating(rating.getMoodysRating());
        ratingFromDb.setSandPRating(rating.getSandPRating());
        ratingFromDb.setFitchRating(rating.getFitchRating());
        ratingFromDb.setOrderNumber(rating.getOrderNumber());
        saveRating(ratingFromDb);
    }

    /**

     This method deletes a rating from the database.
     @param id The ID of the rating to be deleted */
    public void deleteRating(Integer id){
        Rating ratingFromDb = ratingRepository.findById(id).orElseThrow(()->new RuntimeException("Rating not found with Id: "+id));
        ratingRepository.delete(ratingFromDb);
    }
}
