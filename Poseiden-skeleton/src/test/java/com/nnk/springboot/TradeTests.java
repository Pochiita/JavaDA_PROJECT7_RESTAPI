package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
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
public class TradeTests {

    @Autowired
    TradeService tradeService;

    @Autowired
    TradeRepository tradeRepository;

    static int tradeId;

    @Test
    @Order(1)

    public  void saveAndUpdateRuleName(){
        Trade trade = new Trade();
        trade.setAccount("test");
        trade.setBuyQuantity(12.0);
        trade.setType("test");
        Trade savedTrade = tradeService.saveTrade(trade);
        Assert.assertEquals(trade,savedTrade);
        savedTrade.setAccount("Updated for test");
        savedTrade.setType("Updated for test");
        savedTrade.setBuyQuantity(21.0);
        Trade afterUpdate = tradeService.saveTrade(savedTrade);
        tradeId = afterUpdate.getId();
        Assert.assertEquals("Updated for test", afterUpdate.getAccount());
        Assert.assertEquals("Updated for test", afterUpdate.getType());

    }
    @Test
    @Order(2)

    public void deleteRuleName(){
        Optional<Trade> rating = tradeRepository.findById(tradeId);
        if(rating.isPresent()) {
            tradeService.deleteTrade(tradeId);
            Assert.assertFalse(tradeRepository.findById(tradeId).isPresent());
        }
    }

}
