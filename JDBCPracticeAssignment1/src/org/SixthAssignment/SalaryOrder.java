package org.SixthAssignment;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Scanner;

public class SalaryOrder {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccompanydb",
				"Shambhu Sharma", "Sh@mbhu26")) {

			if (connect != null) {
				System.out.println("Database is Connected Successfully...");

				try {
					String query = "SELECT * FROM employees ORDER BY salary desc";
					PreparedStatement stmt = connect.prepareStatement(query);

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						System.out.println(rs.getInt("employee_id") + "\t" + rs.getString("employee_name") + "\t"
								+ rs.getString("email") + "\t" + rs.getInt("salary") + "\t" + rs.getInt("department_id")
								+ "\t" + rs.getDate("joining_date") + "\t" + rs.getString("city"));
					}

				} catch (SQLException e) {
					System.out.println("Database Error : " + e.getMessage());
				}

			} else {
				System.out.println("Database is Not Connected!");
			}

		} catch (SQLException e) {
			System.out.println("Database Error : " + e.getMessage());
		}
	}

}
