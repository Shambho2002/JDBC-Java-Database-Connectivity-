package org.NinethAssignment;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeesWithDeptName {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);

		try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccompanydb",
				"Shambhu Sharma", "Sh@mbhu26")) {

			if (connect != null) {
				System.out.println("Database is Connected Successfully...");

				try (PreparedStatement stmt = connect.prepareStatement(
						"SELECT employee_id, employee_name, salary, department_name FROM employees INNER JOIN departments ON employees.department_id = departments.department_id ORDER BY departments.department_name ASC, employees.employee_name ASC")) {

					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						System.out.println(rs.getInt("employee_id")+ "\t" + rs.getString("employee_name") + "\t"
								+ rs.getBigDecimal("salary") + "\t" + rs.getString("department_name"));
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
