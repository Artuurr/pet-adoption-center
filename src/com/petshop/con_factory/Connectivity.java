package com.petshop.con_factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connectivity
{	
	public static Connection Connect() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop"
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sabree10");
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			
			return null;
		}
	}
}
