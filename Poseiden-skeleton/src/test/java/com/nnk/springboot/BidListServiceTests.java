package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class BidListServiceTests {

    @Autowired
    BidListService bidListService;

    @Autowired
    BidListRepository bidListRepository;

    static int bid_id;

    @Test
    public  void saveAndUpdateBidList(){
        // given
        BidList bid = new BidList();
        bid.setAccount("BidList");
        bid.setBidQuantity(229.0);
        bid.setType("test");
        BidList saved_bid = bidListService.saveBid(bid);
        Assert.assertEquals(bid,saved_bid);

        saved_bid.setAccount("Updated For Test");
        saved_bid.setType("Updated For Test");
        saved_bid.setBidQuantity(230.0);
        BidList afterUpdate = bidListService.saveBid(saved_bid);
        bid_id = afterUpdate.getId();
        Assert.assertEquals("Updated For Test", afterUpdate.getAccount());

    }
    @Test
    public void deleteBidList(){
        Optional<BidList> bid = bidListRepository.findById(bid_id);
        if(bid.isPresent()) {
            bidListService.deleteBid(bid_id);
            Assert.assertFalse(bidListRepository.findById(bid_id).isPresent());
        }
    }

}
