package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Db.DbConnect;
import com.dao.DoctorDao;
import com.entity.Doctor;

@SuppressWarnings("serial")
@WebServlet("/doctorlogin")
public class DoctorLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		HttpSession session = req.getSession();

		DoctorDao dao = new DoctorDao(DbConnect.getConn());
		Doctor user = dao.login(email, password);

		if (user != null) {

			session.setAttribute("docObj", user);
			resp.sendRedirect("doctor/index.jsp");
			
		} else {
			session.setAttribute("errorMsg", "Invalid email & password");
			resp.sendRedirect("doctor_login.jsp");
		}
	}

}
