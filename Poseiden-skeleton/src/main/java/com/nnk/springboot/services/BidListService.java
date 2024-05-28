package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BidListService {

    @Autowired
    BidListRepository bidListRepository;

    /**
     * Get a list of all bids in dv
     *
     * @return List of all available bids in db
     */
    public List<BidList> displayAllBids (){
        return bidListRepository.findAll();
    }

    public BidList saveBid (BidList bid){
        return bidListRepository.save(bid);
    }

    public void updateBid(Integer id,BidList bid){
        BidList bidFromDb = bidListRepository.findById(id).orElseThrow(()->new RuntimeException("Bid list not found with Id: "+id));
        bidFromDb.setAccount(bid.getAccount());
        bidFromDb.setType(bid.getType());
        bidFromDb.setBidQuantity(bid.getBidQuantity());
        saveBid(bidFromDb);
    }

    public void deleteBid(Integer id){
        BidList bidFromDb = bidListRepository.findById(id).orElseThrow(()->new RuntimeException("Bid list not found with Id: "+id));
        bidListRepository.delete(bidFromDb);
    }
}
