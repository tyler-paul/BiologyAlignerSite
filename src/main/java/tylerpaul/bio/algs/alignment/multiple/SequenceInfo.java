package tylerpaul.bio.algs.alignment.multiple;

public class SequenceInfo {
	private String sequenceData;
	private String sequenceName;
	private int startPos;
	private int endPos;
	
	public String getSequenceData() {
		return sequenceData;
	}
	public void setSequenceData(String sequenceData) {
		this.sequenceData = sequenceData;
	}
	public String getSequenceName() {
		return sequenceName;
	}
	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}
	public int getStartPos() {
		return startPos;
	}
	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}
	public int getEndPos() {
		return endPos;
	}
	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}
}
