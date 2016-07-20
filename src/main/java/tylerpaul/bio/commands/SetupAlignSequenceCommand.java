package tylerpaul.bio.commands;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.daos.ISequenceDAO;
import tylerpaul.bio.models.Sequence;
import tylerpaul.bio.service.ISequenceManager;

@Component
public class SetupAlignSequenceCommand implements ICommand {
	@Autowired
	private ISequenceManager sequenceManager;
	
	public SetupAlignSequenceCommand() {
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		List<Sequence> sequences = sequenceManager.getSequences(request.getUserPrincipal().getName());
		request.setAttribute("sequences", sequences);
		return "/jsp/alignSequences.jsp";
	}
}
