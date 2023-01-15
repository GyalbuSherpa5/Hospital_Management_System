package com.admin.servlet;

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
@WebServlet("/updateDoctor")
public class UpdateDoctor extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String fullName = req.getParameter("fullname");
			String dob = req.getParameter("dob");
			String qualification = req.getParameter("qualification");
			String specification = req.getParameter("spec");
			String email = req.getParameter("email");
			String phone = req.getParameter("mobno");
			String password = req.getParameter("password");
			
			int id = Integer.parseInt(req.getParameter("id"));
			
			
			Doctor d = new Doctor(id,fullName, dob, qualification, specification, email, phone, password);
			DoctorDao dao = new DoctorDao(DbConnect.getConn());
			
			HttpSession session = req.getSession();
			
			if(dao.updateDoctor(d)) {
				session.setAttribute("successMsg", "Doctor Updated Successfully");
				resp.sendRedirect("admin/viewDoctor.jsp");
			}
			else {
				session.setAttribute("errorMsg", "Something wrong on server");
				resp.sendRedirect("admin/viewDoctor.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
