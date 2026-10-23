package org.ThirteenAssignment;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustWithoutOrder {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);

		String url = "jdbc:mysql://localhost:3306/jdbccompanydb";
		String username = "Shambhu Sharma";
		String password = "Sh@mbhu26";

		String query = "SELECT customers.customer_id, customers.customer_name, customers.email, customers.city FROM customers LEFT JOIN orders ON customers.customer_id = orders.customer_id WHERE orders.order_id IS NULL";

		try (Connection connect = DriverManager.getConnection(url, username, password)) {

			if (connect != null) {
				System.out.println("Database is Connected Successfully...");

				PreparedStatement stmt = connect.prepareStatement(query);

				try (ResultSet rs = stmt.executeQuery()) {
					boolean flag = false;
					while (rs.next()) {
						flag = true;
						System.out.println(rs.getInt("customer_id") + "\t" + rs.getString("customer_name") + "\t"
								+ rs.getString("email") + "\t" + rs.getString("city"));
					}
					
					if(!flag) {
						System.out.println("All customers have orders!");
					}
				}

			} else {
				System.out.println("Database is not Connected!");
			}

		} catch (java.sql.SQLException ex) {
			System.out.println("Database Error : " + ex.getMessage());
		}

	}

}
