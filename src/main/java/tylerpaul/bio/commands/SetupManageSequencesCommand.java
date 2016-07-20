package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.service.ISequenceManager;

@Component
public class SetupManageSequencesCommand implements ICommand {
	@Autowired
	private ISequenceManager sequenceManager;

	public SetupManageSequencesCommand() {
		super();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("sequences", sequenceManager.getSequences(request.getUserPrincipal().getName()));
		return "/jsp/manageSequences.jsp";
	}

}
