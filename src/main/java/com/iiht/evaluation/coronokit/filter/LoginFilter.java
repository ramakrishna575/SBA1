package com.iiht.evaluation.coronokit.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
			HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse resp = (HttpServletResponse) response;
	        
	        List<ProductMaster> productMaster = (List<ProductMaster>) request.getAttribute("productMaster");
	        String uri = req.getRequestURI();
			HttpSession session = req.getSession(false);        
	        if("admin".equals(request.getParameter("loginid")) && "admin".equals(request.getParameter("password"))) {
	        	session.invalidate();
	        	session=req.getSession(true);
	        	session.setAttribute("logUser", "admin");
	        	chain.doFilter(req, resp);
	        } else if("admin".equals(session.getAttribute("logUser"))) {
	        	chain.doFilter(req, resp);
	        } else {
	        	resp.sendRedirect("index.jsp?action=failed");
	        }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
