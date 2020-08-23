package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterDao productMasterDao;
	private KitDao kitDAO;

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}
	
	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		String viewName = "";
		HttpSession session;
		//requestAdminController=request;
		try {
			switch (action) {
			case "login" : 
				viewName = adminLogin(request, response);	
				break;
			case "newproduct":
				System.out.println("New Prod");
				viewName = updateProduct(request, response);
				break;
			case "insertproduct":
				viewName = insertProduct(request, response);
				break;
			case "deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "updateproduct":
				viewName = updateProduct(request, response);
				break;
			case "list":
				viewName = listAllProducts(request, response);
				break;	
			case "listorders":
				viewName = listOrders(request, response);
				break;				
			case "logout":
				session = request.getSession(false);
				session.removeAttribute("logUser");
				session.invalidate();
				session=null;
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "index.jsp?action=logout";
	}

	private String listOrders(HttpServletRequest request, HttpServletResponse response) {
		String view="";		
		try {
			request.setAttribute("kitDAO", kitDAO);
			view = "listorders.jsp";
		} catch (Exception e) {
			view = "errorPage.jsp";
		}
		return view;
	}
	
	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) {
		String view="";		
		try {
			List<ProductMaster> productMaster = productMasterDao.getAllProducts();
			request.setAttribute("productMaster", productMaster);
			view = "listproducts.jsp";
		} catch (Exception e) {
			view = "errorPage.jsp";
		}
		return view;
	}

	private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
		ProductMaster product = new ProductMaster();
		
		product.setId(Integer.parseInt(request.getParameter("pId")));
		product.setProductName(""+ request.getParameter("pName"));
		product.setCost(""+request.getParameter("pCost"));
		product.setProductDescription(""+request.getParameter("pDesc"));
		try {
			
			if(request.getParameter("action").equals("updateproduct")) {
				productMasterDao.save(product);
			}else {
				productMasterDao.add(product);
			}
		} catch (Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			return "errorPage.jsp";
		}
		return listAllProducts(request, response) ;
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) {
		String view="productForm.jsp";
		int loanId = Integer.parseInt(request.getParameter("id"));
		try {
			ProductMaster product = productMasterDao.getById(loanId);
			request.setAttribute("product", product);
			request.setAttribute("isNew", false);
			view = "productForm.jsp";
		} catch (Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int prodId = Integer.parseInt(request.getParameter("id"));
		productMasterDao.deleteById(prodId);
		return listAllProducts(request, response) ;
	}

	private String insertProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.setAttribute("isNew", true);
		return "productForm.jsp";
	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "productForm.jsp";
	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProductMaster> productMaster = productMasterDao.getAllProducts();
		request.setAttribute("productMaster", productMaster);
		return listAllProducts(request, response) ;
	}
	private String errorPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "errorPage.jsp";
	}
	
}