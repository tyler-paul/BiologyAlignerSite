package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.daos.IAlignmentDAO;
import tylerpaul.bio.daos.SequenceDAO;
import tylerpaul.bio.service.IAlignmentManager;

@Component
public class SetupDeleteAlignmentCommand implements ICommand {
	@Autowired
	private IAlignmentManager alignmentManager;

	public SetupDeleteAlignmentCommand() {
		super();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("alignments", alignmentManager.getAlignments(request.getUserPrincipal().getName()));
		return "/jsp/deleteAlignments.jsp";
	}

}
