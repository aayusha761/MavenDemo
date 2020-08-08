package com.lti.dao.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnManager {
	public static Connection connect() {
		try {
			Properties dbProps = new Properties();
			//how to remove hardcoded filename -- we need to pass the file name as env variable
			//one option is to use VM arguments using -D option
//			dbProps.load(new FileReader("dev-db.properties"));
			dbProps.load(ConnManager.class.getClassLoader().getResourceAsStream("dev-db.properties"));
			Class.forName(dbProps.getProperty("driverName"));
			return DriverManager.getConnection(dbProps.getProperty("url"), dbProps.getProperty("user"), dbProps.getProperty("pass"));
		}
		catch (ClassNotFoundException | IOException| SQLException e) {
			e.printStackTrace();	//should be thrown rather
			return null;			//should throw some user defined exception
		}
		
	}
}