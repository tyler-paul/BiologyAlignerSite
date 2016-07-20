package tylerpaul.bio.algs.phylogeny;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	private Taxon taxon;
	private List<TreeNode> children;
	private TreeNode parent;
	
	public TreeNode() {
		this.children = new ArrayList<TreeNode>();
	}
	public TreeNode(Taxon taxon, List<TreeNode> children) {
		this.taxon = taxon;
		if (children == null) {
			this.children = new ArrayList<TreeNode>();
		} else {
			setChildren(children);
		}
	}

	public Taxon getTaxon() {
		return taxon;
	}
	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
		for (TreeNode child : children) {
			child.setParent(this);
		}
	}
	public void setChildrenOnly(List<TreeNode> children) {
		this.children = children;
	}
	public TreeNode getSibling() {
		if (parent.getChildren().get(0) == this) {
			return parent.getChildren().get(1);
		}
		else
			return parent.getChildren().get(0);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((taxon == null) ? 0 : taxon.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof TreeNode))
			return false;
		TreeNode treeNode = (TreeNode)obj;
		if (this.taxon == treeNode.taxon)
			return true;
		else
			return false;
	}
	public TreeNode getParent() {
		return parent;
	}
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
}
