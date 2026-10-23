package org.EleventhAssignment;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Scanner;

public class EmpAssignProject {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccompanydb",
				"Shambhu Sharma", "Sh@mbhu26")) {
			if (connect != null) {
				System.out.println("Database is Connected Successfully...");

				try (PreparedStatement stmt = connect.prepareStatement(
						"SELECT employees.employee_name, projects.project_name, employee_projects.hours_worked, employee_projects.assigned_date FROM employees INNER JOIN employee_projects ON employees.employee_id = employee_projects.employee_id INNER JOIN projects ON employee_projects.project_id = projects.project_id where projects.project_id = ? ORDER BY employees.employee_name ASC")) {

					Scanner input = new Scanner(System.in);

					System.out.print("Enter ID : ");
					int pid = input.nextInt();

					stmt.setInt(1, pid);

					try (ResultSet rs = stmt.executeQuery()) {
						boolean found = false;
						while (rs.next()) {
							found = true;
							System.out.println(rs.getString("employee_name") + "\t" + rs.getString("project_name")
									+ "\t" + rs.getInt("hours_worked") + "\t" + rs.getDate("assigned_date"));
						}
						if (!found) {
							System.out.println("No employees assigned to project ID : " + pid);
						}
					} catch (java.sql.SQLException ex) {
						System.out.println("Database Error : " + ex.getMessage());
					}

				} catch (java.sql.SQLException ex) {
					System.out.println("Database Error : " + ex.getMessage());
				} catch (java.util.InputMismatchException ex) {
					System.out.println("Invalid input! Please enter a numeric project ID.");
				}

			} else {
				System.out.println("Database is Not Connected!");
			}
		} catch (java.sql.SQLException ex) {
			System.out.println("Database Error : " + ex.getMessage());
		}
	}

}
