package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.daos.IAlignmentDAO;
import tylerpaul.bio.service.IAlignmentManager;

@Component
public class DeleteAlignmentCommand implements ICommand {
	@Autowired
	private IAlignmentManager alignmentManager;
	
	public DeleteAlignmentCommand() {
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int alignmentID = Integer.parseInt(request.getParameter("id"));
		alignmentManager.deleteAlignment(alignmentID);
		return "/Controller?action=SetupManageAlignments";
	}

}
