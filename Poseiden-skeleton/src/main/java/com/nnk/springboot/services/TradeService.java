package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    @Autowired
    TradeRepository tradeRepository;

    public List<Trade> displayAllTrades (){
        return tradeRepository.findAll();
    }

    public Trade saveTrade (Trade trade){
        return tradeRepository.save(trade);
    }

    public void updateTrade(Integer id, Trade trade){
        Trade tradeFromDb = tradeRepository.findById(id).orElseThrow(()->new RuntimeException("Trade not found with Id: "+id));
        tradeFromDb.setAccount(trade.getAccount());
        tradeFromDb.setType(trade.getType());
        tradeFromDb.setBuyQuantity(trade.getBuyQuantity());
        saveTrade(tradeFromDb);
    }

    public void deleteTrade(Integer id){
        Trade tradeFromDb = tradeRepository.findById(id).orElseThrow(()->new RuntimeException("Trade not found with Id: "+id));
        tradeRepository.delete(tradeFromDb);
    }
}
