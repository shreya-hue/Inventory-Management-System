package com.accenture.lkm.test;

//import java.util.ArrayList;
//
import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.web.servlet.MockMvc;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.accenture.lkm.entity.PurchaseEntity;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.result.view.ViewResultMatchers;
//import org.springframework.http.MediaType;
//
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.accenture.lkm.services.PurchaseService;
//import com.accenture.lkm.web.controller.PurchaseEntryController;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebMvcTest(PurchaseEntryController.class)
public class PurchaseEntryControllerTest {
	
//	private static final Logger logger = LoggerFactory.getLogger(PurchaseEntryControllerTest.class);
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private PurchaseService purchaseServiceMock;
//
	@Test
	public void testPurchaseEntry() throws Exception{
		//implementation goes here
		//assertTrue(PurchaseEntryController.getPurchasDetails.contains())
//		 MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//
//		    PurchaseService purchaseServiceMock = Mockito.mock(PurchaseService.class);
//		    Mockito.when(purchaseServiceMock.addPurchaseDetails()).thenReturn(new ArrayList<>());

		    // Call the corresponding method on the PurchaseEntryController class.
//		    mockMvc.perform(get("/purchaseEntry.html"))
//		            .andExpect(status().isOk())
//		            .andExpect(view().name("PurchaseEntry"));
	}

	@Test
	public void testGenerateVendorList() {
		//implementation goes here
	}

	@Test
	public void testGenerateUnitAndTypeList() {
		//implementation goes here
	}

	@Test
	public void testGenerateCategoryList() {
		//implementation goes here
	}

	@Test
	public void testAddPurchaseDetail() {
		//implementation goes here
	}
}
