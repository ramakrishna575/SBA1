package com.iiht.evaluation.coronokit.test.boundary;

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

import com.iiht.evaluation.coronokit.controller.AdminController;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
public class BoundaryTest {
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

    @Before
    public void setUp() {
        
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInvalidDbDetails() throws ServletException, IOException {
    	this.servlet = new AdminController();
        ProductMasterDao productMasterDao = new ProductMasterDao("", "", "");
        this.servlet.setProductMasterDao(productMasterDao);
    	when(this.request.getParameter("action")).thenReturn("list");
    	when(this.request.getRequestDispatcher("listproducts.jsp")).thenReturn(rd);
    	when(this.request.getSession()).thenReturn(session);
    	try {
	    	 this.servlet.doPost(request, response);
	    	 verify(rd).forward(requestCaptor.capture(), responseCaptor.capture());
	    	 yakshaAssert(currentTest(), 
	    			  "true", 
	    			 boundaryTestFile);
    	}catch(Exception ex) {
    		yakshaAssert(currentTest(), 
       			 "false", 
       			 boundaryTestFile);
    	}
    }
}
