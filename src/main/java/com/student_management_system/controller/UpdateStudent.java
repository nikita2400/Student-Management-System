package com.student_management_system.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student_management_system.dao.StudentDao;
import com.student_management_system.dto.Student;

@WebServlet("/updateStudent")
public class UpdateStudent extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// You have to find the existing Student from the database,
		// then send the Students to the jsp file.
		// then get data from the jsp and save it.
		
		String id =  req.getParameter("studentId");
		Student student = new StudentDao().getStudent(Integer.parseInt(id));
		
		req.setAttribute("student", student);
		
		req.getRequestDispatcher("UpdateStudent.jsp").forward(req, resp);
	}

}
