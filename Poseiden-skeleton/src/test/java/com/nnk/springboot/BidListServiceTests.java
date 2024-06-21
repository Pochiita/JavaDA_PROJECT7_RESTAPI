package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
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
public class BidListServiceTests {
    @Autowired
    BidListService bidListService;

    @Autowired
    BidListRepository bidListRepository;

    private static int bid_id;

    @Test
    @Order(1)
    public  void saveAndUpdateBidList(){
        // given
        BidList bid = new BidList();
        bid.setAccount("BidList");
        bid.setBidQuantity(229.0);
        bid.setType("test");
        BidList saved_bid = bidListService.saveBid(bid);
        Assert.assertEquals(bid, saved_bid);

        saved_bid.setAccount("Updated For Test");
        saved_bid.setType("Updated For Test");
        saved_bid.setBidQuantity(230.0);
        BidList afterUpdate = bidListService.saveBid(saved_bid);
        bid_id = afterUpdate.getId(); // Update the bid_id variable after saving
        System.out.println("Hello2");

        System.out.println(bid_id);
        Assert.assertEquals("Updated For Test", afterUpdate.getAccount());

    }
    @Test
    @Order(2)
    public void deleteBidList(){
        Optional<BidList> bid = bidListRepository.findById(bid_id);
        System.out.println("Hello");

        System.out.println(bid_id);
        if(bid.isPresent()) {
            bidListService.deleteBid(bid_id);
            Assert.assertFalse(bidListRepository.findById(bid_id).isPresent());
        }
    }

}
