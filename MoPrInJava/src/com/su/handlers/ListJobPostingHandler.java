package com.su.handlers;
import java.util.List;
import com.su.dtos.ListJobPostingDTO;

/**
 * ListJobPostingHandler - interface for listing the jobs posted
 * @author Shreyas
 *
 */
public interface ListJobPostingHandler {
	/** 
	 * Abstract method to get the list of jobs posted
	 * @param fetchPostings
	 * @return list of jobs posted
	 */
	public List<ListJobPostingDTO> fetchPostingsList(String fetchPostings);
	
}
