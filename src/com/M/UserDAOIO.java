package com.M;

import java.util.ArrayList;
import java.util.List;

import com.M.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class UserDAOIO implements UserDAO{
	@Override
	public int AddUser(User user) {
		// TODO Auto-generated method stub
		int i = 0;
		PreparedStatement pstmt = null;
		DataBase db = new DataBase();
		try {
			db.ConnectionSQL();
			String sql = "insert into user(name,pwd) values(?,?)";
	        try {
				pstmt = (PreparedStatement) db.con.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				pstmt.setString(1, user.getName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try {
				pstmt.setString(2, user.getPwd());
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

	@Override
	public boolean UpdateUser(User user) {//
		// TODO 
		boolean check = false;
		int i = 0;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		DataBase db = new DataBase();
		try {
			try {
				con = db.ConnectionSQL();
				st = con.createStatement();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sql = "update user set pwd='"+user.getPwd()+"'where name='"+user.getName()+"'";
			i = st.executeUpdate(sql);
			//System.out.println(i);
			//System.out.println(st.executeUpdate(sql));
			if(i>0) {
				check = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	@Override
	public boolean LoginUser(User user) {//�û���½
		//System.out.println(user.getName());
		//System.out.println(user.getPwd());
		boolean check=false;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		DataBase db = new DataBase();
		try {
			try {
				con = db.ConnectionSQL();
				st = con.createStatement();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sql = "select * from user where name='"+user.getName()+"' and pwd='"+user.getPwd()+"'";
			check = st.execute(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		db.AllClose();
		return check;
	}

	@Override
	public ArrayList<User> getUser(User user) {//��ȡ��½�û���Ϣ
		// TODO �Զ����ɵķ������
		ArrayList list = new ArrayList();
		boolean check=false;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		DataBase db = new DataBase();
		try {
			try {
				con = db.ConnectionSQL();
				st = con.createStatement();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sql = "select * from user";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				user.setName(rs.getString("name"));
				list.add(user);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		db.AllClose();
		return list;
	}
}
