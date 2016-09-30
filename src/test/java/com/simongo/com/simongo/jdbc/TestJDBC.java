package com.simongo.com.simongo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by simon on 30/09/16.
 */
public class TestJDBC {

	public static void main(String[] args){

		String url = "jdbc:postgresql://localhost:5432/tests";
		String user = "sylvain";
		String password = "";

		try {

			System.out.println("Testing connection to database " + url);
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connection is working =)");

		}  catch (Exception e){
			e.printStackTrace();
		}
	}
}
