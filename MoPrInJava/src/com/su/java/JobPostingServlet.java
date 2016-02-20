package com.su.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.su.dtos.ListJobPostingDTO;
import com.su.handlers.JobPosterHandler;

/**
 * JobPostingServlet- handles the requests from the jsp page related to job
 * postings and connects with the JobPosterHandler to handle the requests to DB
 * Extends HttpServlet and implements Runnable
 * 
 * @author Shreyas and Prateek
 *
 */
public class JobPostingServlet extends HttpServlet implements Runnable {

	private static final long serialVersionUID = 1L;
	public static String[] skillArr = new String[15];
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {

			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datePosted = sdf.format(dt);	
			
			ListJobPostingDTO jobPosting = new ListJobPostingDTO();
			jobPosting.setJobTitle(request.getParameter("jobTitle"));
			jobPosting.setCompanyName(request.getParameter("companyName"));
			jobPosting.setLocation(request.getParameter("location"));
			jobPosting.setJobDescription(request.getParameter("jobDescription"));
			jobPosting.setContact(request.getParameter("contact"));
			jobPosting.setDatePosted(datePosted);
			
			String skills = request.getParameter("skills");
			skillArr = skills.split(",");
			
			
			JobPosterHandler jobPosterhandler = new JobPosterHandler();
			boolean isInsertSuccessful = jobPosterhandler.insertInfo(jobPosting);
			
			//Sending of emails on a separate Thread
			Thread sendEmailThread = new Thread(this);
			sendEmailThread.start();
			sendEmailThread.join();			
			
			if (isInsertSuccessful) {
				System.out.println("Insert Successful");

				out.write("<p>The Job Posting with title\"" + jobPosting.getJobTitle()
						+ " in the company " + jobPosting.getCompanyName()
						+ "\" was posted successfully</p>");
				out.write("<br/>");
				out.write("<a href=\"index.jsp\">Back</a>");
			} else {
				out.write("<p>Insert unsucessful</p>");
				out.write("<br/>");
				out.write("<a href=\"index.jsp\">Back</a>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	//run method implemented to send emailIds on a separate thread
	@Override
	public void run() {
		// TODO Auto-generated method stub
		JobPosterHandler jobPosterHandler = new JobPosterHandler();
		StringBuilder sb2 = new StringBuilder();
		sb2.append("select distinct(Email) from JobSeekerTb jp INNER JOIN jobseekerskillsTb js ON jp.Id = js.JobSeekerId ");
		sb2.append(" WHERE ");
		for (int i = 0; i < skillArr.length; i++) {
			if (i >= 1) {
				sb2.append(" OR ");
			}
			sb2.append("js.skills = \'" + skillArr[i].toLowerCase().trim() + "\'");
		}
		ArrayList<String> jobSeekerEmails = jobPosterHandler
				.fetchJobseekerEmails(sb2.toString());
		jobPosterHandler.sendEmails(jobSeekerEmails);			
				
	}
}
