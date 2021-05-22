/**
 * 
 */
package com.db.dbtest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sangram
 *
 */
@Entity
@Table(name = "tradestore")
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name = "tradeid")
	private String tradeId;
	@Column(name = "tradeversion")
	private int tradeVersion;
	@Column(name = "counterpartyid")
	private String counterPartyId;
	@Column(name = "bookid")
	private String bookId;
	@Column(name = "maturitydate")
	private Date maturityDate;
	@Column(name = "createddate")
	private Date createdDate;
	@Column(name = "expired")
	private String expired;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the tradeId
	 */
	public String getTradeId() {
		return tradeId;
	}

	/**
	 * @param tradeId the tradeId to set
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	/**
	 * @return the tradeVersion
	 */
	public int getTradeVersion() {
		return tradeVersion;
	}

	/**
	 * @param tradeVersion the tradeVersion to set
	 */
	public void setTradeVersion(int tradeVersion) {
		this.tradeVersion = tradeVersion;
	}

	/**
	 * @return the counterPartyId
	 */
	public String getCounterPartyId() {
		return counterPartyId;
	}

	/**
	 * @param counterPartyId the counterPartyId to set
	 */
	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	/**
	 * @return the bookId
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the maturityDate
	 */
	public Date getMaturityDate() {
		return maturityDate;
	}

	/**
	 * @param maturityDate the maturityDate to set
	 */
	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the expired
	 */
	public String getExpired() {
		return expired;
	}

	/**
	 * @param expired the expired to set
	 */
	public void setExpired(String expired) {
		this.expired = expired;
	}
}
