/**
 * 
 */
package com.db.dbtest.service;

import java.util.List;

import com.db.dbtest.exception.LowTradeVersionRejectException;
import com.db.dbtest.model.Trade;

/**
 * @author Sangram
 */
public interface TradeService {

	List<Trade> fetchTrades();
	String addTrade(Trade trade) throws LowTradeVersionRejectException;
	String updateTradeExpiryStatus();
	
}
