/**
 * 
 */
package com.db.dbtest.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.db.dbtest.exception.LowTradeVersionRejectException;
import com.db.dbtest.model.Trade;
import com.db.dbtest.repository.TradeRepository;
import com.db.dbtest.seviceImpl.TradeServiceImpl;

/**
 * @author Sangram
 */
//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TradeServiceTest {

	@InjectMocks
	private TradeServiceImpl tradeService;
	
	@Mock
	TradeRepository tradeRepository;
	
	@Test
	public void whenTradeIsValid_ReturnSuccess() throws LowTradeVersionRejectException, ParseException {
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdDate = new Date();
        Date maturityDate = sdf.parse("2022-05-25");
		
        Trade trade = new Trade();
		trade.setTradeId("T2");
		trade.setTradeVersion(4);
		trade.setBookId("B1");
		trade.setCounterPartyId("CP-2");
		trade.setCreatedDate(createdDate);
		trade.setMaturityDate(maturityDate);
		trade.setExpired("N");
		
		Trade tradeHighVersion = new Trade();
		tradeHighVersion.setTradeId("T2");
		tradeHighVersion.setTradeVersion(3);
		tradeHighVersion.setBookId("B2");
		tradeHighVersion.setCounterPartyId("CP-3");
		tradeHighVersion.setCreatedDate(createdDate);
		tradeHighVersion.setMaturityDate(maturityDate);
		tradeHighVersion.setExpired("N");
		
		Mockito.when(tradeRepository.findFirstByTradeIdOrderByTradeVersionDesc(trade.getTradeId())).thenReturn(tradeHighVersion);
		Mockito.when(tradeRepository.save(trade)).thenReturn(trade);
		
		String message = tradeService.addTrade(trade);
		assertEquals("New Trade record saved successfully.", message);
	}
	
	@Test
	public void whenTradeIsInValid_MaturityDate_ReturnFail() throws LowTradeVersionRejectException, ParseException {
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdDate = new Date();
        Date maturityDate = sdf.parse("2021-05-20");
		
        Trade trade = new Trade();
		trade.setTradeId("T2");
		trade.setTradeVersion(4);
		trade.setBookId("B1");
		trade.setCounterPartyId("CP-2");
		trade.setCreatedDate(createdDate);
		trade.setMaturityDate(maturityDate);
		trade.setExpired("N");
		
		Trade tradeHighVersion = new Trade();
		tradeHighVersion.setTradeId("T2");
		tradeHighVersion.setTradeVersion(3);
		tradeHighVersion.setBookId("B2");
		tradeHighVersion.setCounterPartyId("CP-3");
		tradeHighVersion.setCreatedDate(createdDate);
		tradeHighVersion.setMaturityDate(maturityDate);
		tradeHighVersion.setExpired("N");
		
		Mockito.when(tradeRepository.findFirstByTradeIdOrderByTradeVersionDesc(trade.getTradeId())).thenReturn(tradeHighVersion);
		
		String message = tradeService.addTrade(trade);
		assertEquals("Maturity date is less than todays date.", message);
	}
	
	@Test
	public void whenTradeIsValid_SameTradeVersion_ReturnSuccess() throws LowTradeVersionRejectException, ParseException {
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdDate = new Date();
        Date maturityDate = sdf.parse("2022-05-20");
		
        Trade trade = new Trade();
		trade.setTradeId("T2");
		trade.setTradeVersion(3);
		trade.setBookId("B1");
		trade.setCounterPartyId("CP-4");
		trade.setCreatedDate(createdDate);
		trade.setMaturityDate(maturityDate);
		trade.setExpired("N");
		
		Trade tradeHighVersion = new Trade();
		tradeHighVersion.setTradeId("T2");
		tradeHighVersion.setTradeVersion(3);
		tradeHighVersion.setBookId("B2");
		tradeHighVersion.setCounterPartyId("CP-3");
		tradeHighVersion.setCreatedDate(createdDate);
		tradeHighVersion.setMaturityDate(maturityDate);
		tradeHighVersion.setExpired("N");
		
		Mockito.when(tradeRepository.findFirstByTradeIdOrderByTradeVersionDesc(trade.getTradeId())).thenReturn(tradeHighVersion);
		Mockito.when(tradeRepository.save(trade)).thenReturn(trade);
		
		String message = tradeService.addTrade(trade);
		assertEquals("Trade record updated successfully.", message);
	}
	
	@Test
	public void whenTradeIsInValid_SameTradeVersionMaturity_ReturnFail() throws LowTradeVersionRejectException, ParseException {
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdDate = new Date();
        Date maturityDate = sdf.parse("2021-05-20");
		
        Trade trade = new Trade();
		trade.setTradeId("T2");
		trade.setTradeVersion(3);
		trade.setBookId("B1");
		trade.setCounterPartyId("CP-4");
		trade.setCreatedDate(createdDate);
		trade.setMaturityDate(maturityDate);
		trade.setExpired("N");
		
		Trade tradeHighVersion = new Trade();
		tradeHighVersion.setTradeId("T2");
		tradeHighVersion.setTradeVersion(3);
		tradeHighVersion.setBookId("B2");
		tradeHighVersion.setCounterPartyId("CP-3");
		tradeHighVersion.setCreatedDate(createdDate);
		tradeHighVersion.setMaturityDate(maturityDate);
		tradeHighVersion.setExpired("N");
		
		Mockito.when(tradeRepository.findFirstByTradeIdOrderByTradeVersionDesc(trade.getTradeId())).thenReturn(tradeHighVersion);
		Mockito.when(tradeRepository.save(trade)).thenReturn(trade);
		
		String message = tradeService.addTrade(trade);
		assertEquals("Maturity date is less than todays date.", message);
	}
	
	@Test(expected = LowTradeVersionRejectException.class)
	public void whenTradeIsInValid_LoweTradeVersion_ReturnFail() throws LowTradeVersionRejectException, ParseException {
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdDate = new Date();
        Date maturityDate = sdf.parse("2022-05-20");
		
        Trade trade = new Trade();
		trade.setTradeId("T2");
		trade.setTradeVersion(2);
		trade.setBookId("B1");
		trade.setCounterPartyId("CP-4");
		trade.setCreatedDate(createdDate);
		trade.setMaturityDate(maturityDate);
		trade.setExpired("N");
		
		Trade tradeHighVersion = new Trade();
		tradeHighVersion.setTradeId("T2");
		tradeHighVersion.setTradeVersion(3);
		tradeHighVersion.setBookId("B2");
		tradeHighVersion.setCounterPartyId("CP-3");
		tradeHighVersion.setCreatedDate(createdDate);
		tradeHighVersion.setMaturityDate(maturityDate);
		tradeHighVersion.setExpired("N");
		
		Mockito.when(tradeRepository.findFirstByTradeIdOrderByTradeVersionDesc(trade.getTradeId())).thenReturn(tradeHighVersion);
		
		tradeService.addTrade(trade);
	}
	
	@Test
	public void whenTradeIsValid_NewTrade_ReturnSuccess() throws LowTradeVersionRejectException, ParseException {
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdDate = new Date();
        Date maturityDate = sdf.parse("2022-05-20");
		
        Trade trade = new Trade();
		trade.setTradeId("T3");
		trade.setTradeVersion(1);
		trade.setBookId("B1");
		trade.setCounterPartyId("CP-4");
		trade.setCreatedDate(createdDate);
		trade.setMaturityDate(maturityDate);
		trade.setExpired("N");
		
		Mockito.when(tradeRepository.findFirstByTradeIdOrderByTradeVersionDesc(trade.getTradeId())).thenReturn(null);
		Mockito.when(tradeRepository.save(trade)).thenReturn(trade);
		
		String message = tradeService.addTrade(trade);
		assertEquals("New Trade record saved successfully.", message);
	}
	
	@Test
	public void whenTradeIsInValid_NewTradeMaturity_ReturnFail() throws LowTradeVersionRejectException, ParseException {
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdDate = new Date();
        Date maturityDate = sdf.parse("2021-05-20");
		
        Trade trade = new Trade();
		trade.setTradeId("T3");
		trade.setTradeVersion(1);
		trade.setBookId("B1");
		trade.setCounterPartyId("CP-4");
		trade.setCreatedDate(createdDate);
		trade.setMaturityDate(maturityDate);
		trade.setExpired("N");
		
		Mockito.when(tradeRepository.findFirstByTradeIdOrderByTradeVersionDesc(trade.getTradeId())).thenReturn(null);
		Mockito.when(tradeRepository.save(trade)).thenReturn(trade);
		
		String message = tradeService.addTrade(trade);
		assertEquals("Maturity date is less than todays date.", message);
	}
	
	@Test
	public void whenTradeIsExpired_Maturity_ReturnSuccess() throws LowTradeVersionRejectException, ParseException {
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date createdDate = new Date();
        Date maturityDate = sdf.parse("2021-05-20");
		
        Trade trade = new Trade();
		trade.setTradeId("T3");
		trade.setTradeVersion(1);
		trade.setBookId("B1");
		trade.setCounterPartyId("CP-4");
		trade.setCreatedDate(createdDate);
		trade.setMaturityDate(maturityDate);
		trade.setExpired("N");
		
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(trade);
		
		Mockito.when(tradeService.fetchTrades()).thenReturn(tradeList);
		Mockito.when(tradeRepository.save(trade)).thenReturn(trade);
		
		String message = tradeService.updateTradeExpiryStatus();
		assertEquals("Expiry status updated.", message);
	}
	
}
