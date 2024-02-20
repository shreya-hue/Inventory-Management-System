package com.accenture.lkm.dao;


//import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
//import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.entity.PurchaseEntity;

/**
 *  <br/>
 *	CLASS DESCRIPTION:  <br/>
 *	Implementation class for PurchaseDAO to perform the CRUD operation on Purchase table <br/>
 *
 */
@Repository
public class PurchaseDAOImpl implements PurchaseDAO {
	private static Logger LOGGER =  Logger.getLogger(PurchaseDAOImpl.class);
	@Autowired
	private EntityManagerFactory entityManagerFactory;	
	
	/* 
	 * This method inserts the Purchase Data into the Purchase table.
	 * @param purchaseEntity
	 * @return PurchaseEntity
	 */
	@Override
	public PurchaseEntity savePurchaseDetail(PurchaseEntity purchaseEntity) throws Exception {		
		LOGGER.info("Execution Started [savePurchaseDetail()] with purchaseEntity:["+purchaseEntity+"]");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction et = entityManager.getTransaction();
		try {
			et.begin();
			entityManager.persist(purchaseEntity);			
			et.commit();		
			et.begin();
			purchaseEntity.setTransactionId(purchaseEntity.getTransactionId().concat(Integer.toString(purchaseEntity.getPurchaseId())));
			entityManager.merge(purchaseEntity);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			entityManager.close();
		}
		LOGGER.info("Execution over [savePurchaseDetail()] with purchaseEntity:["+purchaseEntity+"]");
		return purchaseEntity;
	}
}
