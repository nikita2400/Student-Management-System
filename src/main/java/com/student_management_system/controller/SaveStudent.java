package com.student_management_system.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student_management_system.dao.AdminDao;
import com.student_management_system.dao.StudentDao;
import com.student_management_system.dto.Admin;
import com.student_management_system.dto.Student;

@WebServlet("/save")
public class SaveStudent extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin!=null) {
		String name = req.getParameter("studentName");
		String StringPhoneNumber = req.getParameter("studentPhoneNumber");
		String email = req.getParameter("studentEmail");
		
		long PhoneNumber = Long.parseLong(StringPhoneNumber);
		
		ServletContext context = getServletContext();
		String fee = context.getInitParameter("studentFee");
		
		long studentFee = Long.parseLong(fee);
		
		Student student = new Student();
		student.setStudentName(name);
		student.setStudentPhoneNumber(PhoneNumber);
		student.setStudentEmail(email);
		student.setStudentFee(studentFee);
		student.setAdmin(admin);
		
		new StudentDao().saveStudent(student);
		// after saving the student assign the student to the related admin
		List<Student> students = admin.getStudents();
		students.add(student);
		new AdminDao().updateAdmin(admin);
		
		resp.sendRedirect("Dashboard.jsp");
		}
		else {
			resp.sendRedirect("AdminLogin.jsp");
		}
	}
}
