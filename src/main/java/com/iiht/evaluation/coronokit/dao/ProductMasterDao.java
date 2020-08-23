package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.ProductMaster;

public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	private final String INS_PRODUCT_QRY = "INSERT INTO products (pid,pName,pCost,pDesc) values(?,?,?,?)";
	private final String UPD_PRODUCT_QRY = "UPDATE products SET pName=?,pCost=?,pDesc=? WHERE pid=?";
	private final String SEL_ALL_PRODUCT_QRY = "SELECT pid,pName,pCost,pDesc from products";
	private final String GET_BY_ID_PRODUCT_QRY = "SELECT pid,pName,pCost,pDesc from products WHERE pid=?";
	private final String DEL_PRODUCT_QRY = "DELETE FROM products WHERE pid=?";
	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public List<ProductMaster> getAllProducts() throws Exception {
		List<ProductMaster> productMaster=new ArrayList<>();
		connect();
		try( 	
			PreparedStatement pst = jdbcConnection.prepareStatement(SEL_ALL_PRODUCT_QRY);){
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				ProductMaster product = new ProductMaster();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setCost(String.valueOf(rs.getInt(3)));
				product.setProductDescription(rs.getString(4));							
				productMaster.add(product);
			}
			
			if(productMaster.isEmpty()) {
				productMaster=null;
			}
		} catch (SQLException exp) {
			throw new Exception("An error occured, Could not retrive the product details!" +exp);
		}
		disconnect();
		return productMaster;
	}
	public ProductMaster save(ProductMaster product) throws Exception {
		if (product != null) {
			connect();
			try (
					PreparedStatement pst = jdbcConnection.prepareStatement(UPD_PRODUCT_QRY);) {

				pst.setString(1, product.getProductName());
				pst.setDouble(2, (Double.parseDouble(product.getCost())));
				pst.setString(3, product.getProductDescription());
				pst.setInt(4, product.getId());
				pst.executeUpdate();

			} catch (SQLException exp) {
				throw new Exception("An error occured, Could not save the product details!");
			}
			disconnect();
		}
		return product;
	}
	public ProductMaster getById(int productId) throws Exception {
		ProductMaster product=null;
		connect();
		try (
				PreparedStatement pst = jdbcConnection.prepareStatement(GET_BY_ID_PRODUCT_QRY);) {		

			pst.setInt(1, productId);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				product = new ProductMaster();
				product.setId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setCost(rs.getDouble(3)+"");
				product.setProductDescription(rs.getString(4));
			}
			
		} catch (SQLException exp) {
			throw new Exception("An error occured, Could not retrive the product details!");
		}
		disconnect();
		return product;
	}
	public ProductMaster add(ProductMaster product) throws Exception {
		if (product != null) {
			connect();
			try (
					PreparedStatement pst = jdbcConnection.prepareStatement(INS_PRODUCT_QRY);) {
				pst.setInt(1, product.getId());
				pst.setString(2, product.getProductName());
				pst.setDouble(3, Double.parseDouble(product.getCost()));
				pst.setString(4, product.getProductDescription());
				pst.executeUpdate();
			} catch (SQLException exp) {
				throw new Exception("An error occured, Could not add the loan details!");
			}
			disconnect();
		}
		return product;
	}	
	public boolean deleteById(int productId) throws Exception {
		boolean isDeleted = false;
		connect();
		try (				
				PreparedStatement pst = jdbcConnection.prepareStatement(DEL_PRODUCT_QRY);) {
			pst.setInt(1, productId);
			int rowsCount = pst.executeUpdate();
			
			isDeleted= rowsCount>0 ;

		} catch (SQLException exp) {
			throw new Exception("An error occured, Could not delete the loan details!");
		}
		return isDeleted;
	}
}