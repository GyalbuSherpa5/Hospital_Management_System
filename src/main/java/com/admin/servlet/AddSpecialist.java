package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Db.DbConnect;
import com.dao.SpecialistDao;

@SuppressWarnings("serial")
@WebServlet("/addSpecialist")
public class AddSpecialist extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String specName = req.getParameter("spec_Name");
		SpecialistDao dao = new SpecialistDao(DbConnect.getConn());
		boolean f = dao.addSpecialllist(specName);
		
		HttpSession session = req.getSession();
		
		if(f) {
			session.setAttribute("successMsg", "Specialist Added");
			resp.sendRedirect("admin/index.jsp");
		}
		else {
			session.setAttribute("errorMsg", "Something wrong on server");
			resp.sendRedirect("admin/index.jsp");
		}
		
		
	}
	
}
