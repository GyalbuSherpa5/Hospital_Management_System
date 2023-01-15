package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Db.DbConnect;
import com.dao.UserDao;
import com.entity.User;

@SuppressWarnings("serial")
@WebServlet("/user_register")
public class UserRegister extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		try {
			String name = req.getParameter("fullname");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			User user = new User(name,email,password);
			UserDao dao = new UserDao(DbConnect.getConn());
			boolean f = dao.register(user);
			
			HttpSession session = req.getSession();
			
			if(f) {
				session.setAttribute("successMsg","Register Successfull");
				resp.sendRedirect("signup.jsp");
			}
			else {
				session.setAttribute("errorMsg","Something wrong on server");
				resp.sendRedirect("signup.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
