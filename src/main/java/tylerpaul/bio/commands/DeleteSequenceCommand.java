package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.daos.ISequenceDAO;
import tylerpaul.bio.models.SingleAlignment;
import tylerpaul.bio.service.ISequenceManager;

@Component
public class DeleteSequenceCommand implements ICommand {
	@Autowired
	private ISequenceManager sequenceManager;
	
	public DeleteSequenceCommand() {
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int sequenceID = Integer.parseInt(request.getParameter("id"));
		sequenceManager.deleteSequence(sequenceID);
		return "/Controller?action=SetupManageSequences";
	}

}
