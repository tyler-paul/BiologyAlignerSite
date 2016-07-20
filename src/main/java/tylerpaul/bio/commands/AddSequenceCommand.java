package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.models.Sequence;
import tylerpaul.bio.service.ISequenceManager;

@Component
public class AddSequenceCommand implements ICommand {
	@Autowired
	private ISequenceManager sequenceManager;
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String data = request.getParameter("data");
		
		//make sure only A, C, G, or T is in the string
		for (int i = 0; i < data.length(); i++) {
			if ("ACGT".indexOf(data.charAt(i)) == -1) {
				System.out.println("error:" + data.charAt(i));
				return "/jsp/error.jsp";
			}
		}
		
		String description = request.getParameter("description");
		
		Sequence sequence = new Sequence();
		sequence.setDescription(description);
		sequence.setData(data);
		sequenceManager.addSequenceToUser(sequence, request.getUserPrincipal().getName());
		
		return "/jsp/index.jsp";
	}
}