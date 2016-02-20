package com.su.handlers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.su.dtos.ListJobPostingDTO;
/**
 * JobPosterHandler- handles the requests from the servlet related to job postings and 
 * connects with the database to query for output
 * @author Shreyas and Prateek
 *
 */
public class JobPosterHandler {
	
	/**Inserts the job posting data into the database table 
	 * @param jobPosting object of ListJobPostingDTO type containing posted job details
	 * @return true, if inserted into the table
	 */
	public boolean insertInfo(ListJobPostingDTO jobPosting){
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try {
			
			properties.load(classLoader.getResourceAsStream("config.properties"));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		
		Connection conn = null;
		Statement stmt  = null;		
		try {
			String insertPostingQuery = "INSERT INTO JobPostingTb(CompanyName, JobTitle, JobDescription, Location,DatePosted, Contact) VALUES(\'"
			+ jobPosting.getCompanyName()
			+ "\',\'"
			+ jobPosting.getJobTitle()
			+ "\',\'"
			+ jobPosting.getJobDescription()
			+ "\',\'"
			+ jobPosting.getLocation()
			+ "\',\'" + jobPosting.getDatePosted() + "\',\'" + jobPosting.getContact() + "\')";
			//Register JDBC Driver:
			Class.forName(properties.getProperty("JDBC_DRIVER"));			
			//Connecting to database
			conn = DriverManager.getConnection(properties.getProperty("DB_URL"), properties.getProperty("user"), properties.getProperty("password"));
			stmt = conn.createStatement();
			stmt.executeUpdate(insertPostingQuery);
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

	/**Retrieve the email-Ids from the table from database
	 * @param fetchEmailQuery Query to retrieve the emailIds
	 * @return ArrayList of Email Ids
	 */
	public ArrayList<String> fetchJobseekerEmails(String fetchEmailQuery) {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try {
			
			properties.load(classLoader.getResourceAsStream("config.properties"));
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Connection conn = null;
		Statement stmt = null;
		ArrayList<String> listEmails = new ArrayList<String>();
		try {
			Class.forName(properties.getProperty("JDBC_DRIVER"));
			//Connecting to database
			conn = DriverManager.getConnection(properties.getProperty("DB_URL"), properties.getProperty("user"), properties.getProperty("password"));
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(fetchEmailQuery);

			while (rs.next()) {
				listEmails.add(rs.getString("Email"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				stmt.close();
				conn.close();
				//Database Connection closed
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return listEmails;
	}
	/**
	 * Sending of emails to the email list using JavaMail API
	 * @param emailList email list to which mail has to be sent
	 */
	public void sendEmails(ArrayList<String> emailList) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.stmp.user", "wbrpsproject@gmail.com");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						String username = "wbrpsproject@gmail.com";
						String password = "shreyasprateek";
						return new PasswordAuthentication(username, password);
					}
				});

		String from = "wbrpsproject@gmail.com";
		String subject = "New Job Posted!!";
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from));

			InternetAddress[] addressTo = new InternetAddress[emailList.size()];
			for (int i = 0; i < emailList.size(); i++) {
				addressTo[i] = new InternetAddress(emailList.get(i));
			}
			msg.setRecipients(RecipientType.BCC, addressTo);
			msg.setSubject(subject);
			StringBuilder builder = new StringBuilder();		
			builder.append("Dear Jobseeker ,");
			builder.append("\n\n");
			builder.append("A new job matching your profile has been posted. ");
			builder.append("\n\n");
			builder.append("All the best,");
			builder.append("\n\n");
			builder.append("Web-based Job Notification System team");
			msg.setText(builder.toString());
			Transport.send(msg);	//sending of email
		} catch (Exception exc) {
			System.out.println(exc);
		}
	}

}
