package com.sh.produt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sh.connect.DBConnector;

public class ProductDAO {
	
	public int insert(ProductDTO productDTO) throws Exception{
		
		Connection conn = DBConnector.dbconnect();
		
		String sql = "insert into product values(product_seq.nextval, ?, ?, ?, ?)";  //pnum은 자동증가하는 시퀀스를 사용하기 때문
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		
		st.setString(1, productDTO.getCategory());
		st.setString(2, productDTO.getPname());
		st.setInt(3, productDTO.getPrice());
		st.setInt(4, productDTO.getStock());
		
		int result = st.executeUpdate();
		
		DBConnector.dbDisconnect(st, conn);
		
		return result;
	}
	
	public ProductDTO select_one(int pnum) throws Exception{
		ProductDTO productDTO =  null;
		Connection conn = DBConnector.dbconnect();
		
		String sql = "select * from product where pnum = ?";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, pnum);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			productDTO =  new ProductDTO();
			productDTO.setpNum(rs.getInt(1));
			productDTO.setCategory(rs.getString(2));
			productDTO.setPname(rs.getString(3));
			productDTO.setPrice(rs.getInt(4));
			productDTO.setStock(rs.getInt(5));
		}
		
		DBConnector.dbDisconnect(st, conn, rs);
		
		return productDTO;
	}
	

	public ArrayList<ProductDTO> select_list(int pnum) throws Exception{
		ArrayList<ProductDTO> ar = new ArrayList<ProductDTO>();
		ProductDTO productDTO =  null;
		Connection conn = DBConnector.dbconnect();
		
		String sql = "select * from product ";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, pnum);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			productDTO =  new ProductDTO();
			productDTO.setpNum(rs.getInt(1));
			productDTO.setCategory(rs.getString(2));
			productDTO.setPname(rs.getString(3));
			productDTO.setPrice(rs.getInt(4));
			productDTO.setStock(rs.getInt(5));
			ar.add(productDTO);
		}
		
		DBConnector.dbDisconnect(st, conn, rs);
		
		return ar;
	}
	public int update(ProductDTO productDTO) throws Exception{
		Connection conn = DBConnector.dbconnect();
		
		String sql = "update product set stock = stock + ? where pnum = ?";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, productDTO.getStock());
		st.setInt(2, productDTO.getpNum());
		
		int result = st.executeUpdate();
		
		DBConnector.dbDisconnect(st, conn);
		
		return result;
	}
	
	public int delete(int pnum) throws Exception{
		Connection conn = DBConnector.dbconnect();
		
		String sql = "delete from product where pnum = ?";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, pnum);
		
		int result = st.executeUpdate();
		
		DBConnector.dbDisconnect(st, conn);
		
		return result;
	}
	
	
	
}
