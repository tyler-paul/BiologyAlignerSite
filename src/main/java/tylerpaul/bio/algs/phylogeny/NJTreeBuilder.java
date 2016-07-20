package tylerpaul.bio.algs.phylogeny;

import java.util.ArrayList;
import java.util.List;

public class NJTreeBuilder implements ITreeBuilder {
	private DistanceMatrix dm;;

	public NJTreeBuilder() {
	}

	@Override
	public TreeNode build(List<Taxon> taxons) {
		this.dm = new DistanceMatrix(taxons);

		// special cases
		if (taxons.size() == 0)
			return null;
		else if (taxons.size() == 1)
			return new TreeNode(taxons.get(0), null);
		else if (taxons.size() == 2) {
			TreeNode child1 = new TreeNode(taxons.get(0), null);
			TreeNode child2 = new TreeNode(taxons.get(1), null);
			List<TreeNode> children = new ArrayList<TreeNode>();
			children.add(child1);
			children.add(child2);

			TreeNode root = new TreeNode(null, children);
			return root;
		}

		//core logic
		List<Taxon> currentTaxons = dm.getCurrentTaxons();
		List<TreeNode> currentNodes = new ArrayList<TreeNode>();
		for (Taxon taxon : currentTaxons) {
			currentNodes.add(new TreeNode(taxon, null));
		}
		while (currentNodes.size() > 2) {
			// find two nearest taxons
			Taxon[] bestTaxons = getBestTaxons(currentTaxons);
			System.out.println("chose" + bestTaxons[0].getName() + " and " + bestTaxons[1].getName());
			//update matrix
			Taxon newTaxon = dm.mergeTaxons(bestTaxons[0], bestTaxons[1]);

			//update tree
			List<TreeNode> children = new ArrayList<TreeNode>();
			TreeNode tn1 = null;
			TreeNode tn2 = null;
			for (TreeNode node : currentNodes) {
				if(node.getTaxon() == bestTaxons[0])
					tn1 = node;
				else if (node.getTaxon() == bestTaxons[1])
					tn2 = node;
			}
			children.add(tn1);
			children.add(tn2);
			TreeNode newNode = new TreeNode(newTaxon, children);
			currentNodes.add(newNode);
			currentNodes.remove(tn1);
			currentNodes.remove(tn2);

			currentTaxons = dm.getCurrentTaxons();
		}
		//combine last two nodes
		List<TreeNode> children = new ArrayList<TreeNode>();
		children.add(currentNodes.get(0));
		children.add(currentNodes.get(1));
		return new TreeNode(null, children);
	}

	private Taxon[] getBestTaxons(List<Taxon> currentTaxons) {
//		System.out.println("size: " + currentTaxons.size());
//		System.out.println("Start MAP");
//		for (TaxonPair pair : dm.matrix.keySet()) {
//			System.out.println("    " + pair.t1 + " " + pair.t2 + " distance: " + dm.matrix.get(pair));
//		}
//		System.out.println("END MAP");
		
		
		Taxon[] bestTaxons = new Taxon[2];
		
		Taxon bestT1 = null;
		Taxon bestT2 = null;
		float minValue = Float.POSITIVE_INFINITY;
		for (int i = 0; i < currentTaxons.size() - 1; i++) {
			for (int j = i + 1; j < currentTaxons.size(); j++) {
				Taxon t1 = currentTaxons.get(i);
				Taxon t2 = currentTaxons.get(j);
				float value = dm.d(t1, t2) - u(t1, currentTaxons) - u(t2, currentTaxons);
				if (value < minValue) {
					minValue = value;
					bestT1 = t1;
					bestT2 = t2;
				}
			}
		}
		bestTaxons[0] = bestT1;
		bestTaxons[1] = bestT2;
		return bestTaxons;
	}

	private float u(Taxon taxon, List<Taxon> currentTaxons) {
		float distance = 0.0f;
		for (Taxon _taxon : currentTaxons) {
			if (!taxon.equals(_taxon))
				distance += dm.d(_taxon, taxon);
		}
		return distance / (currentTaxons.size() - 2);
	}
}
