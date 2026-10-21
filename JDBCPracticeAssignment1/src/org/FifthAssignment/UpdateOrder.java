package org.FifthAssignment;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Scanner;

public class UpdateOrder {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccompanydb",
				"Shambhu Sharma", "Sh@mbhu26");) {
			if (connect != null) {
				System.out.println("Database is Connected Successfully...");
				String query = "UPDATE orders SET order_status = ? WHERE order_id = ?";

				try {
					PreparedStatement stmt = connect.prepareStatement(query);

					Scanner input = new Scanner(System.in);

					System.out.print("Enter Order Id : ");
					int orderId = input.nextInt();
					input.nextLine();

					System.out.print("Enter Order Status : ");
					String status = input.nextLine();

					stmt.setString(1, status);
					stmt.setInt(2, orderId);

					int result = stmt.executeUpdate();

					if (result > 0) {
						System.out.println("Order Status Updated Successfully...");
						try {
							ResultSet rs = stmt.executeQuery("SELECT * FROM orders ORDER BY order_id");
							while (rs.next()) {
								System.out.println(rs.getInt("order_id") + "\t" + rs.getInt("customer_id") + "\t"
										+ rs.getDate("order_date") + "\t" + rs.getDouble("total_amount") + "\t"
										+ rs.getString("order_status"));
							}
						} catch (SQLException e) {
							System.out.println("Database Error : " + e.getMessage());
						}

					} else {
						System.out.println("Order Status Not Updated!");
					}
				} catch (SQLException e) {
					System.out.println("Database Error : " + e.getMessage());
				} catch (java.util.InputMismatchException e) {
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
