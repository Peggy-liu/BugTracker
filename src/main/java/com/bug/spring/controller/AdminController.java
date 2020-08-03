package com.bug.spring.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bug.spring.model.CSVGenerator;
import com.bug.spring.model.PDFGenerator;
import com.bug.spring.security.model.User;
import com.bug.spring.service.UserService;

@Controller
public class AdminController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/admin/home")
	public String home() {
		return "admin_home";
	}
	
	@RequestMapping("/admin/manage")
	public String toMngPage() {
		return "admin_mng";
	}
	
	@RequestMapping("/admin/pdf")
	public  ResponseEntity<?> generatePDF() {
		List<User> users = userService.getAllUsers();
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Disposition", "inline; filename=UserReport.pdf");
		ByteArrayInputStream input = PDFGenerator.generateUserReport(users);
		
		
		return ResponseEntity.ok().headers(header).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(input));
	}
	
	@RequestMapping("/admin/csv")
	public void generateCSV(HttpServletResponse res) throws IOException {
		res.setHeader("Content-Disposition", "attachment; filename=users.csv");
		CSVGenerator.generateUserReport(res.getWriter(), userService.getAllUsers());
	}
}
