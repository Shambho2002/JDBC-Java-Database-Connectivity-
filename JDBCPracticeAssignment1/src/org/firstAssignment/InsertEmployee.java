package org.firstAssignment;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class InsertEmployee {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccompanydb","Shambhu Sharma","Sh@mbhu26");
		if(connect != null) {
			System.out.println("Database is Connected Successfully!");
			PreparedStatement stmt = connect.prepareStatement("INSERT INTO employees (employee_name, email, salary, department_id, joining_date, city) VALUES(?,?,?,?,?,?)");
			
			Scanner input = new Scanner(System.in);
			
			System.out.print("Enter Employee Name : ");
			String name = input.nextLine();
			
			System.out.print("Enter Email : ");
			String email = input.nextLine();
			
			System.out.print("Enter Salary : ");
			Double salary = input.nextDouble();
			
			System.out.print("Enter Department ID : ");
			int depId = input.nextInt();
			input.nextLine();
			
			System.out.print("Enter Joining Date (dd-MM-yyyy): ");
			String joiningDate = input.nextLine();
			
			System.out.print("Enter City : ");
			String city = input.nextLine();
			
			LocalDate date = LocalDate.parse(joiningDate, DateTimeFormatter.ofPattern("dd-M-yyyy"));
			
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setDouble(3, salary);
			stmt.setInt(4, depId);
			stmt.setDate(5, java.sql.Date.valueOf(date));
			stmt.setString(6, city);
			
			int result = stmt.executeUpdate();
			
			if(result != 0) {
				System.out.println("Record Saved Successfully...");
			}
			else {
				System.out.println("Record Not Saved!");
			}
			
		}
		else {
			System.out.println("Database is Not Connected Successfully!");
		}
	}

}
