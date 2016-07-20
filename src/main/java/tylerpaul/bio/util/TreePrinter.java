package tylerpaul.bio.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import tylerpaul.bio.algs.phylogeny.TreeNode;

public class TreePrinter implements ITreePrinter {
	private static final int RISE = 20;
	private static final int BRANCH_LENGTH = 80;
	private static final int ROOT_LEN = 20;
	private static final int TEXT_OFFSET_Y = 5;
	private static final int LEEWAY = 5;
	
	@Override
	public void print(TreeNode root, File file, String title) throws Exception {
		int maxDepth = getDepth(root);
		int lowerLimit = getRiseLeft(root);
		int upperLimit = getRiseRight(root);
		
		FontMetrics fm = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).createGraphics().getFontMetrics();
		int longestWordWidth = longestWordWidth(root, fm);
		
		final int HEIGHT = lowerLimit + upperLimit + 10;
		final int WIDTH = maxDepth*BRANCH_LENGTH + LEEWAY + longestWordWidth + ROOT_LEN;
		final int CENTER = lowerLimit;
		
		BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setBackground(Color.WHITE);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		g2d.setColor(Color.BLACK);
		BasicStroke bs = new BasicStroke(2);
		g2d.setStroke(bs);

		g2d.drawLine(0, CENTER, ROOT_LEN, CENTER);
		g2d.drawString(title, 0, 10);
		drawNode(g2d, root, CENTER, 0, maxDepth, WIDTH);

		ImageIO.write(bufferedImage, "png", file);
	}
	
	/*draw all the children of the node passed in */
	private void drawNode(Graphics2D g2d, TreeNode node, int center, int depth, int maxDepth, int width) {
		if (node.getChildren().size() == 0)
			return;

		int riseLeft = getRiseLeft(node.getChildren().get(0));
		int riseRight = getRiseRight(node.getChildren().get(1));
		
		if (node.getChildren().get(0).getChildren().size() != 0 ) {
			g2d.drawLine(ROOT_LEN + BRANCH_LENGTH*depth, center + riseLeft, ROOT_LEN + BRANCH_LENGTH*(depth+1), center + riseLeft);
		}
		else {
			g2d.drawString(node.getChildren().get(0).getTaxon().getName(), ROOT_LEN + BRANCH_LENGTH*(maxDepth), center + riseLeft - TEXT_OFFSET_Y);
			g2d.drawLine(ROOT_LEN + BRANCH_LENGTH*depth, center + riseLeft, width, center + riseLeft);
		}
		if (node.getChildren().get(1).getChildren().size() != 0) {
			g2d.drawLine(ROOT_LEN + BRANCH_LENGTH*depth, center - riseRight, ROOT_LEN + BRANCH_LENGTH*(depth+1), center - riseRight);
		}
		else {
			g2d.drawString(node.getChildren().get(1).getTaxon().getName(), ROOT_LEN + BRANCH_LENGTH*(maxDepth), center - riseRight - TEXT_OFFSET_Y);
			g2d.drawLine(ROOT_LEN + BRANCH_LENGTH*depth, center - riseRight, width, center - riseRight);
		}
		
		
		g2d.drawLine(ROOT_LEN + BRANCH_LENGTH*depth, center, ROOT_LEN + BRANCH_LENGTH*depth, center + riseLeft);
		g2d.drawLine(ROOT_LEN + BRANCH_LENGTH*depth, center, ROOT_LEN + BRANCH_LENGTH*depth, center - riseRight);
		
		drawNode(g2d, node.getChildren().get(0), center + riseLeft, depth + 1, maxDepth, width);
		drawNode(g2d, node.getChildren().get(1), center - riseRight, depth + 1, maxDepth, width);
	}
	
	/* get width of longest word in the tree */
	private int longestWordWidth(TreeNode node, FontMetrics fm) {
		if (node.getChildren().size() == 0)
			return fm.stringWidth(node.getTaxon().getName());
		int max = longestWordWidth(node.getChildren().get(0), fm);
		int temp = longestWordWidth(node.getChildren().get(1), fm);
		if (temp > max) max = temp;
		return max;
	}
	
	/* get depth of the tree */
	private int getDepth(TreeNode root) {
		if (root.getChildren().size() == 0)
			return 0;
		int max = getDepth(root.getChildren().get(0)) + 1;
		int temp = getDepth(root.getChildren().get(1)) + 1;
		if (temp > max) max = temp;
		return max;
	}
	
	/* get the required length needed such that the left subtree doesn't overlap the right subtree */
	private int getRiseLeft(TreeNode start) {
		if (start.getChildren().size() != 2)
			return RISE;
		int num = RISE + LEEWAY;
		TreeNode node = start;
		do {
			node = node.getChildren().get(1);
			num += getRiseRight(node);
		} while (node.getChildren().size() == 2);
		return num;
	}
	
	/* get the required length needed such that the right subtree doesn't overlap the left subtree */
	private int getRiseRight(TreeNode start) {
		if (start.getChildren().size() != 2)
			return RISE;
		int num = RISE + LEEWAY;
		TreeNode node = start;
		do {
			node = node.getChildren().get(0);
			num += getRiseLeft(node);
		} while (node.getChildren().size() == 2);
		return num;
	}

}
