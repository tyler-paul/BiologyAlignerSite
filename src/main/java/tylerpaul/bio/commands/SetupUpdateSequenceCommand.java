package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.models.Sequence;
import tylerpaul.bio.service.ISequenceManager;

@Component
public class SetupUpdateSequenceCommand implements ICommand {
	@Autowired
	private ISequenceManager sequenceManager;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int sequenceID = Integer.parseInt(request.getParameter("id"));
		
		Sequence sequence = sequenceManager.getSequence(sequenceID);
		request.setAttribute("oldDescription", sequence.getDescription());
		request.setAttribute("oldData", sequence.getData());
		request.setAttribute("id", sequenceID);
		
		return "/jsp/updateSequence.jsp";
	}

}
