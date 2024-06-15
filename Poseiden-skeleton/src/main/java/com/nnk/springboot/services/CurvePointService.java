package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    /**

     Retrieves all curve points from the database.
     @return List of CurvePoint objects */
    public List<CurvePoint> displayAllCurvePoint (){
        return curvePointRepository.findAll();
    }

    /**

     Saves a new CurvePoint object to the database.
     @param curvePoint the CurvePoint object to be saved
     @return the saved CurvePoint object */
    public CurvePoint saveCurvePoint (CurvePoint curvePoint){
        return curvePointRepository.save(curvePoint);
    }

    /**

     Updates an existing CurvePoint object in the database.
     @param id the id of the CurvePoint object to be updated
     @param curve the updated CurvePoint object */
    public void updateCurvePoint(Integer id, CurvePoint curve){
        CurvePoint curveFromDb = curvePointRepository.findById(id).orElseThrow(()->new RuntimeException("curvePoint not found with Id: "+id));
        curveFromDb.setTerm(curve.getTerm());
        curveFromDb.setValue(curve.getValue());
        saveCurvePoint(curveFromDb);
    }

    /**

     Deletes a CurvePoint object from the database.
     @param id the id of the CurvePoint object to be deleted */
    public void deleteBid(Integer id){
        CurvePoint curveFromDb = curvePointRepository.findById(id).orElseThrow(()->new RuntimeException("Bid list not found with Id: "+id));
        curvePointRepository.delete(curveFromDb);
    }
}
