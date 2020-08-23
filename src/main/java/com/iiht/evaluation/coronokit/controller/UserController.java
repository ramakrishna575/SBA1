package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.OrderSummary;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config. getServletContext().getInitParameter("jdbcPassword");
		
		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;	
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;	
			case "ordersummary":
				viewName = showOrderSummary(request, response);
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

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String oid=request.getParameter("id");
		OrderSummary orderSummary=kitDAO.getOrderSummary(oid);
		request.setAttribute("OrderSummary", orderSummary);
		request.setAttribute("pmDAO", productMasterDao);		
		return "ordersummary.jsp";
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		
		CoronaKit kit=(CoronaKit) request.getSession().getAttribute("UserDetails");
		kit.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		List<KitDetail> kitDetails=new ArrayList<>();	
		String[] products= request.getParameterValues("productList");
		String[] prodQuantity= request.getParameterValues("productQuantity");
		ProductMaster product;
		for(int i=0;i<products.length;i++) {
			KitDetail kitDetail=new KitDetail();
			product=productMasterDao.getById(Integer.parseInt(products[i]));
			kitDetail.setAmount((int) Double.parseDouble(product.getCost()));
			kitDetail.setProductId(Integer.parseInt(products[i]));
			kitDetail.setQuantity(Integer.parseInt(prodQuantity[i]));
			
			kitDetails.add(kitDetail);
		}
		OrderSummary order=new OrderSummary(kit,kitDetails);
		String oid = kitDAO.save(order);
		request.setAttribute("OrderSummar", order);
		request.setAttribute("UserDetails", kit);
		request.setAttribute("OID", oid);
		return "placeorder.jsp";
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "";
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		String[] products= request.getParameterValues("productList");
		CoronaKit kit=(CoronaKit) request.getSession().getAttribute("UserDetails");
		List<ProductMaster> productMaster=new ArrayList<>();
		double totCost=0.0;
		for(String productId: products) {
			ProductMaster product=productMasterDao.getById(Integer.parseInt(productId));
			productMaster.add(product);
			totCost = totCost+Double.parseDouble(product.getCost());
		}
		request.setAttribute("productMaster", productMaster);
		request.setAttribute("UserDetails", kit);
		request.setAttribute("totalCost", totCost);
		return "showproductstoadd.jsp";
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoronaKit kit=new CoronaKit();
		//personName, email, contactNo, address, orderDate
		kit.setPersonName(request.getParameter("personName"));
		kit.setEmail(request.getParameter("email"));
		kit.setContactNumber(request.getParameter("contactNumber"));
		kit.setDeliveryAddress(request.getParameter("deliveryAddress"));
		List<ProductMaster> productMaster = productMasterDao.getAllProducts();
		request.setAttribute("productMaster", productMaster);
		request.getSession().setAttribute("logUser", "user");
		request.setAttribute("OrderDetails", kit);
		return "listproducts.jsp";
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		return "newuser.jsp";
	}
}