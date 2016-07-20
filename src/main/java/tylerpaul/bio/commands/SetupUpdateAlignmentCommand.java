package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.models.Sequence;
import tylerpaul.bio.models.SequenceAlignment;
import tylerpaul.bio.service.IAlignmentManager;
import tylerpaul.bio.service.ISequenceManager;

@Component
public class SetupUpdateAlignmentCommand implements ICommand {
	@Autowired
	private IAlignmentManager alignmentManager;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int alignmentID = Integer.parseInt(request.getParameter("id"));
		
		SequenceAlignment alignment = alignmentManager.getAlignment(alignmentID);
		request.setAttribute("oldDescription", alignment.getDescription());
		request.setAttribute("oldTaxonNames", alignment.getPartNames());
		request.setAttribute("id", alignmentID);
		
		return "/jsp/updateAlignment.jsp";
	}

}
