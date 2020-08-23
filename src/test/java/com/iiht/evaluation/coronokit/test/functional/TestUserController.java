package com.iiht.evaluation.coronokit.test.functional;

import static com.iiht.evaluation.coronokit.test.util.TestUtils.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iiht.evaluation.coronokit.controller.UserController;
import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;

public class TestUserController {

	private UserController servlet;
	@Mock
    private HttpServletRequest request;
	@Mock
    private HttpServletResponse response;
	@Mock
	private HttpSession session;
	@Mock
    private RequestDispatcher rd;
	
	@Captor
	ArgumentCaptor<HttpServletRequest> requestCaptor;
    
	@Captor
	ArgumentCaptor<HttpServletResponse> responseCaptor;
	
    private ProductMasterDao productMasterDao;
    private KitDao kitDao;

    @Before
    public void setUp() {
        this.servlet = new UserController();
        productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
        this.servlet.setProductMasterDao(productMasterDao);
        kitDao = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
        this.servlet.setKitDAO(kitDao);
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testShowNewUserForm() throws ServletException, IOException {
    	when(this.request.getParameter("action")).thenReturn("newuser");
    	when(this.request.getRequestDispatcher("newuser.jsp")).thenReturn(rd);
    	when(this.request.getSession()).thenReturn(session);
    	try {
	    	 this.servlet.doPost(request, response);
	    	 verify(rd).forward(requestCaptor.capture(), responseCaptor.capture());
	    	 yakshaAssert(currentTest(), 
	    			  "true", 
	    			 businessTestFile);
    	}catch(Exception ex) {
    		yakshaAssert(currentTest(), 
       			 "false", 
       			 businessTestFile);
    	}
    }
    
    @Test
    public void testAddNewUser() throws ServletException, IOException {
    	when(this.request.getParameter("action")).thenReturn("insertuser");
    	when(this.request.getParameter("pname")).thenReturn("First");
    	when(this.request.getParameter("pemail")).thenReturn("first@mail.com");
    	when(this.request.getParameter("pcontact")).thenReturn("1234567890");
    	when(this.request.getRequestDispatcher("showproductstoadd.jsp")).thenReturn(rd);
    	when(this.request.getSession()).thenReturn(session);
    	try {
	    	 this.servlet.doPost(request, response);
	    	 verify(rd).forward(requestCaptor.capture(), responseCaptor.capture());
	    	 yakshaAssert(currentTest(), 
	    			  "true", 
	    			 businessTestFile);
    	}catch(Exception ex) {
    		yakshaAssert(currentTest(), 
       			 "false", 
       			 businessTestFile);
    	}
    }
    @Test
    public void testShowProducts() throws ServletException, IOException {
    	when(this.request.getParameter("action")).thenReturn("showproducts");
    	when(this.request.getRequestDispatcher("showproductstoadd.jsp")).thenReturn(rd);
    	when(this.request.getSession()).thenReturn(session);
    	try {
	    	 this.servlet.doPost(request, response);
	    	 verify(rd).forward(requestCaptor.capture(), responseCaptor.capture());
	    	 yakshaAssert(currentTest(), 
	    			  "true", 
	    			 businessTestFile);
    	}catch(Exception ex) {
    		yakshaAssert(currentTest(), 
       			 "false", 
       			 businessTestFile);
    	}
    }
    @Test
    public void testShowKitDetails() throws ServletException, IOException {
    	when(this.request.getParameter("action")).thenReturn("showkit");
    	when(this.request.getRequestDispatcher("showkit.jsp")).thenReturn(rd);
    	when(this.request.getSession()).thenReturn(session);
    	try {
	    	 this.servlet.doPost(request, response);
	    	 verify(rd).forward(requestCaptor.capture(), responseCaptor.capture());
	    	 yakshaAssert(currentTest(), 
	    			  "true", 
	    			 businessTestFile);
    	}catch(Exception ex) {
    		yakshaAssert(currentTest(), 
       			 "false", 
       			 businessTestFile);
    	}
    }
    
    @Test
    public void testShowPlaceOrderForm() throws ServletException, IOException {
    	when(this.request.getParameter("action")).thenReturn("placeorder");
    	when(this.request.getRequestDispatcher("placeorder.jsp")).thenReturn(rd);
    	when(this.request.getSession()).thenReturn(session);
    	try {
	    	 this.servlet.doPost(request, response);
	    	 verify(rd).forward(requestCaptor.capture(), responseCaptor.capture());
	    	 yakshaAssert(currentTest(), 
	    			  "true", 
	    			 businessTestFile);
    	}catch(Exception ex) {
    		yakshaAssert(currentTest(), 
       			 "false", 
       			 businessTestFile);
    	}
    }
    
    @Test
    public void testSaveOrder() throws ServletException, IOException {
    	when(this.request.getParameter("action")).thenReturn("saveorder");
    	when(this.request.getParameter("address")).thenReturn("Some Address");
    	when(this.request.getRequestDispatcher("ordersummary.jsp")).thenReturn(rd);
    	when(this.request.getSession()).thenReturn(session);
    	try {
	    	 this.servlet.doPost(request, response);
	    	 verify(rd).forward(requestCaptor.capture(), responseCaptor.capture());
	    	 yakshaAssert(currentTest(), 
	    			  "true", 
	    			 businessTestFile);
    	}catch(Exception ex) {
    		yakshaAssert(currentTest(), 
       			 "false", 
       			 businessTestFile);
    	}
    }
    
    @Test
    public void testShowOrderSummary() throws ServletException, IOException {
    	when(this.request.getParameter("action")).thenReturn("ordersummary");
    	when(this.request.getRequestDispatcher("ordersummary.jsp")).thenReturn(rd);
    	when(this.request.getSession()).thenReturn(session);
    	try {
	    	 this.servlet.doPost(request, response);
	    	 verify(rd).forward(requestCaptor.capture(), responseCaptor.capture());
	    	 yakshaAssert(currentTest(), 
	    			  "true", 
	    			 businessTestFile);
    	}catch(Exception ex) {
    		yakshaAssert(currentTest(), 
       			 "false", 
       			 businessTestFile);
    	}
    }
    
}


