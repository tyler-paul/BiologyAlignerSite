package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.daos.ISequenceDAO;
import tylerpaul.bio.service.ISequenceManager;

@Component
public class SetupDeleteSequenceCommand implements ICommand {
	@Autowired
	private ISequenceManager sequenceManager;

	public SetupDeleteSequenceCommand() {
		super();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("sequences", sequenceManager.getSequences(request.getUserPrincipal().getName()));
		return "/jsp/deleteSequences.jsp";
	}

}
