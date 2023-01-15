package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Db.DbConnect;
import com.dao.AppointmentDAO;

@SuppressWarnings("serial")
@WebServlet("/updateStatus")
public class UpdateStatus extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	try {
		int id = Integer.parseInt(req.getParameter("id"));
		
		int did = Integer.parseInt(req.getParameter("did"));
		
		String comm = req.getParameter("comm");
		
		AppointmentDAO dao = new AppointmentDAO(DbConnect.getConn());
		
		HttpSession session = req.getSession();
		
		if(dao.updateCommentStatus(id,did,comm)) {
			session.setAttribute("successMsg", "Comment updated");
			resp.sendRedirect("doctor/patient.jsp");
		}
		else {
			session.setAttribute("errorMsg", "Something wrong on server");
			resp.sendRedirect("doctor/patient.jsp");
		}
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}	
		
	}
	
}
