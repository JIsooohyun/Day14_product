package com.sh.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sh.connect.DBConnector;

public class IoDAO {

	public int insert(IoDTO ioDTO, Connection conn) throws Exception{

		int result = 0;

		String sql = "insert into IO values(product_seq.nextval, ?, ?, ?, ?, ?)";

		PreparedStatement st = conn.prepareStatement(sql);

		st.setInt(1, ioDTO.getPnum());
		st.setInt(2, ioDTO.getIn_pct());
		st.setString(3, ioDTO.getIn_date());
		st.setInt(4, ioDTO.getOut_pct());
		st.setString(5, ioDTO.getOut_date());

		result = st.executeUpdate();
		

		DBConnector.dbDisconnect(st, conn);

		return result;	
	}
	
	public int delete(int num) throws Exception{
		Connection conn = DBConnector.dbconnect();
		
		String sql = "delete from io where num = ?";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setInt(1, num);
		
		int result = st.executeUpdate();
		
		DBConnector.dbDisconnect(st, conn);
		
		return result;
	}
	
	public IoDTO select_one(int num) throws Exception{
		IoDTO ioDTO = null;
		Connection conn = DBConnector.dbconnect();
		
		String sql = "select * from IO where num = ?";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			ioDTO = new IoDTO();
			ioDTO.setNum(rs.getInt(1));
			ioDTO.setPnum(rs.getInt(2));
			ioDTO.setIn_pct(rs.getInt(3));
			ioDTO.setIn_date(rs.getString(4));
			ioDTO.setOut_pct(rs.getInt(5));
			ioDTO.setOut_date(rs.getString(6));
		}
		
		DBConnector.dbDisconnect(st, conn, rs);
		
		return ioDTO;
	}
	public ArrayList<IoDTO>  select_list() throws Exception{
		ArrayList<IoDTO> ar = new ArrayList<IoDTO>();

		Connection conn = DBConnector.dbconnect();

		String sql = "Select * from IO";

		PreparedStatement st = conn.prepareStatement(sql);

		ResultSet rs = st.executeQuery();

		while(rs.next()) {
			IoDTO ioDTO = new IoDTO();
			ioDTO.setNum(rs.getInt(1));
			ioDTO.setPnum(rs.getInt(2));
			ioDTO.setIn_pct(rs.getInt(3));
			ioDTO.setIn_date(rs.getString(4));
			ioDTO.setOut_pct(rs.getInt(5));
			ioDTO.setOut_date(rs.getString(6));
			ar.add(ioDTO);
		}

		DBConnector.dbDisconnect(st, conn, rs);

		return ar;

	}



	public void update() throws Exception{

	}

	
}
