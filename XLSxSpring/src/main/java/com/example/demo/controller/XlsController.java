package com.example.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repo.xlsrepo;

@RestController
public class XlsController {
	@Autowired
	xlsrepo pr;
	@GetMapping("/geteid/{eid}")
	public String m(@PathVariable ("eid") int eid) throws Exception 
	{
		Class.forName("com.mysql.jdbc.Driver");
	      Connection connect = DriverManager.getConnection( 
	         "jdbc:mysql://localhost:3306/xlsemployee" , 
	         "root" , 
	         "root"
	      );
	      
	      Statement statement = connect.createStatement();
	      ResultSet resultSet = statement.executeQuery("select * from employee");
	      XSSFWorkbook workbook = new XSSFWorkbook(); 
	      XSSFSheet spreadsheet = workbook.createSheet("employe db");
	      
	      XSSFRow row = spreadsheet.createRow(1);
	      XSSFCell cell;
	      cell = row.createCell(1);
	      cell.setCellValue("EMP ID");
	      cell = row.createCell(2);
	      cell.setCellValue("EMP NAME");
	      cell = row.createCell(3);
	      cell.setCellValue("DEG");
	      cell = row.createCell(4);
	      cell.setCellValue("SALARY");
	      cell = row.createCell(5);
	      cell.setCellValue("DEPT");
	      int i = 2;

	      while(resultSet.next()) {
	         row = spreadsheet.createRow(i);
	         cell = row.createCell(1);
	         cell.setCellValue(resultSet.getInt("eid"));
	         cell = row.createCell(2);
	         cell.setCellValue(resultSet.getString("ename"));
	         cell = row.createCell(3);
	         cell.setCellValue(resultSet.getString("deg"));
	         cell = row.createCell(4);
	         cell.setCellValue(resultSet.getString("salary"));
	         cell = row.createCell(5);
	         cell.setCellValue(resultSet.getString("dept"));
	         i++;
	      }

	      FileOutputStream out = new FileOutputStream(new File("exceldatabase.xlsx"));
	      workbook.write(out);
	      out.close();
		
		System.out.println("Done");
		return "data get successfully";
	}
	}

