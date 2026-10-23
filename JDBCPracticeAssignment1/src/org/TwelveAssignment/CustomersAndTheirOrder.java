package org.TwelveAssignment;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Scanner;

public class CustomersAndTheirOrder {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccompanydb",
				"Shambhu Sharma", "Sh@mbhu26")) {
			if (connect != null) {
				System.out.println("Database is Connected Successfully...");

				try (PreparedStatement stmt = connect.prepareStatement(
						"SELECT customers.customer_name, customers.email, orders.order_id, orders.order_date, orders.total_amount, orders.order_status FROM customers INNER JOIN orders ON customers.customer_id = orders.customer_id WHERE customers.customer_id = ? ORDER BY orders.order_date DESC")) {

					Scanner input = new Scanner(System.in);

					System.out.print("Enter Customer ID : ");
					int customerId = input.nextInt();

					stmt.setInt(1, customerId);

					try (ResultSet rs = stmt.executeQuery()) {
						boolean flag = false;
						while (rs.next()) {
							flag = true;
							System.out.println(rs.getString("customer_name") + "\t" + rs.getString("email") + "\t"
									+ rs.getInt("order_id") + "\t" + rs.getDate("order_date") + "\t"
									+ rs.getBigDecimal("total_amount") + "\t" + rs.getString("order_status"));
						}

						if (!flag) {
							System.out.println("No orders found for customer ID : " + customerId);
						}
					}

				} catch (java.util.InputMismatchException ex) {
					System.out.println("Invalid input! Please enter a numeric customer ID.");
				}

			} else {
				System.out.println("Database is Not Connected!");
			}
		} catch (java.sql.SQLException ex) {
			System.out.println("Database Error : " + ex.getMessage());
		}
	}

}
