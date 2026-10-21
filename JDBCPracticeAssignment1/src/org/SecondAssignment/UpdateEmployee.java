package org.SecondAssignment;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;

public class UpdateEmployee {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccompanydb", "Shambhu Sharma",
				"Sh@mbhu26");

		if (connect != null) {
			System.out.println("Database Connected Successfully...");

			try {

				PreparedStatement stmt = connect.prepareStatement("UPDATE employees SET salary = ? WHERE employee_id = ?");
				Scanner input = new Scanner(System.in);

				System.out.print("ENTER EMPLOYEE ID : ");
				int id = input.nextInt();
				
				System.out.print("ENTER NEW SALARY : ");
				double salary = input.nextDouble();

				stmt.setDouble(1, salary);
				stmt.setInt(2, id);

				int result = stmt.executeUpdate();

				if (result > 0) {
					System.out.println("EMPLOYEE SALARY HAS BEEN UPDATED SUCCESSFULLY...");
				} else {
					System.out.println("EMPLOYEE NOT FOUND WITH THIS ID!");
				}
			} catch (SQLException e) {
				System.out.println("Database Error : "+e.getMessage());
			}
			catch(java.util.InputMismatchException e) {
				System.out.println("Invalid input! Enter a valid employee ID and salary.");
			}

		} else {
			System.out.println("Database is Not Connected!");
		}
	}

}
