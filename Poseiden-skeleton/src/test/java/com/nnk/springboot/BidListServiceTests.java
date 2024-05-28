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

    BidList bid = new BidList();
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

        bid.setAccount("Updated For Test");
        bid.setType("Updated For Test");
        bid.setBidQuantity(230.0);
        bid_id = saved_bid.getId();
        Assert.assertEquals("Updated For Test", saved_bid.getAccount());

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
