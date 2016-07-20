package tylerpaul.bio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tylerpaul.bio.algs.alignment.ISettable;
import tylerpaul.bio.algs.alignment.multiple.IProfileScorer;
import tylerpaul.bio.algs.alignment.multiple.MultipleAligner;
import tylerpaul.bio.algs.alignment.single.INucleotideScorer;
import tylerpaul.bio.algs.alignment.single.SingleAligner;
import tylerpaul.bio.daos.IAlignmentDAO;
import tylerpaul.bio.daos.ISequenceDAO;
import tylerpaul.bio.daos.IUserDAO;
import tylerpaul.bio.models.Sequence;
import tylerpaul.bio.models.SequenceAlignment;
import tylerpaul.bio.models.SequenceAlignmentPart;
import tylerpaul.bio.models.SingleAlignment;

@Service
public class AlignmentManager implements IAlignmentManager {
	@Autowired
	private IAlignmentDAO alignmentDAO;
	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private ISequenceDAO sequenceDAO;
	
	@Override
	@Transactional
	public SequenceAlignment getAlignment(int alignmentID) {
		return alignmentDAO.getAlignment(alignmentID);
	}

	@Override
	@Transactional
	public void deleteAlignment(int alignmentID) {
		alignmentDAO.deleteAlignment(alignmentID);
	}

	@Override
	@Transactional
	public void saveAlignmentToUser(SequenceAlignment alignment, String username) {
		alignment.setOwner(userDAO.getUser(username));
		alignmentDAO.saveAlignment(alignment);
	}

	@Override
	@Transactional
	public List<SequenceAlignment> getAlignments(String username) {
		return alignmentDAO.getAlignments(username);
	}
	
	@Override
	@Transactional
	public void updateAlignment(int alignmentID, String newDescription, String[] newTaxonNames) {
		SequenceAlignment alignment = alignmentDAO.getAlignment(alignmentID);
		alignment.setDescription(newDescription);
		List<SequenceAlignmentPart> parts = alignment.getParts();
		for (int i = 0; i < parts.size(); i++) {
			parts.get(i).setTaxon(newTaxonNames[i]);
		}
		alignmentDAO.saveAlignment(alignment);
	}

	@Override
	@Transactional
	public void singleAlignment(String username, String description, boolean isLocal, int sequence1ID, int sequence2ID, List<String> taxonNames, ISettable scorer) {
		SingleAlignment alignment = new SingleAlignment();
		alignment.setStatus("Pending");
		alignment.setDescription(description);
		alignment.setIsLocal(isLocal);
		alignment.setOwner(userDAO.getUser(username));
		alignmentDAO.saveAlignment(alignment);
		
		//get persistent sequences
		Sequence seq1 = sequenceDAO.getSequence(sequence1ID);
		Sequence seq2 = sequenceDAO.getSequence(sequence2ID);
		
		//align sequences
		SingleAligner aligner = new SingleAligner((INucleotideScorer)scorer);
		List<String> sequences = new ArrayList<String>();
		sequences.add(seq1.getData());
		sequences.add(seq2.getData());
		aligner.align(sequences, taxonNames, isLocal, alignment);
		
		//save aligned sequences
		alignment.setStatus("Complete");
		alignmentDAO.saveAlignment(alignment);
	}
	
	@Override
	@Transactional
	public void multipleAlignment(String username, String description, boolean isLocal, String[] sequenceIDs, List<String> taxonNames, ISettable scorer) {
		SequenceAlignment multipleAlignment = new SequenceAlignment();
		multipleAlignment.setStatus("Pending");
		multipleAlignment.setDescription(description);
		multipleAlignment.setIsLocal(isLocal);
		multipleAlignment.setOwner(userDAO.getUser(username));
		alignmentDAO.saveAlignment(multipleAlignment);
		
		//get persistent sequences
		List<String> sequencesData = new ArrayList<String>();
		for (int i = 0; i < sequenceIDs.length; i++) {
			Sequence sequence = sequenceDAO.getSequence(Integer.parseInt(sequenceIDs[i]));
			sequencesData.add(sequence.getData());
		}

		//align sequences
		MultipleAligner aligner = new MultipleAligner((IProfileScorer)scorer);
		aligner.align(sequencesData, taxonNames, isLocal, multipleAlignment);
		
		//save aligned sequences
		multipleAlignment.setStatus("Complete");
		alignmentDAO.saveAlignment(multipleAlignment);
	}
}
