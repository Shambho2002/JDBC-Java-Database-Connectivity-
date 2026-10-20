package org.SecondJDBCConnectionApplication;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
public class JDBCConnectionApplication {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstjdbc","Shambhu Sharma","Sh@mbhu26");
		
		if(connect != null) {
			System.out.println("Database is Connected Successfully...");
		}
		else {
			System.out.println("Something went wrong!");
			System.out.println("Database is not Connected...!");
		}
	}

}
