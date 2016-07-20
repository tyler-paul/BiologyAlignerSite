package tylerpaul.bio.commands;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.service.IAlignmentManager;

@Component
public class UpdateAlignmentCommand implements ICommand {
	@Autowired
	private IAlignmentManager alignmentManager;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int alignmentID = Integer.parseInt(request.getParameter("id"));
		String newDescription = request.getParameter("newDescription");
		String[] newTaxonNames = request.getParameterValues("newTaxonNames");
		
		alignmentManager.updateAlignment(alignmentID, newDescription, newTaxonNames);
		
		return "/jsp/index.jsp";
	}

}
