package com.su.handlers;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import com.su.dtos.JobSeekerDTO;
/**
 * JobSeekerHandler- handles the requests from the servlet related to users subscribed and 
 * connects with the database to query for output
 * @author Shreyas and Prateek
 *
 */
public class JobSeekerHandler {

	/**Inserts the job seekers information into the database table 
	 * @param usersInfo object of JobSeekerDTO type containing users details encapsulated
	 * @return true, if inserted into the table
	 */
	public boolean insertInfo(JobSeekerDTO usersInfo){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try {
			
			properties.load(classLoader.getResourceAsStream("config.properties"));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection conn = null;
		Statement stmt  = null;		
		String insertQuery = "INSERT INTO JobSeekerTb(FirstName,LastName,Email,Experience,Phone) VALUES(\'"
				+ usersInfo.getFirstName()
				+ "\',\'"
				+ usersInfo.getLastName()
				+ "\',\'"
				+ usersInfo.getEmailId()
				+ "\',\'" + usersInfo.getExperience() + "\',\'" + usersInfo.getPhoneNo() + "\')";
		
		try {
			Class.forName(properties.getProperty("JDBC_DRIVER"));			
			//Connecting to database
			conn = DriverManager.getConnection(properties.getProperty("DB_URL"), properties.getProperty("user"), properties.getProperty("password"));
			stmt = conn.createStatement();
			stmt.executeUpdate(insertQuery);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				stmt.close();
				conn.close();
				//Database Connection closed
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;	
	}

	/**Obtains the Id of the job seeker
	 * @param seekIdQuery string Query for obtaining the Id for the job seeker
	 * @return int, ID of the jobseeker
	 */
	public int fetchJobSeekerId(String seekIdQuery) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try {
			
			properties.load(classLoader.getResourceAsStream("config.properties"));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Connection conn = null;
		Statement stmt = null;
		int jobId = 0;
		try {
			
			Class.forName(properties.getProperty("JDBC_DRIVER"));
			//Connecting to database
			conn = DriverManager.getConnection(properties.getProperty("DB_URL"), properties.getProperty("user"), properties.getProperty("password"));
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(seekIdQuery);
			while (rs.next()) {
				jobId = rs.getInt("Id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				//Database Connection closed
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		return jobId;
	}

	public boolean insertSkillsInfo(String insertSkillsQuery) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try {
			
			properties.load(classLoader.getResourceAsStream("config.properties"));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Connection conn = null;
		Statement stmt = null;
		try {
			
			Class.forName(properties.getProperty("JDBC_DRIVER"));
			//Connecting to database
			conn = DriverManager.getConnection(properties.getProperty("DB_URL"), properties.getProperty("user"), properties.getProperty("password"));
			stmt = conn.createStatement();
			stmt.executeUpdate(insertSkillsQuery);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				stmt.close();
				conn.close();
				//"Database Connection closed
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
}
