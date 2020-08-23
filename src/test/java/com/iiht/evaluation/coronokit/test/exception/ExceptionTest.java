package com.iiht.evaluation.coronokit.test.exception;

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
import com.iiht.evaluation.coronokit.controller.AdminController;

public class ExceptionTest {
	private UserController userServlet;
	private AdminController adminServlet;
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
        this.userServlet = new UserController();
        this.adminServlet = new AdminController();
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testBadAdminRequest() throws ServletException, IOException {
    	when(this.request.getParameter("action")).thenReturn("xyz");
    	when(this.request.getRequestDispatcher("notfound.jsp")).thenReturn(rd);
    	when(this.request.getSession()).thenReturn(session);
    	try {
	    	 this.adminServlet.doPost(request, response);
	    	 verify(rd).forward(requestCaptor.capture(), responseCaptor.capture());
	    	 yakshaAssert(currentTest(), 
	    			  "true", 
	    			 exceptionTestFile);
    	}catch(Exception ex) {
    		yakshaAssert(currentTest(), 
       			 "false", 
       			 exceptionTestFile);
    	}
    }
    
    @Test
    public void testBadUserRequest() throws ServletException, IOException {
    	when(this.request.getParameter("action")).thenReturn("xyz");
    	when(this.request.getRequestDispatcher("notfound.jsp")).thenReturn(rd);
    	when(this.request.getSession()).thenReturn(session);
    	try {
	    	 this.userServlet.doPost(request, response);
	    	 verify(rd).forward(requestCaptor.capture(), responseCaptor.capture());
	    	 yakshaAssert(currentTest(), 
	    			  "true", 
	    			 exceptionTestFile);
    	}catch(Exception ex) {
    		yakshaAssert(currentTest(), 
       			 "false", 
       			 exceptionTestFile);
    	}
    }
    
}
