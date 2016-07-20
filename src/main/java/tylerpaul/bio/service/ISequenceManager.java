package tylerpaul.bio.service;

import java.util.List;

import tylerpaul.bio.models.Sequence;

public interface ISequenceManager {
	public void addSequenceToUser(Sequence sequence, String username);
	public void deleteSequence(int sequenceID);
	public List<Sequence> getSequences(String username);
	public Sequence getSequence(int sequenceID);
	public void updateSequence(int sequenceID, String newDescription, String newData);
}
