package com.accenture.lkm.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.lkm.dao.PurchaseDAO;
import com.accenture.lkm.entity.PurchaseEntity;



public class PurchaseDAOTest {
	
	@Autowired
	PurchaseDAO purchaseDAO;

	@Test
	public void testSavePurchaseDetail() throws Exception {
	
//	    PurchaseEntity purchaseToSave = new PurchaseEntity();
//	    purchaseToSave.setPurchaseId(1);
//	    //purchaseToSave.setPurchaseDate(2020-03-26);
//	    purchaseToSave.setPurchaseDate(new Date());
//	    purchaseToSave.setPurchaseAmount(1100.00);
//
//	    PurchaseEntity purchaseSaved = purchaseDAO.savePurchaseDetail(purchaseToSave);
//	    assertNotNull(purchaseSaved);
//	    
//	    Date purchaseDate = purchaseSaved.getPurchaseDate();
//
//	    assertEquals(purchaseToSave.getPurchaseId(), purchaseSaved.getPurchaseId());
//	    //assertEquals(purchaseToSave.getPurchaseDate(), purchaseSaved.getPurchaseDate());
//	    assertEquals(purchaseToSave.getPurchaseDate(), purchaseDate);
//	    assertEquals(purchaseToSave.getPurchaseAmount(), purchaseSaved.getPurchaseAmount());
	}
}


