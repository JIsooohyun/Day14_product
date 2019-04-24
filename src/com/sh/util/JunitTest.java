package com.sh.util;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.sh.connect.DBConnector;
import com.sh.io.IoDAO;
import com.sh.io.IoDTO;
import com.sh.produt.ProductDAO;
import com.sh.produt.ProductDTO;

public class JunitTest {

	//	@Test
	//	public void test() throws Exception {
	//		Connection conn = DBConnector.dbconnect();
	//		assertNotNull(conn);
	//	}
	//	
	//	@Test
	//	public void update() throws Exception{
	//		ProductDAO productDAO = new ProductDAO();
	//		ProductDTO productDTO = new ProductDTO();
	////		productDTO.setCategory("Electronic");
	////		productDTO.setPname("LG");
	////		productDTO.setPrice(300);
	//		productDTO.setStock(50);
	//		productDTO.setpNum(4);
	//		
	//		int result = productDAO.update(productDTO);
	//		
	//		assertEquals(1, result);
	//	}

	@Test
	public void insert() throws Exception{  //트랜잭션
		int result = 0;
		IoDAO ioDAO = new IoDAO();
		IoDTO ioDTO = new IoDTO();
		ioDTO.setPnum(3);
		ioDTO.setIn_pct(20);
		ioDTO.setIn_date("2019-04-03");
		//ioDTO.setOut_pct(20);
		//ioDTO.setOut_date("2019-03-03");

		Connection conn = DBConnector.dbconnect();
		try {
			conn.setAutoCommit(false);
			result = ioDAO.insert(ioDTO, conn);
//			if(result<1) {
//				throw new Exception();
//			}
			if(result>0) {
				ProductDAO productDAO = new ProductDAO();
				ProductDTO productDTO = new ProductDTO();

				productDTO.setpNum(122);
				productDTO.setStock(ioDTO.getIn_pct());

				result = productDAO.update(productDTO);
				
//				if(result<0) {
//					throw new Exception();
//				}
			}

			if(result>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch (Exception e) {
			conn.rollback();
		}finally {
			conn.close();
			conn.setAutoCommit(true);
		}
		assertEquals(1, result);

	}

}















