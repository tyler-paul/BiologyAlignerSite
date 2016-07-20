package tylerpaul.bio.algs.alignment.single;

import java.util.List;

import tylerpaul.bio.algs.alignment.IAligner;
import tylerpaul.bio.models.SequenceAlignment;
import tylerpaul.bio.models.SequenceAlignmentPart;
import tylerpaul.bio.models.SingleAlignment;

public class SingleAligner implements IAligner {
	private INucleotideScorer scorer;

	private enum Dir {
		LEFT, UP, DIAG;
	}
	private class Node {
		int score;
		Node parent; //for backtracking
		Dir dir;
	}
	
	public SingleAligner(INucleotideScorer scorer) {
		this.scorer = scorer;
	}
	
	public void align(List<String> sequences, List<String> sequenceNames, boolean localAlignment, SequenceAlignment alignment) {
		SingleAlignment singleAlignment = (SingleAlignment)alignment;
		String sequenceA = sequences.get(0);
		String sequenceB = sequences.get(1);
		String sequenceAName = sequenceNames.get(0);
		String sequenceBName = sequenceNames.get(1);
		
		//initialize nodes
		Node[][] nodes = new Node[sequenceB.length() + 1][sequenceA.length() + 1];
		initializeNodes(nodes);
		
		//score[i,j] = max{score[i-1,j], score[i][j-1], score[i-1][j-1]+1 (if seqA[i] == seqB[j])}
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[0].length; j++) {
				int max;
				if (localAlignment) {
					max = 0;
				}
				else { //if global alignment
					max = Integer.MIN_VALUE;
					if (i == 0 && j ==0)
						max = 0;
				}

				Node parent = null;
				Dir dir = null;
				if (j > 0 && nodes[i][j-1].score + scorer.getScore(sequenceA.charAt(j - 1), '-') > max) {
					max = nodes[i][j-1].score + scorer.getScore(sequenceA.charAt(j - 1), '-');
					parent = nodes[i][j-1];
					dir = Dir.LEFT;
				}
				if (i > 0 && nodes[i-1][j].score + scorer.getScore('-',  sequenceB.charAt(i - 1)) > max) {
					max = nodes[i-1][j].score + scorer.getScore('-',  sequenceB.charAt(i - 1));
					parent = nodes[i-1][j];
					dir = Dir.UP;
				}
				if (i > 0 && j > 0 && nodes[i-1][j-1].score + scorer.getScore(sequenceA.charAt(j - 1), sequenceB.charAt(i - 1)) > max) {
					max = nodes[i-1][j-1].score + scorer.getScore(sequenceA.charAt(j - 1), sequenceB.charAt(i - 1));
					parent = nodes[i-1][j-1];
					dir = Dir.DIAG;
				}
				nodes[i][j].score = max;
				nodes[i][j].parent = parent;
				nodes[i][j].dir = dir;
			}
		}

		int bIndex = sequenceB.length(), aIndex = sequenceA.length();
		
		if (localAlignment) {
			//for local alignment find node with greatest score;
			int maxScore = Integer.MIN_VALUE;
			for (int i = 0; i < nodes.length; i++) {
				for (int j = 0; j < nodes[0].length; j++) {
					if (nodes[i][j].score > maxScore) {
						maxScore = nodes[i][j].score;
						bIndex = i;
						aIndex = j;
					}
				}
			}
		}

		//backtrack to find alignment
		backtrack(nodes, sequenceA, sequenceB, sequenceAName, sequenceBName, aIndex, bIndex, singleAlignment);
	}
	
	private void initializeNodes(Node[][] nodes) {
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[0].length; j++) {
				nodes[i][j] = new Node();
				nodes[i][j].score = Integer.MIN_VALUE;
				if (i == 0)
					nodes[i][j].dir = Dir.LEFT;
				else if (j == 0)
					nodes[i][j].dir = Dir.UP;
			}
		}
	}
	
	private void backtrack(Node[][] nodes, String sequenceA, String sequenceB, 
				String sequenceAName, String sequenceBName, int aIndex, int bIndex, SingleAlignment singleAlignment) {
		Node node = nodes[bIndex][aIndex];
		StringBuilder top = new StringBuilder();
		StringBuilder bottom = new StringBuilder();
		int seqAPos = aIndex - 1;
		int seqBPos = bIndex - 1;
		int numMatches = 0;
		int numMismatches = 0;
		int numInsertions = 0;
		int numDeletions = 0;
		while (node != null) {
			if (node.dir == Dir.LEFT) {
				top.append(sequenceA.charAt(seqAPos--));
				bottom.append('-');
				numDeletions++;
			}
			else if (node.dir == Dir.UP) {
				top.append('-');
				bottom.append(sequenceB.charAt(seqBPos--));
				numInsertions++;
			}
			else if (node.dir == Dir.DIAG) {
				if (sequenceA.charAt(seqAPos) == sequenceB.charAt(seqBPos))
					numMatches++;
				else
					numMismatches++;
				top.append(sequenceA.charAt(seqAPos--));
				bottom.append(sequenceB.charAt(seqBPos--));
			}
			node = node.parent;
		}
		
		int score = nodes[bIndex][aIndex].score;
		singleAlignment.setNumMatches(numMatches);
		singleAlignment.setNumMismatches(numMismatches);
		singleAlignment.setNumInsertions(numInsertions);
		singleAlignment.setNumDeletions(numDeletions);
		singleAlignment.setScore(score);
		
		SequenceAlignmentPart topPart = new SequenceAlignmentPart();
		topPart.setData(top.reverse().toString());
		topPart.setTaxon(sequenceAName);
		topPart.setSequenceAlignment(singleAlignment);
		topPart.setStartPos(seqAPos + 1);
		topPart.setEndPos(aIndex);
		
		SequenceAlignmentPart bottomPart = new SequenceAlignmentPart();
		bottomPart.setData(bottom.reverse().toString());
		bottomPart.setSequenceAlignment(singleAlignment);
		bottomPart.setTaxon(sequenceBName);
		bottomPart.setStartPos(seqBPos + 1);
		bottomPart.setEndPos(bIndex);
		
		singleAlignment.addPart(topPart);
		singleAlignment.addPart(bottomPart);
	}
	
	private void debugNodes(Node[][] nodes) {
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[0].length; j++) {
				System.out.print(nodes[i][j].score + " ");
			}
			System.out.println();
		}
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[0].length; j++) {
				System.out.print(nodes[i][j].dir + " ");
			}
			System.out.println();
		}
	}
}


