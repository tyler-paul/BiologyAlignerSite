package tylerpaul.bio.commands;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.daos.IAlignmentDAO;
import tylerpaul.bio.models.SequenceAlignment;
import tylerpaul.bio.models.SequenceAlignmentPart;
import tylerpaul.bio.service.IAlignmentManager;

@Component
public class ViewMultipleAlignmentCommand implements ICommand {
	@Autowired
	private IAlignmentManager alignmentManager;
	
	public ViewMultipleAlignmentCommand() {
		super();
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int alignmentID = Integer.parseInt(request.getParameter("id"));
		SequenceAlignment alignment = alignmentManager.getAlignment(alignmentID);
		
		List<String> taxonNames = new ArrayList<String>();
		for (SequenceAlignmentPart part : alignment.getParts()) {
			taxonNames.add(part.getTaxon());
		}
		
		request.setAttribute("sequences", alignment.getPartStrings());
		request.setAttribute("description", alignment.getDescription());
		request.setAttribute("taxonNames", taxonNames);
		request.setAttribute("parts", alignment.getParts());
		return "/jsp/viewMultipleAlignmentById.jsp";
	}

}
