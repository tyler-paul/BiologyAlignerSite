package tylerpaul.bio.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.algs.phylogeny.TreeRerooter;
import tylerpaul.bio.algs.phylogeny.ITreeBuilder;
import tylerpaul.bio.algs.phylogeny.NJTreeBuilder;
import tylerpaul.bio.algs.phylogeny.Taxon;
import tylerpaul.bio.algs.phylogeny.TreeNode;
import tylerpaul.bio.daos.IAlignmentDAO;
import tylerpaul.bio.models.SequenceAlignment;
import tylerpaul.bio.models.SequenceAlignmentPart;
import tylerpaul.bio.service.IAlignmentManager;
import tylerpaul.bio.util.ITreePrinter;
import tylerpaul.bio.util.TreePrinter;

@Component
public class SetupOutgroupCommand implements ICommand {
	@Autowired
	private IAlignmentManager alignmentManager;
	
	public SetupOutgroupCommand() {
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		SequenceAlignment alignment = alignmentManager.getAlignment(id);
		
		List<String> taxonNames = new ArrayList<String>();
		for (SequenceAlignmentPart part : alignment.getParts()) {
			taxonNames.add(part.getTaxon());
		}
	
		
		request.setAttribute("taxonNames", taxonNames);
		request.setAttribute("alignmentID", id);
		return "/jsp/setupOutgroup.jsp";
	}

}
