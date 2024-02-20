package com.accenture.lkm.dao;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.entity.PurchaseEntity;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * Implementation class for ReportsDAO which deals with all the reports related
 * queries<br/>
 *
 */
@Repository
public class ReportsDAOImpl implements ReportsDAO {
	private static Logger LOGGER = Logger.getLogger(ReportsDAOImpl.class);

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	
	/*
	 * This method fetches purchase details for a given vendor name. If to and from
	 * date is given then it fetches data between the given dates.
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @param vendorName
	 * 
	 * @return List<PurchaseEntity>
	 */
	@Override
	public List<PurchaseBean> getVendorPurchaseReport(Date from, Date to, String vendorName) {
		LOGGER.info("Execution Started [getVendorPurchaseReport()] with from:[" + from + "], to;[" + to
				+ "] vendorName [" + vendorName + "]");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String q = "select m.purchase_id, m.transaction_id, m.vendor_name, m.material_category_id, m.material_type_id,m.brandname, "
				+ "m.unit_id, m.quantity, m.purchase_amount, m.purchase_date, m.status from Purchase as m where m.vendor_name = ?1";
	
		if (from != null) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			String fromDate=sdf1.format(from);
			q = q.concat(" and  m.purchase_date >= '" + fromDate + "'");
		}
		if (to != null) {
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String toDate=sdf2.format(to);
			q = q.concat(" and  m.purchase_date <= '" + toDate + "'");
		}
		Query query = entityManager.createNativeQuery(q);
		query.setParameter(1, vendorName);
		List<Object[]> resultList = query.getResultList();
		List<PurchaseBean> vendorWisePuchaseBeanList = new ArrayList<>();
		for (Object[] row : resultList) {
			PurchaseBean bean=new PurchaseBean();
			bean.setPurchaseId(row[0] != null ? (Integer)row[0] : 0);
			bean.setTransactionId((String)row[1]);
			bean.setVendorName((String)row[2]);
			bean.setMaterialCategoryId((String)row[3]);
			bean.setMaterialTypeId((String)row[4]);
			bean.setBrandName((String)row[5]);
			bean.setUnitId((String)row[6]);
			bean.setQuantity((Integer)row[7]);
			bean.setPurchaseAmount((Double)row[8]);
			Date date = (Date) row[9];		
			//bean.setPurchaseDate(date.toString());
			bean.setPurchaseDate(date);
			bean.setStatus((String)row[10]);
			vendorWisePuchaseBeanList.add(bean);
		}
		LOGGER.info("Execution over [getVendorPurchaseReport()] with from:[" + from + "], to;[" + to + "]");
		return vendorWisePuchaseBeanList;
	}


	
}
