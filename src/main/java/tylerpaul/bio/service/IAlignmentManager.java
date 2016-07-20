package tylerpaul.bio.service;

import java.util.List;

import tylerpaul.bio.algs.alignment.ISettable;
import tylerpaul.bio.models.SequenceAlignment;

public interface IAlignmentManager {
	public SequenceAlignment getAlignment(int alignmentID);
	public void deleteAlignment(int alignmentID);
	public List<SequenceAlignment> getAlignments(String username);
	public void saveAlignmentToUser(SequenceAlignment alignment, String username);
	public void updateAlignment(int alignmentID, String newDescription, String[] newTaxonNames);
	void singleAlignment(String username, String description, boolean isLocal, int sequence1id, int sequence2id,
			List<String> taxonNames, ISettable scorer);
	void multipleAlignment(String username, String description, boolean isLocal, String[] sequenceIDs,
			List<String> taxonNames, ISettable scorer);
}
