package org.SeventhAssignment;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;

public class SearchEmployee {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccompanydb",
				"Shambhu Sharma", "Sh@mbhu26")) {
			if (connect != null) {
				System.out.println("Database is Connected Successfully...");
				try (PreparedStatement stmt = connect.prepareStatement("SELECT * FROM employees WHERE city = ? ORDER BY employee_name ASC")) {
					Scanner input = new Scanner(System.in);

					System.out.print("Enter City : ");
					String city = input.nextLine();

					stmt.setString(1, city);
					ResultSet rs = stmt.executeQuery();
					boolean flag = false;
					while (rs.next()) {
						flag = true;
						System.out.println(rs.getInt("employee_id") + "\t" + rs.getString("employee_name") + "\t"
								+ rs.getString("email") + "\t" + rs.getDouble("salary") + "\t\t"
								+ rs.getInt("department_id") + "\t" + rs.getDate("joining_date") + "\t"
								+ rs.getString("city"));
					}
					
					if(!flag) {
						System.out.println("No Employee Belongs To "+city+".");
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
