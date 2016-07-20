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
import tylerpaul.bio.models.SequenceAlignment;
import tylerpaul.bio.models.SequenceAlignmentPart;
import tylerpaul.bio.service.IAlignmentManager;
import tylerpaul.bio.util.ITreePrinter;
import tylerpaul.bio.util.TreePrinter;

@Component
public class CreateTreeCommand implements ICommand {
	@Autowired
	private IAlignmentManager alignmentManager;
	
	public CreateTreeCommand() {
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String outgroup = request.getParameter("outgroup");
		String title = request.getParameter("title");
		if (title == null)
			title = "";
		
		SequenceAlignment alignment = alignmentManager.getAlignment(id);
		
		List<Taxon> taxons = new ArrayList<Taxon>();
		for (SequenceAlignmentPart part : alignment.getParts()) {
			//System.out.println(part.getTaxon()+ " " + part.getData());
			taxons.add(new Taxon(part.getTaxon(), part.getData()));
		}
		ITreeBuilder builder = new NJTreeBuilder();
		TreeNode root = builder.build(taxons);

		root = TreeRerooter.reroot(root, outgroup);
		
		
		ITreePrinter printer = new TreePrinter();
		String path = "";
		try {
			path = "/var/lib/openshift/577c08412d5271149c00001e/jbossews/tmp/trees/" + id + ".png";
//this.getClass().getResource("/").getPath() + "/trees/" + id + ".png";//request.getServletContext().getRealPath(request.getContextPath()) + "/trees/" + id + ".png";
			System.out.println("about to save to " + path);
			File file = new File(path);
			System.out.println("saving to " + file.getAbsolutePath());
			printer.print(root, file, title);
			//System.out.println(file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			return "/jsp/error.jsp";
		}
		
		request.setAttribute("treeFileName", "/trees/" + id + ".png");
		return "/jsp/viewTree.jsp";
	}

}
