package com.sh.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnector {
	
	public static Connection dbconnect() throws Exception{
		String user = "user01";
		String password = "user01";
		String url = "jdbc:oracle:thin:@211.238.142.30:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url, user, password);
		
		return conn;
	}
	
	public static void dbDisconnect(PreparedStatement st, Connection conn) throws Exception{
		st.close();
		conn.close();
	}
	
	public static void dbDisconnect(PreparedStatement st, Connection conn, ResultSet rs) throws Exception{
		rs.close();
		DBConnector.dbDisconnect(st, conn);
	}

}
