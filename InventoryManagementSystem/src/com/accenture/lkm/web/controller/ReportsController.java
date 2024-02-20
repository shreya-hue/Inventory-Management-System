package com.accenture.lkm.web.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.business.bean.VendorBean;

import com.accenture.lkm.business.bean.VendorWisePurchaseReportBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.accenture.lkm.services.ReportsService;
import com.accenture.lkm.web.client.MaterialCategoryConsumer;
import com.accenture.lkm.web.client.MaterialTypeConsumer;
import com.accenture.lkm.web.client.UnitServiceConsumer;
import com.accenture.lkm.web.client.VendorServiceConsumer;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * A controller class for handling the request coming from
 * DateWisePurchaseReport.jsp
 *
 */
@Controller
public class ReportsController {

	private static Logger LOGGER = Logger.getLogger(ReportsController.class);

	@Autowired
	private ReportsService reportsService;

	@Autowired
	private MaterialCategoryConsumer materialCategoryConsumer;

	@Autowired
	private UnitServiceConsumer unitServiceConsumer;

	@Autowired
	private MaterialTypeConsumer materialTypeConsumer;

	@Autowired
	private VendorServiceConsumer vendorServiceConsumer;
	
	List <PurchaseBean> purchaseIdList;


		
	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method set the VendorWisePurchaseReportBean into the model attribute and
	 * redirect to VendorWisePurchaseReport.jsp.
	 * 
	 * @return
	 */
	@RequestMapping("loadVendorWisePurchaseReportPage.html")
	public ModelAndView showVendorWisePurchaseReportPage(HttpSession session) {
		LOGGER.info("Execution Started [ showVendorWisePurchaseReportPage()]");
		session.setAttribute("vendorWisePurchaseBeanList", new ArrayList<PurchaseBean>());
		session.setAttribute("vendorBean", null);
		VendorWisePurchaseReportBean vendorWisePurchaseReportBean = new VendorWisePurchaseReportBean();
		session.setAttribute("vendorWisePurchaseReportBean", vendorWisePurchaseReportBean);
		LOGGER.info("Execution over [ showVendorWisePurchaseReportPage()]");
		return new ModelAndView("VendorWisePurchaseReport", "vendorWisePurchaseReportBean", vendorWisePurchaseReportBean);
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method returns the vendor list to be populated on the
	 * DateWisePurchaseReport.jsp getVendorBeanList method of VendorServiceConsumer
	 * is called to get the vendor list.
	 * 
	 * @return vendorList - List of vendor values
	 * @throws MicroServiceException 
	 */
	@ModelAttribute("vendorList")
	public List<VendorBean> generateVendorList() throws MicroServiceException {
		LOGGER.info("Execution Started [ generateVendorList()]");
		List<VendorBean> vendorList = vendorServiceConsumer.getVendorBeanList();
		LOGGER.info("Execution over [ generateVendorList()]");
		return vendorList;
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to fetch the purchase details for a given vendor and
	 * between the two given dates and redirect to the VendorWisePurchaseReport.jsp
	 * to display the list
	 * 
	 * @param vendorWisePurchaseReportBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getVendorWisePurchaseDetail.html", method = RequestMethod.POST)
	public ModelAndView getVendorWisePurchaseDetail(
			@ModelAttribute("vendorWisePurchaseReportBean") @Valid VendorWisePurchaseReportBean vendorWisePurchaseReportBean,
			BindingResult result, HttpSession session) throws Exception {
		LOGGER.info("Execution Started [getDateWisePurchaseDetails()] with vendorWisePuchaseReportBean:["
				+ vendorWisePurchaseReportBean + "]");
		ModelAndView view = new ModelAndView("VendorWisePurchaseReport");
		if (!result.hasErrors()) {
			List<PurchaseBean> vendorWisePurchaseBeanList = reportsService.getVendorWisePurchaseDetails(
					vendorWisePurchaseReportBean.getFromDate(), vendorWisePurchaseReportBean.getToDate(),
					vendorWisePurchaseReportBean.getVendorName());
			if (vendorWisePurchaseBeanList != null && vendorWisePurchaseBeanList.size() > 0) {
				Map<String, String> categoryMap = materialCategoryConsumer.getCategoryMap();
				Map<String, String> unitMap = unitServiceConsumer.getUnitMap();
				Map<String, String> typeMap = materialTypeConsumer.getCategoryTypeMap();
				for (PurchaseBean bean : vendorWisePurchaseBeanList) {
					bean.setMaterialCategoryName(categoryMap.get(bean.getMaterialCategoryId()));
					bean.setMaterialTypeName(typeMap.get(bean.getMaterialTypeId()));
					bean.setMaterialUnitName(unitMap.get(bean.getUnitId()));
				}
				view.addObject("vendorWisePurchaseBeanList", vendorWisePurchaseBeanList);
				session.setAttribute("vendorWisePurchaseBeanList", vendorWisePurchaseBeanList);
				session.setAttribute("vendorWisePurchaseReportBean", vendorWisePurchaseReportBean);
			} else {
				session.setAttribute("vendorWisePurchaseBeanList", new ArrayList<PurchaseBean>());
				view.addObject("message", "No records found for vendor name " +vendorWisePurchaseReportBean.getVendorName() + ".");
			}
			Map<String, VendorBean> vendorMap = vendorServiceConsumer.getVendorMap();

			view.addObject("vendorBean", vendorMap.get(vendorWisePurchaseReportBean.getVendorName()));
			session.setAttribute("vendorBean", vendorMap.get(vendorWisePurchaseReportBean.getVendorName()));
		}

		LOGGER.info("Execution over [getDateWisePurchaseDetails()] with vendorWisePuchaseReportBean:["
				+ vendorWisePurchaseReportBean + "]");
		return view;

	}
	
	/**
	 * This method will be called when user used pagination buttons to see next or previous records on VendorWisePurchaseReport.jsp.
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getVendorWisePurchaseDetail.html", method = RequestMethod.GET)
	public ModelAndView returnVendorWisePurchaseReport(HttpSession session) throws Exception {
		LOGGER.info("Execution Started [returnVendorWisePurchaseReport()]");
		ModelAndView view = new ModelAndView("VendorWisePurchaseReport", "vendorWisePurchaseReportBean", session.getAttribute("vendorWisePurchaseReportBean"));		
		LOGGER.info("Execution over [returnVendorWisePurchaseReport()]");
		return view;

	}
	


}
