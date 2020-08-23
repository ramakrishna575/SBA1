package com.iiht.evaluation.coronokit.test.functional;

import static com.iiht.evaluation.coronokit.test.util.TestUtils.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

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


import com.iiht.evaluation.coronokit.controller.AdminController;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;

public class TestAdminController {
	private AdminController servlet;
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

    @Before
    public void setUp() {
        this.servlet = new AdminController();
        productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
        this.servlet.setProductMasterDao(productMasterDao);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdminLogin() throws ServletException, IOException {
    	
    	when(this.request.getParameter("action")).thenReturn("login");
    	when(this.request.getParameter("loginid")).thenReturn("admin");
    	when(this.request.getParameter("password")).thenReturn("admin");
    	
    	when(this.request.getRequestDispatcher("listproducts.jsp")).thenReturn(rd);
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
    public void testNewProductForm() throws ServletException, IOException {
    	when(this.request.getParameter("action")).thenReturn("newproduct");
    	when(this.request.getRequestDispatcher("newproduct.jsp")).thenReturn(rd);
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
    public void testInsertNewProduct() throws ServletException, IOException {
    	when(this.request.getParameter("action")).thenReturn("insertproduct");
    	when(this.request.getParameter("pname")).thenReturn("Mask");
    	when(this.request.getParameter("pcost")).thenReturn("10");
    	when(this.request.getParameter("pdesc")).thenReturn("Safe");
    	when(this.request.getRequestDispatcher("listproducts.jsp")).thenReturn(rd);
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
    public void testEditProductForm() throws ServletException, IOException {
    	when(this.request.getParameter("action")).thenReturn("editproduct");
    	when(this.request.getRequestDispatcher("editproduct.jsp")).thenReturn(rd);
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
    public void testListProduct() throws ServletException, IOException {
    	when(this.request.getParameter("action")).thenReturn("list");
    	when(this.request.getRequestDispatcher("listproducts.jsp")).thenReturn(rd);
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
