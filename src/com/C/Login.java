package com.C;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.M.User;
import com.M.UserDAOIO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		UserDAOIO userDAOIO = new UserDAOIO();
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		//System.out.println(user.getName());
		//System.out.println(user.getPwd());
		boolean check = userDAOIO.LoginUser(user);
		if(check){
			Cookie coo1 = new Cookie("pwd",user.getPwd());
			Cookie coo2 = new Cookie("name",user.getName());
			response.addCookie(coo1);
			response.addCookie(coo2);
			request.getRequestDispatcher("user.jsp").forward(request, response);  
		}else{
			request.getRequestDispatcher("success.jsp").forward(request, response);  
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
