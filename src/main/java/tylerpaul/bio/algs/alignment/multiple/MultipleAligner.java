package tylerpaul.bio.algs.alignment.multiple;

import java.util.ArrayList;
import java.util.List;

import tylerpaul.bio.algs.alignment.IAligner;
import tylerpaul.bio.algs.alignment.Symbol;
import tylerpaul.bio.models.SequenceAlignment;
import tylerpaul.bio.models.SequenceAlignmentPart;

public class MultipleAligner implements IAligner {
	private IProfileScorer scorer;

	private enum Dir {
		LEFT, UP, DIAG;
	}
	private class Node {
		float score;
		Node parent; //for backtracking
		Dir dir;
	}
	
	public MultipleAligner(IProfileScorer scorer) {
		this.scorer = scorer;
	}
	
	public MultipleAligner() {
		this.scorer = new ProfileScoreMatrix(2, 2);
	}
	
	public void align(List<String> sequences, List<String> sequenceNames, boolean isLocal, SequenceAlignment alignment) {
		//construct initial profiles
		List<Profile> profiles = new ArrayList<Profile>();
		for (int i = 0; i < sequences.size(); i++) {
			List<ProfileChar> profileData = new ArrayList<ProfileChar>();
			for (int j = 0; j < sequences.get(i).length(); j++) {
				profileData.add(new ProfileChar(Symbol.getSymbol(sequences.get(i).charAt(j))));
			}
			profiles.add(new Profile(profileData, new ProfileContext(sequences.get(i), sequenceNames.get(i))));
		}
		
		//find two 'nearest' profiles, align them, combine them into a new profile, repeat
		while (profiles.size() > 1) {
			float maxScore = Float.NEGATIVE_INFINITY;
			ProfileContext bestContext = null;
			Pair bestPair = null;
			List<ProfileChar> newProfileAData = null;
			List<ProfileChar> newProfileBData = null;
			
			//get two profiles with maximum score
			for (Pair pair : getPairs(profiles)) {
				AlignmentOutput ao = align(pair.a, pair.b, isLocal);
				if (ao.score > maxScore) {
					maxScore = ao.score;
					bestContext = ao.profileContext;
					newProfileAData = ao.newProfileAData;
					newProfileBData = ao.newProfileBData;
					bestPair = pair;
				}
			}
			bestPair.a.setData(newProfileAData);
			bestPair.b.setData(newProfileBData);
			
			//combine two 'nearest' aligned sequences into a single profile
			List<ProfileChar> data = new ArrayList<ProfileChar>();
			for (int i = 0; i < bestPair.a.getData().size(); i++) {
				data.add(new ProfileChar(bestPair.a.getData().get(i), bestPair.b.getData().get(i)));
			}
			
			//update profiles list
			Profile profile = new Profile(data, bestContext);
			profiles.add(profile);
			profiles.remove(bestPair.a);
			profiles.remove(bestPair.b);
		}
		
		//construct output
		List<StringBuilder> sequenceDataList = profiles.get(0).getContext().getSequences();
		List<String> sequenceNamesList = profiles.get(0).getContext().getSequenceNames();
		List<Integer> startPositions = profiles.get(0).getContext().getStartPositions();
		List<Integer> endPositions = profiles.get(0).getContext().getEndPositions();
		for (int i = 0; i < sequenceDataList.size(); i++) {
			SequenceAlignmentPart part = new SequenceAlignmentPart();
			part.setData(sequenceDataList.get(i).toString());
			part.setTaxon(sequenceNamesList.get(i));
			part.setSequenceAlignment(alignment);
			part.setStartPos(startPositions.get(i));
			part.setEndPos(endPositions.get(i));
			alignment.addPart(part);
		}
	}
	
	//return a list of all possible pairs of a given list of profiles
	private List<Pair> getPairs(List<Profile> profiles) {
		List<Pair> pairs = new ArrayList<Pair>();
		for (int i = 0; i < profiles.size() - 1; i++) {
			for (int j = i + 1; j < profiles.size(); j++) {
				pairs.add(new Pair(profiles.get(i), profiles.get(j)));
			}
		}
		return pairs;
	}
	private static class Pair {
		Pair(Profile a, Profile b) {
			this.a = a;
			this.b = b;
		}
		Profile a;
		Profile b;
	}
	
