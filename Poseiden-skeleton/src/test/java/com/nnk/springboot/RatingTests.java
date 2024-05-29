package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class RatingTests {

    @Autowired
    RatingService ratingService;

    @Autowired
    RatingRepository ratingRepository;

    static int ratingId;

    @Test
    public  void saveAndUpdateRating(){

        Rating rating = new Rating();
        rating.setFitchRating("Test");
        rating.setMoodysRating("Test");
        rating.setSandPRating("Test");
        rating.setOrderNumber(12);
        Rating savedRating = ratingService.saveRating(rating);
        Assert.assertEquals(rating,savedRating);

        savedRating.setFitchRating("Updated for test");
        savedRating.setMoodysRating("Updated for test");
        savedRating.setSandPRating("Updated for test");
        savedRating.setOrderNumber(15);
        Rating afterUpdate = ratingService.saveRating(savedRating);
        ratingId = afterUpdate.getId();
        Assert.assertEquals("Updated for test", afterUpdate.getFitchRating());
    }
    @Test
    public void deleteRating(){
        Optional<Rating> rating = ratingRepository.findById(ratingId);
        if(rating.isPresent()) {
            ratingService.deleteRating(ratingId);
            Assert.assertFalse(ratingRepository.findById(ratingId).isPresent());
        }
    }

}
