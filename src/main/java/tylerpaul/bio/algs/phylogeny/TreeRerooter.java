package tylerpaul.bio.algs.phylogeny;

import java.util.ArrayList;
import java.util.List;

public class TreeRerooter {
	public static TreeNode reroot(TreeNode oldroot, String outgroup) {
		//find outgroup's node
		TreeNode node = search(oldroot, outgroup);
		
		TreeNode parent = node.getParent();
		if (parent == oldroot)
			return oldroot;
		TreeNode newRoot = new TreeNode();
		List<TreeNode> children = new ArrayList<TreeNode>();
		children.add(node);
		children.add(node.getParent());
		newRoot.setChildrenOnly(children);
		
		while (true) {
			TreeNode sibling = node.getSibling();
			TreeNode grandparent = parent.getParent();
			
			List<TreeNode> newChildren = new ArrayList<TreeNode>();
			newChildren.add(sibling);
			if (grandparent.getParent() == null) {
				newChildren.add(parent.getSibling());
				parent.setChildrenOnly(newChildren);
				break;
			}
			else {
				newChildren.add(grandparent);
				parent.setChildrenOnly(newChildren);
			}
			
			node = parent;
			parent = grandparent;
		}
		
		setParents(newRoot);

		return newRoot;
	}
	
	private static void setParents(TreeNode node) {
		for (TreeNode child : node.getChildren()) {
			child.setParent(node);
			setParents(child);
		}
	}
	
	private static TreeNode search(TreeNode root, String taxonName) {
		if (root.getTaxon() != null && root.getTaxon().getName() != null && root.getTaxon().getName().equals(taxonName))
			return root;
		else if (root.getChildren().size() > 0) {
			for (TreeNode child : root.getChildren()) {
				TreeNode node = search(child, taxonName);
				if (node != null)
					return node;
			}
			return null;
		}
		else
			return null;
	}
}
