package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.OrderSummary;
import com.iiht.evaluation.coronokit.model.ProductMaster;



public class KitDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	private final String INS_ORDER_QRY = "INSERT INTO orderSummary (oid, pid, quantity, amount, personName, email, contactNo, address, orderDate, orderStatus) values(?,?,?,?,?,?,?,?,?,?)";
	private final String UPD_ORDERKit_QRY = "UPDATE orderSummary SET pid=?,quantity=?,orderStatus=? WHERE oid=?";
	private final String SEL_ALL_ORDER_QRY = "SELECT oid, kitId, pid, quantity, amount, personName, email, contactNo, address, orderDate, orderStatus from orderSummary";
	private final String GET_BY_ID_ORDER_QRY = "SELECT oid, kitId, pid, quantity, amount, personName, email, contactNo, address, orderDate, orderStatus from orderSummary WHERE oid=?";

	public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public String save(OrderSummary order) throws Exception {
		String oid="";
		if (order != null) {
			connect();
			try(PreparedStatement pst = jdbcConnection.prepareStatement(INS_ORDER_QRY);) {				
				
				CoronaKit kit=order.getCoronaKit();
				List<KitDetail> kitDetails= order.getKitDetails();
				oid= new SimpleDateFormat("yyMMddhhmmssMs").format(new Date()).toString();
				for(int i=0;i<kitDetails.size();i++) {				
					//(oid, pid, quantity, personName, email, contactNo, address, orderDate, orderStatus)
					oid= new SimpleDateFormat("yyMMddhhmmssMs").format(new Date()).toString();
					pst.setString(1, oid);
					pst.setInt(2, kitDetails.get(i).getProductId());
					pst.setInt(3,kitDetails.get(i).getQuantity());
					pst.setDouble(4,kitDetails.get(i).getQuantity()*kitDetails.get(i).getAmount());
					pst.setString(5, kit.getPersonName());
					pst.setString(6, kit.getEmail());
					pst.setString(7, kit.getContactNumber());
					pst.setString(8, kit.getDeliveryAddress());
					pst.setString(9, kit.getOrderDate());
					pst.setString(10, "Successful");
					pst.addBatch();
				}	
				pst.executeBatch();
			} catch (SQLException exp) {
				throw new Exception("An error occured, Could not save the user details!");
			}
			disconnect();
			return oid;
		}
		return oid;
	}
	public OrderSummary getOrderSummary(String oid) throws Exception {
		List<KitDetail> kitDetails=new ArrayList<>();
		CoronaKit kit=new CoronaKit();
		OrderSummary orderSummary=new OrderSummary(kit, kitDetails);
		connect();
		try( 	
			PreparedStatement pst = jdbcConnection.prepareStatement(GET_BY_ID_ORDER_QRY);){
			pst.setString(1, oid);
			ResultSet rs = pst.executeQuery();
			int totAmount = 0;
			//oid, kitId, pid, quantity, amount, personName, email, contactNo, address, orderDate, orderStatus 
			while(rs.next()) {
				KitDetail kitDetail = new KitDetail();
				kitDetail.setAmount(rs.getInt(5));
				kitDetail.setCoronaKitId(rs.getInt(2));
				kitDetail.setProductId(rs.getInt(3));
				kitDetail.setQuantity(rs.getInt(4));						
				kitDetails.add(kitDetail);
				totAmount = totAmount + rs.getInt(5);
				kit.setContactNumber(rs.getString(8));
				kit.setDeliveryAddress(rs.getString(9));
				kit.setEmail(rs.getString(7));
				kit.setOrderDate(rs.getString(10));
				kit.setPersonName(rs.getString(6));
				kit.setTotalAmount(totAmount);
			}
			if(kitDetails.isEmpty()) {
				kitDetails=null;
			}
		} catch (SQLException exp) {
			throw new Exception("An error occured, Could not retrive the product details!" +exp);
		}
		disconnect();
		return orderSummary;
	}
	public OrderSummary getOrderSummary() throws Exception {
		List<KitDetail> kitDetails=new ArrayList<>();
		CoronaKit kit=new CoronaKit();
		OrderSummary orderSummary=new OrderSummary(kit, kitDetails);
		connect();
		try( 	
			PreparedStatement pst = jdbcConnection.prepareStatement(SEL_ALL_ORDER_QRY);){
			ResultSet rs = pst.executeQuery();
			int totAmount = 0;
			//oid, kitId, pid, quantity, amount, personName, email, contactNo, address, orderDate, orderStatus 
			while(rs.next()) {
				KitDetail kitDetail = new KitDetail();
				kitDetail.setAmount(rs.getInt(5));
				kitDetail.setCoronaKitId(rs.getInt(2));
				kitDetail.setProductId(rs.getInt(3));
				kitDetail.setQuantity(rs.getInt(4));						
				kitDetails.add(kitDetail);
				totAmount = totAmount + rs.getInt(5);
				kit.setContactNumber(rs.getString(8));
				kit.setDeliveryAddress(rs.getString(9));
				kit.setEmail(rs.getString(7));
				kit.setOrderDate(rs.getString(10));
				kit.setPersonName(rs.getString(6));
				kit.setTotalAmount(totAmount);
			}
			if(kitDetails.isEmpty()) {
				kitDetails=null;
			}
		} catch (SQLException exp) {
			throw new Exception("An error occured, Could not retrive the order summary details!" +exp);
		}
		disconnect();
		return orderSummary;
	}
}