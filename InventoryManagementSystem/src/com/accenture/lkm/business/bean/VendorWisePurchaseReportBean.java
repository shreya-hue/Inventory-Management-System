package com.accenture.lkm.business.bean;


import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class VendorWisePurchaseReportBean {
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fromDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date toDate;
	@NotBlank
	private String vendorName;
	public VendorWisePurchaseReportBean() {
		super();
	}
	
	public VendorWisePurchaseReportBean(Date fromDate,  Date toDate, String vendorName) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.vendorName = vendorName;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}	
	
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	@Override
	public String toString() {
		return "DateWisePurchaseReportBean [fromDate=" + fromDate + ", toDate="
				+ toDate + "]  vendorName [" + vendorName + "]";
	}



}
