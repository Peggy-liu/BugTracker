package com.bug.spring.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.bug.spring.security.model.Role;
import com.bug.spring.security.model.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

	public static ByteArrayInputStream generateUserReport(List<User> users) {
		Document pdf = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(pdf, out);
			
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);
			table.setWidths(new int[] {100, 100, 100});
			
			table.addCell(new PdfPCell(new Phrase("ID")));
			table.addCell(new PdfPCell(new Phrase("Username")));
			table.addCell(new PdfPCell(new Phrase("Email")));
			
			for(User user:users) {
				
					table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getID()))));
					table.addCell(new PdfPCell(new Phrase(user.getUsername())));
					table.addCell(new PdfPCell(new Phrase(user.getEmail())));
				
			}
			pdf.open();
			pdf.add(table);
			
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
		finally {
			pdf.close();
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}
