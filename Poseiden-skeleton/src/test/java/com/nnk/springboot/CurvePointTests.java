package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CurvePointTests {

    @Autowired
    CurvePointService curvePointService;

    @Autowired
    CurvePointRepository curvePointRepository;

    static int curveId;

    @Test
    @Order(1)

    public  void saveAndUpdateCurve(){

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setTerm(12.0);
        curvePoint.setValue(13.0);
        CurvePoint savedCurvePoint = curvePointService.saveCurvePoint(curvePoint);
        Assert.assertEquals(curvePoint,savedCurvePoint);

        savedCurvePoint.setValue(15.0);
        savedCurvePoint.setTerm(15.0);
        CurvePoint afterUpdate = curvePointService.saveCurvePoint(savedCurvePoint);
        curveId = afterUpdate.getId();
        Assert.assertEquals(15.0, afterUpdate.getTerm(), 0.001);

    }
    @Test
    @Order(2)

    public void deleteCurve(){
        Optional<CurvePoint> bid = curvePointRepository.findById(curveId);
        if(bid.isPresent()) {
            curvePointService.deleteBid(curveId);
            Assert.assertFalse(curvePointRepository.findById(curveId).isPresent());
        }
    }

}
