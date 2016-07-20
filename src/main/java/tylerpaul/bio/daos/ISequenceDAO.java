package tylerpaul.bio.daos;

import java.util.List;

import tylerpaul.bio.models.Sequence;

public interface ISequenceDAO {

	void addSequence(Sequence sequence);

	void deleteSequence(int sequenceID);

	List<Sequence> getSequences(String username);

	Sequence getSequence(int sequenceId);

	void updateSequence(Sequence sequence);

}