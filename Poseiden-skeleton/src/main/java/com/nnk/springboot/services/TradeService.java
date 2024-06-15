package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    /**

     Retrieves and returns all trades from the repository.
     @return List<Trade> - a list of all trades */
    public List<Trade> displayAllTrades (){
        return tradeRepository.findAll();
    }

    /**

     Saves a new trade to the repository.
     @param trade the trade to be saved
     @return Trade - the saved trade */
    public Trade saveTrade (Trade trade){
        return tradeRepository.save(trade);
    }

    /**

     Updates a trade in the repository based on the provided ID.
     @param id the ID of the trade to update
     @param trade the updated trade object */
    public void updateTrade(Integer id, Trade trade){
        Trade tradeFromDb = tradeRepository.findById(id).orElseThrow(()->new RuntimeException("Trade not found with Id: "+id));
        tradeFromDb.setAccount(trade.getAccount());
        tradeFromDb.setType(trade.getType());
        tradeFromDb.setBuyQuantity(trade.getBuyQuantity());
        saveTrade(tradeFromDb);
    }

    /**

     Deletes a trade from the repository based on the provided ID.
     @param id the ID of the trade to delete */
    public void deleteTrade(Integer id){
        Trade tradeFromDb = tradeRepository.findById(id).orElseThrow(()->new RuntimeException("Trade not found with Id: "+id));
        tradeRepository.delete(tradeFromDb);
    }
}
