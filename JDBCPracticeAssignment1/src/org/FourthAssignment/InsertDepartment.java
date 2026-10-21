package org.FourthAssignment;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Scanner;
public class InsertDepartment {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		
		try {
			
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccompanydb", "Shambhu Sharma", "Sh@mbhu26");
			if(connect != null) {
				System.out.println("Database is Connected Successfully...");
				try {
					String query = "INSERT INTO departments (department_name, location) VALUES (?,?)";
					PreparedStatement stmt = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					
					Scanner input = new Scanner(System.in);
					
					System.out.print("Enter Department Name : ");
					String deptName = input.nextLine();
					
					System.out.print("Enter Location : ");
					String location = input.nextLine();
					
					stmt.setString(1, deptName);
					stmt.setString(2, location);
					
					int result = stmt.executeUpdate();
					if(result != 0) {
						System.out.println("Department has been Saved Successfully...");
						try(ResultSet rs = stmt.getGeneratedKeys()){
							if(rs.next()) {
								System.out.println("Generated Department ID : "+rs.getInt(1));
							}
						}
					}
					else {
						System.out.println("Department not saved!");
					}
					
				}
				catch(SQLException e) {
					System.out.println("Database Error : "+e.getMessage());
				}
				catch(java.util.InputMismatchException e) {
					System.out.println("Invalid input! Please enter valid input!");
				}
			}
			else {
				System.out.println("Database is Not Connected!");
			}
		}
		catch(SQLException e) {
			System.out.println("Database Error : "+e.getMessage());
		}
		
	}

}
