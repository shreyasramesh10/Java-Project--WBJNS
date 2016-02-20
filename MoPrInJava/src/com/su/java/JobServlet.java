package com.su.java;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.su.dtos.JobSeekerDTO;
import com.su.handlers.JobSeekerHandler;
import java.io.PrintWriter;

/**
 * JobServlet- handles the requests from the jsp page related to job seekers and 
 * connects with the JobSeekerHandler to handle the requests to DB
 * @author Shreyas and Prateek
 *
 */
public class JobServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		JobSeekerDTO users = new JobSeekerDTO();
		try{
			// reading the user input
			users.setFirstName(request.getParameter("firstName"));
			users.setLastName(request.getParameter("lastName"));
			users.setEmailId(request.getParameter("emailId"));
			users.setExperience(request.getParameter("experience"));
			users.setPhoneNo(request.getParameter("phoneNumber"));
			
			JobSeekerHandler jobSeekerhandler = new JobSeekerHandler();
			boolean isInsertSuccessful = jobSeekerhandler.insertInfo(users);			
			
			String skills = request.getParameter("skills");
			String[] skillArr = skills.split(",");
		
			if(skillArr.length > 0){
				String jobSeekerIdQuery = "select id from JobSeekerTb order by id desc limit 1";
				int jobSeekerId = jobSeekerhandler.fetchJobSeekerId(jobSeekerIdQuery);			
				StringBuilder sb1 = new StringBuilder();
				sb1.append("INSERT INTO JobSeekerSkillsTb(JobSeekerId, Skills) VALUES");
				for(int i =0 ; i < skillArr.length ; i++){				
					if(i >= 1){
						sb1.append(",");
					}
					sb1.append("(");
					sb1.append(jobSeekerId);
					sb1.append(",");
					sb1.append("\'"+skillArr[i].toLowerCase().trim()+"\'");
					sb1.append(")");				
				}	
				String insertSeekerSkillsQuery = sb1.toString();
				boolean isInsertSkillsSuccessful = jobSeekerhandler.insertSkillsInfo(insertSeekerSkillsQuery);	
				
			}			
			if (isInsertSuccessful) {
				out.write("User \""+users.getFirstName()+" "+users.getLastName()+"\" registered successfully");
				out.write("<br/>");
				out.write("<a href=\"index.jsp\">Back</a>");
			}else{
				out.write("<p>Insert unsucessful</p>");
				out.write("<br/>");
				out.write("<a href=\"index.jsp\">Back</a>");
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			out.close();
		}
	}	
}
