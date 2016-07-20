package tylerpaul.bio.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="single_alignment")
@DiscriminatorValue("s")
public class SingleAlignment extends SequenceAlignment {
	@Column(name="num_matches")
	private int numMatches;
	@Column(name="num_mismatches")
	private int numMismatches;
	@Column(name="num_insertions")
	private int numInsertions;
	@Column(name="num_deletions")
	private int numDeletions;
	@Column(name="score")
	private int score;
	
	public SingleAlignment() {
		super();
	}
	
	public int getNumMatches() {
		return numMatches;
	}
	public void setNumMatches(int numMatches) {
		this.numMatches = numMatches;
	}
	public int getNumMismatches() {
		return numMismatches;
	}
	public void setNumMismatches(int numMismatches) {
		this.numMismatches = numMismatches;
	}
	public int getNumInsertions() {
		return numInsertions;
	}
	public void setNumInsertions(int numInsertions) {
		this.numInsertions = numInsertions;
	}
	public int getNumDeletions() {
		return numDeletions;
	}
	public void setNumDeletions(int numDeletions) {
		this.numDeletions = numDeletions;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
