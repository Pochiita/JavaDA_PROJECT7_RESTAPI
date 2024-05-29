package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    public List<CurvePoint> displayAllCurvePoint (){
        return curvePointRepository.findAll();
    }

    public CurvePoint saveCurvePoint (CurvePoint curvePoint){
        return curvePointRepository.save(curvePoint);
    }

    public void updateCurvePoint(Integer id, CurvePoint curve){
        CurvePoint curveFromDb = curvePointRepository.findById(id).orElseThrow(()->new RuntimeException("curvePoint not found with Id: "+id));
        curveFromDb.setTerm(curve.getTerm());
        curveFromDb.setValue(curve.getValue());
        saveCurvePoint(curveFromDb);
    }

    public void deleteBid(Integer id){
        CurvePoint curveFromDb = curvePointRepository.findById(id).orElseThrow(()->new RuntimeException("Bid list not found with Id: "+id));
        curvePointRepository.delete(curveFromDb);
    }
}
