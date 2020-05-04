package com.bug.spring.model;

import java.io.PrintWriter;
import java.util.List;

import com.bug.spring.security.model.User;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class CSVGenerator {

	@SuppressWarnings("unchecked")
	public static void generateUserReport(PrintWriter writer, List<User> users) {

		try {
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(User.class);
			String[] columns = new String[] { "ID", "username", "email" };
			strategy.setColumnMapping(columns);
			strategy.generateHeader(columns);
			StatefulBeanToCsv<User> beanToCsv = new StatefulBeanToCsvBuilder<User>(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withMappingStrategy(strategy).withSeparator(',')
					.build();
			beanToCsv.write(users);
		} catch (CsvRequiredFieldEmptyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		catch (CsvDataTypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
