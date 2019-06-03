package com.M;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TalkDAOIO implements TalkDAO{

	@Override
	public int AddTalk(Talk talk) {
		int i = 0;
		PreparedStatement pstmt = null;
		DataBase db = new DataBase();
		Date date = new Date(); 
		try {
			db.ConnectionSQL();
			String sql = "insert into talk(nameA,nameB,talk,time) values(?,?,?,?)";
	        try {
				pstmt = (PreparedStatement) db.con.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
	        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            String str = format.format(date);
				pstmt.setString(1, talk.getNameA());
				pstmt.setString(2, talk.getNameB());
				pstmt.setString(3, talk.getTalk());
				pstmt.setString(4, str);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				i = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.AllClose();
		return i;
	}
}
