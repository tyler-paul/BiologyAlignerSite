package tylerpaul.bio.util;

import java.io.File;

import tylerpaul.bio.algs.phylogeny.TreeNode;

public interface ITreePrinter {
	public void print(TreeNode root, File file, String title) throws Exception;
}
