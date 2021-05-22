/**
 * 
 */
package com.db.dbtest.seviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.dbtest.exception.LowTradeVersionRejectException;
import com.db.dbtest.model.Trade;
import com.db.dbtest.repository.TradeRepository;
import com.db.dbtest.service.TradeService;

/**
 * @author Sangram
 */
@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	TradeRepository tradeRepository;
	
	@Override
	public List<Trade> fetchTrades() {
		return tradeRepository.findAll();
	}

	@Override
	public String addTrade(Trade trade) throws LowTradeVersionRejectException {
		Date date = new Date();
		Trade tradeHighVersion = tradeRepository.findFirstByTradeIdOrderByTradeVersionDesc(trade.getTradeId());
		// tradeHighVersion vesrion will be null for new trade id
		if (tradeHighVersion!=null) {
			// Check if new trade version is higher than the present trade version in db.
			if (tradeHighVersion.getTradeVersion()<trade.getTradeVersion()) {
				// Check maturity date is greater than todays date
				if (trade.getMaturityDate().after(date)) {
					tradeRepository.save(trade);
					return "New Trade record saved successfully.";
				} else {
					return "Maturity date is less than todays date.";
				}
			} else if (tradeHighVersion.getTradeVersion()==trade.getTradeVersion()) {
				// If new trade version is same as trade present in db then replace the trade information.
				if (trade.getMaturityDate().after(date)) {
					trade.setId(tradeHighVersion.getId());
					tradeRepository.save(trade);
					return "Trade record updated successfully.";
				} else {
					return "Maturity date is less than todays date.";
				}
			} else if (tradeHighVersion.getTradeVersion()>trade.getTradeVersion()) {
				throw new LowTradeVersionRejectException("Trade vesion is lower.");
			}
		} else {
			// if trade is new then check maturity date and save into db.
			if (trade.getMaturityDate().after(date)) {
				tradeRepository.save(trade);
				return "New Trade record saved successfully.";
			} else {
				return "Maturity date is less than todays date.";
			}
		}
		return null;
	}

	@Override
	public String updateTradeExpiryStatus() {
		List<Trade> tradeList = fetchTrades();
		Date date = new Date();
		for (Trade trade : tradeList) {
			// If trade maturity date is smaller than todays date then update the expire status
			if (trade.getMaturityDate().before(date)) {
				trade.setExpired("Y");
				tradeRepository.save(trade);
			}
		}
		return "Expiry status updated.";
	}
}
