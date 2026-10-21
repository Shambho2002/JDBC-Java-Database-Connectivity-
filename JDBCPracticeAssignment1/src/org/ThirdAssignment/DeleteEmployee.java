package org.ThirdAssignment;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;
public class DeleteEmployee {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccompanydb", "Shambhu Sharma", "Sh@mbhu26");
		if(connect != null) {
			System.out.println("Database is Connected Successfully...");
			try {
				String query = "DELETE FROM employees WHERE employee_id = ?";
				PreparedStatement stmt = connect.prepareStatement(query);
				
				Scanner input = new Scanner(System.in);
				
				System.out.print("Enter Employee ID : ");
				int id = input.nextInt();
				
				stmt.setInt(1, id);
				
				int result = stmt.executeUpdate();
				if(result > 0) {
					System.out.println("Employee Deleted Successfully...");
				}
				else {
					System.out.println("Employee Not Found With This ID!");
				}
			}
			catch(SQLException e) {
				System.out.println("Database Error : "+e.getMessage());
			}
			catch(java.util.InputMismatchException e) {
				System.out.println("Invalid input! Please enter valid input.");
			}
		}
		else {
			System.out.println("Database is Not Connected!");
		}
	}

}
