package com.su.java;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.su.dtos.ListJobPostingDTO;
import com.su.handlers.ListJobPostingHandler;

/**
 * ListJobPostingsServlet- handles the requests from the jsp page related to display of 
 * job postings and implements the ListJobPostingHandler interface in an anonymous class
 * @author Shreyas and Prateek
 *
 */
public class ListJobPostingsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String fetchJobPostings = "select * from JobPostingTb";
		StringBuilder stringBuilder = new StringBuilder();
		try{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			final Properties properties = new Properties();
			try {
				properties.load(classLoader.getResourceAsStream("config.properties"));
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
			//Use of Anonymous class returning a list
			List<ListJobPostingDTO> listJobPostingDTO = new ListJobPostingHandler(){
				// set up jdbc
				
				public List<ListJobPostingDTO> fetchPostingsList(String fetchPostings){
					Connection conn = null;
					Statement stmt  = null;
					List<ListJobPostingDTO> listArray = new ArrayList<ListJobPostingDTO>();	
					try {
						//Register JDBC Driver:
						Class.forName(properties.getProperty("JDBC_DRIVER"));			
						//Connecting to database
						conn = DriverManager.getConnection(properties.getProperty("DB_URL"), properties.getProperty("user"), properties.getProperty("password"));
						stmt = conn.createStatement();
						ResultSet result = stmt.executeQuery(fetchPostings);
						
						while(result.next()){
							ListJobPostingDTO listJobPostingDTO = new ListJobPostingDTO();
							listJobPostingDTO.setCompanyName(result.getString("CompanyName"));
							listJobPostingDTO.setContact(result.getString("Contact"));	
							listJobPostingDTO.setDatePosted(result.getString("DatePosted"));
							listJobPostingDTO.setJobDescription(result.getString("JobDescription"));
							listJobPostingDTO.setJobTitle(result.getString("JobTitle"));
							listJobPostingDTO.setLocation(result.getString("Location"));
							listArray.add(listJobPostingDTO);
						}
						
					} catch (Exception e) {
						e.printStackTrace();			
					} 
					finally{
						try {
							stmt.close();
							conn.close();
							//Database Connection closed
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					return listArray;	
				}
			}.fetchPostingsList(fetchJobPostings);
			
			stringBuilder.append("<html>");
			stringBuilder.append("<link rel=\"stylesheet\" href=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css\"/>");				
			stringBuilder.append("<body><div class = \"table-responsive\"><Table class = \"table table-striped\" >");
			stringBuilder.append("<tr>");
			stringBuilder.append("<th>").append("Company Name").append("</th>");
			stringBuilder.append("<th>").append("Job Title").append("</th>");
			stringBuilder.append("<th>").append("Job Description").append("</th>");
			stringBuilder.append("<th>").append("Location").append("</th>");
			stringBuilder.append("<th>").append("Date Posted").append("</th>");
			stringBuilder.append("<th>").append("Contact").append("</th>");	
			stringBuilder.append("</tr>");
			for(ListJobPostingDTO ldp : listJobPostingDTO){		
				stringBuilder.append("<tr>");
				stringBuilder.append("<td>").append(ldp.getCompanyName()).append("</td>");
				stringBuilder.append("<td>").append(ldp.getJobTitle()).append("</td>");
				stringBuilder.append("<td>").append(ldp.getJobDescription()).append("</td>");
				stringBuilder.append("<td>").append(ldp.getLocation()).append("</td>");
				stringBuilder.append("<td>").append(ldp.getDatePosted()).append("</td>");
				stringBuilder.append("<td>").append(ldp.getContact()).append("</td>");
				stringBuilder.append("</tr>");
			}
			stringBuilder.append("</Table></div></body></html>");	
			out.write(stringBuilder.toString());
			out.write("<br/>");
			out.write("<a href=\"index.jsp\">Back</a>");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			out.close();
		}

	}
}
