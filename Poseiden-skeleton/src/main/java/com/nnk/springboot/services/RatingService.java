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

    public List<Rating> displayAllRatings (){
        return ratingRepository.findAll();
    }

    public Rating saveRating (Rating rating){
        return ratingRepository.save(rating);
    }

    public void updateCurvePoint(Integer id, Rating rating){
        Rating ratingFromDb = ratingRepository.findById(id).orElseThrow(()->new RuntimeException("Rating not found with Id: "+id));
        ratingFromDb.setMoodysRating(rating.getMoodysRating());
        ratingFromDb.setSandPRating(rating.getSandPRating());
        ratingFromDb.setFitchRating(rating.getFitchRating());
        ratingFromDb.setOrderNumber(rating.getOrderNumber());
        saveRating(ratingFromDb);
    }

    public void deleteRating(Integer id){
        Rating ratingFromDb = ratingRepository.findById(id).orElseThrow(()->new RuntimeException("Rating not found with Id: "+id));
        ratingRepository.delete(ratingFromDb);
    }
}
