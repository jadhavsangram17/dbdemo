/**
 * 
 */
package com.db.dbtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.dbtest.model.Trade;

/**
 * @author Sangram
 */
public interface TradeRepository extends JpaRepository<Trade, Integer>{

	Trade findFirstByTradeIdOrderByTradeVersionDesc(String tradeId);

}
