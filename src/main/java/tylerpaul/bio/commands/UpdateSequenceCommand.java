package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.service.ISequenceManager;

@Component
public class UpdateSequenceCommand implements ICommand {
	@Autowired
	private ISequenceManager sequenceManager;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int sequenceID = Integer.parseInt(request.getParameter("id"));
		String newData = request.getParameter("newData");
		String newDescription = request.getParameter("newDescription");
		
		//make sure only A, C, G, or T is in the string
		for (int i = 0; i < newData.length(); i++) {
			if ("ACGT".indexOf(newData.charAt(i)) == -1) {
				System.out.println("error:" + newData.charAt(i));
				return "/jsp/error.jsp";
			}
		}
		
		sequenceManager.updateSequence(sequenceID, newDescription, newData);
		
		return "/jsp/index.jsp";
	}
}
