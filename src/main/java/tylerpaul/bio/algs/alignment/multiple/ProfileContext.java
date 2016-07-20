package tylerpaul.bio.algs.alignment.multiple;

import java.util.ArrayList;
import java.util.List;

public class ProfileContext implements Cloneable {
	private List<StringBuilder> sequences;
	private List<String> sequenceNames;
	private List<Integer> startPositions;
	private List<Integer> endPositions;
	
	public ProfileContext clone() {
		ProfileContext context = new ProfileContext();
		context.sequences = new ArrayList<StringBuilder>();
		context.sequenceNames = new ArrayList<String>();
		context.startPositions = new ArrayList<Integer>();
		context.endPositions = new ArrayList<Integer>();
		for (StringBuilder builder : sequences) {
			context.sequences.add(new StringBuilder(builder.toString()));
		}
		for (String name : sequenceNames) {
			context.sequenceNames.add(new String(name));
		}
		for (int i : startPositions) {
			context.startPositions.add(i);
		}
		for (int i : endPositions) {
			context.endPositions.add(i);
		}
		return context;
	}
	public ProfileContext() {
		sequences = new ArrayList<StringBuilder>();
		sequenceNames = new ArrayList<String>();
		startPositions = new ArrayList<Integer>();
		endPositions = new ArrayList<Integer>();
	}
	
	public ProfileContext(String sequence, String name) {
		sequences = new ArrayList<StringBuilder>();
		sequenceNames = new ArrayList<String>();
		startPositions = new ArrayList<Integer>();
		endPositions = new ArrayList<Integer>();
		startPositions.add(0);
		endPositions.add(sequence.length());
		addSequence(sequence, name);
	}
	
	public void addSequence(String sequence, String name) {
		sequences.add(new StringBuilder(sequence));
		sequenceNames.add(name);
	}
	
	public void insertAt(int index) {
		for (StringBuilder sequence : sequences) {
			sequence.insert(index, '-');
		}
	}
	
	public void trimRight(int index) {

		for (StringBuilder sb : sequences) {
			//System.out.println("Trim" + index + " to " + sb.length());
			//System.out.println(sb);
			sb.delete(index, sb.length());
			//System.out.println(sb);
		}
		
		for (int i = 0; i < endPositions.size(); i++) {
			endPositions.set(i, startPositions.get(i) + index);
		}
		

	}
	
	public void trimLeft(int index) {
		for (StringBuilder sb : sequences) {
			sb.delete(0, index);//.subList(0, index).clear();
		}
		
		for (int i = 0; i < startPositions.size(); i++) {
			startPositions.set(i, startPositions.get(i) + index);
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (StringBuilder sequence : sequences) {
			sb.append(sequence.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	public List<StringBuilder> getSequences() {
		return sequences;
	}
	public List<String> getSequenceNames() {
		return sequenceNames;
	}
	public List<Integer> getStartPositions() {
		return startPositions;
	}
	public void setStartPositions(List<Integer> startPositions) {
		this.startPositions = startPositions;
	}
	public List<Integer> getEndPositions() {
		return endPositions;
	}
	public void setEndPositions(List<Integer> endPositions) {
		this.endPositions = endPositions;
	}
	public void setSequences(List<StringBuilder> sequences) {
		this.sequences = sequences;
	}
	public void setSequenceNames(List<String> sequenceNames) {
		this.sequenceNames = sequenceNames;
	}
	/*
	public static void main(String[] args) {
		ProfileContext pc = new ProfileContext();
		pc.addSequence("ACGTTA");
		pc.addSequence("GATACA");
		pc.insertAt(3);
		System.out.println(pc);
	}
	*/
}
