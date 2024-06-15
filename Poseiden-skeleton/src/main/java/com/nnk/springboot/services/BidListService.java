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

     Displays all bids in the bid list repository.
     @return List of all bid lists */
    public List<BidList> displayAllBids (){
        return bidListRepository.findAll();
    }

    /**

     Saves a bid to the bid list repository.
     @param bid The bid to save
     @return The saved bid */
    public BidList saveBid (BidList bid){
        return bidListRepository.save(bid);
    }

    /**

     Updates a bid in the bid list repository based on the given ID.
     @param id The ID of the bid to update
     @param bid The updated bid information */
    public void updateBid(Integer id,BidList bid){
        BidList bidFromDb = bidListRepository.findById(id).orElseThrow(()->new RuntimeException("Bid list not found with Id: "+id));
        bidFromDb.setAccount(bid.getAccount());
        bidFromDb.setType(bid.getType());
        bidFromDb.setBidQuantity(bid.getBidQuantity());
        saveBid(bidFromDb);
    }

    /**

     Deletes a bid from the bid list repository based on the given ID.
     @param id The ID of the bid to delete */
    public void deleteBid(Integer id){
        BidList bidFromDb = bidListRepository.findById(id).orElseThrow(()->new RuntimeException("Bid list not found with Id: "+id));
        bidListRepository.delete(bidFromDb);
    }
}
