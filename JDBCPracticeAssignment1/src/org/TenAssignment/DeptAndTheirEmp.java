package org.TenAssignment;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeptAndTheirEmp {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);

		try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccompanydb",
				"Shambhu Sharma", "Sh@mbhu26")) {

			if (connect != null) {
				System.out.println("Database is Connected Successfully...");

				try (PreparedStatement stmt = connect.prepareStatement(
						"SELECT departments.department_id, departments.department_name, employees.employee_id, employees.employee_name FROM departments LEFT JOIN employees ON departments.department_id = employees.department_id ORDER BY departments.department_name ASC");
						ResultSet rs = stmt.executeQuery()) {

					while (rs.next()) {
						System.out.println(rs.getInt("department_id") + "\t" + rs.getString("department_name") + "\t"
								+ rs.getInt("employee_id") + "\t" + rs.getString("employee_name"));
					}

				} catch (java.sql.SQLException e) {
					System.out.println("Database Error : " + e.getMessage());
				}

			} else {
				System.out.println("Database is Not Connected!");
			}

		} catch (java.sql.SQLException e) {
			System.out.println("Database Error : " + e.getMessage());
		}

	}

}
