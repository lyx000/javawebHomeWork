package com.M;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/text?useSSL=false";
	public static final String DbName = "root";
	public static final String DbPwd = "lyx19970718";
	public Connection con = null;
	public PreparedStatement pstmt = null;
	public ResultSet rst = null;
	public Statement st = null;
	public Connection ConnectionSQL() throws ClassNotFoundException{
		con = null;
		Class.forName(driver);
		try{
			con = DriverManager.getConnection(URL,DbName,DbPwd);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	public void AllClose(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