	private static class AlignmentOutput {
		ProfileContext profileContext;
		List<ProfileChar> newProfileAData;
		List<ProfileChar> newProfileBData;
		float score;
	}
	public AlignmentOutput align(Profile profileA, Profile profileB, boolean isLocal) {
		List<ProfileChar> profileAData = profileA.getData();
		List<ProfileChar> profileBData = profileB.getData();
		
		//initialize nodes
		Node[][] nodes = new Node[profileBData.size() + 1][profileAData.size() + 1];
		initializeNodes(nodes);
		
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[0].length; j++) {
				float max;
				if (isLocal) {
					max = 0;
				}
				else { //if global alignment
					max = Integer.MIN_VALUE;
					if (i == 0 && j == 0)
						max = 0;
				}

				Node parent = null;
				Dir dir = null;
				float score;
				if (j > 0 && (score = nodes[i][j-1].score + scorer.getScoreAgainstIndel(profileAData.get(j - 1))) > max) {
					max = score;
					parent = nodes[i][j-1];
					dir = Dir.LEFT;
				}
				if (i > 0 && (score = nodes[i-1][j].score + scorer.getScoreAgainstIndel(profileBData.get(i - 1))) > max) {
					max = score;
					parent = nodes[i-1][j];
					dir = Dir.UP;
				}
				if (i > 0 && j > 0 && (score = nodes[i-1][j-1].score + scorer.getScore(profileAData.get(j - 1), profileBData.get(i - 1))) > max) {
					max = score;
					parent = nodes[i-1][j-1];
					dir = Dir.DIAG;
				}
				nodes[i][j].score = max;
				nodes[i][j].parent = parent;
				nodes[i][j].dir = dir;
			}
		}
		//debugNodes(nodes);
		
		int bIndex = profileBData.size(), aIndex = profileAData.size();
		float maxScore = Float.MIN_VALUE;
		if (isLocal) {
			//for local alignment find node with greatest score;
			for (int i = 0; i < nodes.length; i++) {
				for (int j = 0; j < nodes[0].length; j++) {
					if (nodes[i][j].score > maxScore) {
						maxScore = nodes[i][j].score;
						bIndex = i;
						aIndex = j;
					}
				}
			}
		} else {
			maxScore = nodes[bIndex][aIndex].score;
		}

		AlignmentOutput ao = new AlignmentOutput();
		ao.score = maxScore;
		//backtrack to find alignment
		BacktrackOutput backtrackOutput =  backtrack(nodes, profileA, profileB, aIndex, bIndex, isLocal);
		ao.profileContext = backtrackOutput.profileContext;
		ao.newProfileAData = backtrackOutput.newProfileAData;
		ao.newProfileBData = backtrackOutput.newProfileBData;
		return ao;
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
	private static class BacktrackOutput {
		ProfileContext profileContext;
		List<ProfileChar> newProfileAData;
		List<ProfileChar> newProfileBData;
	}
	
	private BacktrackOutput backtrack(Node[][] nodes, Profile profileA, Profile profileB, int aIndex, int bIndex, boolean isLocal) {
		List<ProfileChar> profileAData = profileA.getData();
		List<ProfileChar> profileBData = profileB.getData();
		
		List<ProfileChar> newProfileAData = (List<ProfileChar>) ((ArrayList) profileAData).clone();;
		List<ProfileChar> newProfileBData = (List<ProfileChar>) ((ArrayList) profileBData).clone();;
		
		ProfileContext profileAContext = profileA.getContext().clone();
		ProfileContext profileBContext = profileB.getContext().clone();
		
		ProfileContext newContext = new ProfileContext();
		Node node = nodes[bIndex][aIndex];
		int seqAPos = aIndex - 1;
		int seqBPos = bIndex - 1;
		
		if (isLocal) {
			newProfileAData.subList(seqAPos+1, newProfileAData.size()).clear();
			newProfileBData.subList(seqBPos+1, newProfileBData.size()).clear();
			
			profileAContext.trimRight(seqAPos + 1);
			profileBContext.trimRight(seqBPos + 1);
		}
		
		while (node != null) {
			if (node.dir == Dir.LEFT) {
				seqAPos--;
				profileBContext.insertAt(seqBPos+1);
				newProfileBData.add(seqBPos+1, new ProfileChar(Symbol.INDEL));
			}
			else if (node.dir == Dir.UP) {
				profileAContext.insertAt(seqAPos+1);
				newProfileAData.add(seqAPos+1, new ProfileChar(Symbol.INDEL));
				seqBPos--;
			}
			else if (node.dir == Dir.DIAG) {
				seqAPos--;
				seqBPos--;
			}
			node = node.parent;
		}
		
		//remove more for local alignment
		if (isLocal) {
			newProfileAData.subList(0, seqAPos + 1).clear();
			newProfileBData.subList(0, seqBPos + 1).clear();
			
			profileAContext.trimLeft(seqAPos+1);
			profileBContext.trimLeft(seqBPos+1);
		}
		
		for (int i = 0; i < profileAContext.getSequences().size(); i++) {
			StringBuilder sequenceData = profileAContext.getSequences().get(i);
			String sequenceName =  profileAContext.getSequenceNames().get(i);
			newContext.addSequence(sequenceData.toString(), sequenceName);
			newContext.getStartPositions().add(profileAContext.getStartPositions().get(i));
			newContext.getEndPositions().add(profileAContext.getEndPositions().get(i));
		}
		for (int i = 0; i < profileBContext.getSequences().size(); i++) {
			StringBuilder sequenceData = profileBContext.getSequences().get(i);
			String sequenceName =  profileBContext.getSequenceNames().get(i);
			newContext.addSequence(sequenceData.toString(), sequenceName);
			newContext.getStartPositions().add(profileBContext.getStartPositions().get(i));
			newContext.getEndPositions().add(profileBContext.getEndPositions().get(i));
		}

		BacktrackOutput output = new BacktrackOutput();
		output.profileContext = newContext;
		output.newProfileAData = newProfileAData;
		output.newProfileBData = newProfileBData;
		
		return output;
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




