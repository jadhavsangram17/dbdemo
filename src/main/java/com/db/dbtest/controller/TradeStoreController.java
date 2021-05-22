/**
 * 
 */
package com.db.dbtest.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.dbtest.exception.LowTradeVersionRejectException;
import com.db.dbtest.model.Trade;
import com.db.dbtest.service.TradeService;

/**
 * @author Sangram
 */
@RestController
public class TradeStoreController {

	private static final Logger logger = LogManager.getLogger(TradeStoreController.class);

	@Autowired
	TradeService tradeService;

	@PostMapping("/trade")
	String addTrade(@RequestBody Trade trade) {
		logger.info("Saving Trade Store Data");
		try {
			return tradeService.addTrade(trade);
		} catch (LowTradeVersionRejectException e) {
			e.printStackTrace();
			return "Exception: Trade version is lower.";
		}
	}

	@GetMapping("/trade")
	List<Trade> trades() {
		logger.info("Fetching Trade Store Data");
		return tradeService.fetchTrades();
	}

	@PostMapping("/updateExpiry")
	String updateTradeExpiry() {
		logger.info("Update Expiry Status In Trade Store Data");
		return tradeService.updateTradeExpiryStatus();

	}
}
