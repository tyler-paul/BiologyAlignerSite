package tylerpaul.bio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tylerpaul.bio.daos.ISequenceDAO;
import tylerpaul.bio.daos.IUserDAO;
import tylerpaul.bio.models.Sequence;

@Service
public class SequenceManager implements ISequenceManager {
	@Autowired
	private ISequenceDAO sequenceDAO;
	@Autowired
	private IUserDAO userDAO;
	
	@Override
	@Transactional
	public List<Sequence> getSequences(String username) {
		return sequenceDAO.getSequences(username);
	}

	@Override
	@Transactional
	public Sequence getSequence(int sequenceId) {
		return sequenceDAO.getSequence(sequenceId);
	}

	@Override
	@Transactional
	public void addSequenceToUser(Sequence sequence, String username) {
		sequence.setOwner(userDAO.getUser(username));
		sequenceDAO.addSequence(sequence);
		
	}

	@Override
	@Transactional
	public void deleteSequence(int sequenceID) {
		sequenceDAO.deleteSequence(sequenceID);
	}
	
	@Override
	@Transactional
	public void updateSequence(int sequenceID, String newDescription, String newData) {
		Sequence sequence = sequenceDAO.getSequence(sequenceID);
		sequence.setData(newData);
		sequence.setDescription(newDescription);
		sequenceDAO.updateSequence(sequence);
	}
}
