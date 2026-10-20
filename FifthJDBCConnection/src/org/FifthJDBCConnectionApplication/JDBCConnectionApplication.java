package org.FifthJDBCConnectionApplication;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

import java.util.Scanner;
public class JDBCConnectionApplication {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstjdbc","Shambhu Sharma","Sh@mbhu26");
		if(connect != null) {
			System.out.println("Database is Connected Successfully...");
			Scanner input = new Scanner(System.in);
			
			System.out.print("Enter Employee ID : ");
			int id = input.nextInt();
			
			Statement stmt = connect.createStatement();
			int result = stmt.executeUpdate("delete from employee where eid = "+id+"");
			if(result != 0) {
				System.out.println("Employee has been deleted Successfully...");
			}
			else {
				System.out.println("Something Went Wrong!");
				System.out.println("Employee not deleted...!");
			}
		}
		else {
			System.out.println("Database is not Connected...!");
		}
	}

}
