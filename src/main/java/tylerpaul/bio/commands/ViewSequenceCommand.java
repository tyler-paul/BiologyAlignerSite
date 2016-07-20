package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.daos.ISequenceDAO;
import tylerpaul.bio.models.Sequence;
import tylerpaul.bio.service.ISequenceManager;

@Component
public class ViewSequenceCommand implements ICommand {
	@Autowired
	private ISequenceManager sequenceManager;

	public ViewSequenceCommand() {
		super();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int sequenceId = Integer.parseInt(request.getParameter("id"));
		Sequence sequence = sequenceManager.getSequence(sequenceId);
		request.setAttribute("description", sequence.getDescription());
		request.setAttribute("sequences", sequence.getData());
		
		return "/jsp/viewSequenceById.jsp";
	}

}
