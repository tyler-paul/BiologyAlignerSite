package tylerpaul.bio.daos;

import java.util.List;

import tylerpaul.bio.models.SequenceAlignment;

public interface IAlignmentDAO {

	SequenceAlignment getAlignment(int alignmentID);

	void deleteAlignment(int alignmentID);

	void saveAlignment(SequenceAlignment alignment);

	List<SequenceAlignment> getAlignments(String username);

}