package org.EightAssignment;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;
import java.util.InputMismatchException;

public class OrderByAmountAndDate {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);

		try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccompanydb",
				"Shambhu Sharma", "Sh@mbhu26")) {

			if (connect != null) {
				System.out.println("Database is Connected Successfully...");

				try (PreparedStatement stmt = connect.prepareStatement(
						"SELECT order_id, customer_id, order_date, total_amount, order_status FROM orders WHERE order_status = ? ORDER BY total_amount DESC, order_date DESC")) {
					Scanner input = new Scanner(System.in);

					System.out.print("Enter Status : ");
					String status = input.nextLine();

					stmt.setString(1, status);

					ResultSet rs = stmt.executeQuery();
					boolean flag = false;
					while (rs.next()) {
						flag = true;
						System.out.println(rs.getInt("order_id") + "\t" + rs.getInt("customer_id") + "\t"
								+ rs.getString("order_date") + "\t" + rs.getInt("total_amount") + "\t"
								+ rs.getString("order_status"));
					}

					if (!flag) {
						System.out.println("Data Not Found!");
					}

				} catch (SQLException e) {
					System.out.println("Database Error : " + e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println("Invalid input! Please enter valid input.");
				}

			} else {
				System.out.println("Database is Not Connected!");
			}

		} catch (SQLException e) {
			System.out.println("Database Error : " + e.getMessage());
		}

	}

}
