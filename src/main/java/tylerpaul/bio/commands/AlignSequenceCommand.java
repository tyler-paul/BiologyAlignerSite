package tylerpaul.bio.commands;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.algs.alignment.IAligner;
import tylerpaul.bio.algs.alignment.ISettable;
import tylerpaul.bio.algs.alignment.Symbol;
import tylerpaul.bio.algs.alignment.multiple.ArbitraryProfileScoreMatrix;
import tylerpaul.bio.algs.alignment.multiple.IProfileScorer;
import tylerpaul.bio.algs.alignment.multiple.MultipleAligner;
import tylerpaul.bio.algs.alignment.multiple.SequenceInfo;
import tylerpaul.bio.algs.alignment.single.SingleAligner;
import tylerpaul.bio.algs.alignment.single.ArbitraryNucleotideScoreMatrix;
import tylerpaul.bio.algs.alignment.single.INucleotideScorer;
import tylerpaul.bio.daos.IAlignmentDAO;
import tylerpaul.bio.daos.ISequenceDAO;
import tylerpaul.bio.models.Sequence;
import tylerpaul.bio.models.SequenceAlignment;
import tylerpaul.bio.models.SequenceAlignmentPart;
import tylerpaul.bio.models.SingleAlignment;
import tylerpaul.bio.service.IAlignmentManager;
import tylerpaul.bio.service.ISequenceManager;

@Component
public class AlignSequenceCommand implements ICommand {
	@Autowired
	private IAlignmentManager alignmentManager;
	@Autowired
	private ISequenceManager sequenceManager;

	public AlignSequenceCommand() {
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		//get taxon names
		final List<String> taxonNames = new ArrayList<String>();
		ISettable scoringMatrix;
		if (request.getParameter("alignment").equals("single")) {
			taxonNames.add(request.getParameter("s" + request.getParameter("sequence1ID")));
			taxonNames.add(request.getParameter("s" + request.getParameter("sequence2ID")));
			scoringMatrix = new ArbitraryNucleotideScoreMatrix();
		}
		else {
			String[] values = request.getParameterValues("multipleSequences");
			for (String value : values) {
				taxonNames.add(request.getParameter("m" + value));
			}
			scoringMatrix = new ArbitraryProfileScoreMatrix();
		}
		constructMatrix(request, scoringMatrix);
		
		//get other parameters
		final String alignmentType = request.getParameter("alignmentType");
		final String alignment = request.getParameter("alignment");
		final String sequence1IDStr = request.getParameter("sequence1ID");
		final String sequence2IDStr = request.getParameter("sequence2ID");
		int sequence1IDInt = 0;
		int sequence2IDInt = 0;
		if (sequence1IDStr != null && !sequence1IDStr.equals(""))
			sequence1IDInt = Integer.parseInt(sequence1IDStr);
		if (sequence2IDStr != null && !sequence2IDStr.equals(""))
			sequence2IDInt = Integer.parseInt(sequence2IDStr);
		final int sequence1ID = sequence1IDInt;
		final int sequence2ID = sequence2IDInt;
		final String[] multipleSequences = request.getParameterValues("multipleSequences");
		final String description =  request.getParameter("description");
		
		//align sequences in a new thread
		/*
		new Thread(() -> executeInNewThread(alignmentType, alignment, request.getUserPrincipal().getName(),
				sequence1ID, sequence2ID, multipleSequences, scoringMatrix, description, taxonNames
				)).start();
		*/
		final String username = request.getUserPrincipal().getName();
		final ISettable myScoringMatrix = scoringMatrix;
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				executeInNewThread(alignmentType, alignment, username,
		 				sequence1ID, sequence2ID, multipleSequences, myScoringMatrix, description, taxonNames
				);
			}
		});
		t1.start();
		
		return "/jsp/index.jsp";
	}

	private void executeInNewThread(String alignmentType, String alignmentCategory, String username, int sequence1ID,
			int sequence2ID, String[] sequenceIDs, ISettable scorer, String description, List<String> taxonNames) {

		boolean isLocal = false;
		if (alignmentType.equals("local"))
			isLocal = true;

		if (alignmentCategory.equals("single")) {
			SingleAlignment alignment = new SingleAlignment();
			alignment.setStatus("Pending");
			alignment.setDescription(description);
			alignment.setIsLocal(isLocal);
			alignmentManager.saveAlignmentToUser(alignment, username);

			// get persistent sequences
			Sequence seq1 = sequenceManager.getSequence(sequence1ID);
			Sequence seq2 = sequenceManager.getSequence(sequence2ID);

			// align sequences
			SingleAligner aligner = new SingleAligner((INucleotideScorer) scorer);
			List<String> sequences = new ArrayList<String>();
			sequences.add(seq1.getData());
			sequences.add(seq2.getData());
			aligner.align(sequences, taxonNames, isLocal, alignment);

			// save aligned sequences
			alignment.setStatus("Complete");
			alignmentManager.saveAlignmentToUser(alignment, username);
		} else if (alignmentCategory.equals("multiple")) {
			SequenceAlignment multipleAlignment = new SequenceAlignment();
			multipleAlignment.setStatus("Pending");
			multipleAlignment.setDescription(description);
			multipleAlignment.setIsLocal(isLocal);
			alignmentManager.saveAlignmentToUser(multipleAlignment, username);

			// get persistent sequences
			List<String> sequencesData = new ArrayList<String>();
			for (int i = 0; i < sequenceIDs.length; i++) {
				Sequence sequence = sequenceManager.getSequence(Integer.parseInt(sequenceIDs[i]));
				sequencesData.add(sequence.getData());
			}

			// align sequences
			MultipleAligner aligner = new MultipleAligner((IProfileScorer) scorer);
			aligner.align(sequencesData, taxonNames, isLocal, multipleAlignment);

			// save aligned sequences
			multipleAlignment.setStatus("Complete");
			alignmentManager.saveAlignmentToUser(multipleAlignment, username);
		}
	}

	private void constructMatrix(HttpServletRequest request, ISettable scorer) {
		// ArbitraryNucleotideScoreMatrix scorer = new
		// ArbitraryNucleotideScoreMatrix();
		scorer.setEntry(Symbol.A, Symbol.A, Integer.parseInt(request.getParameter("tAA")));
		scorer.setEntry(Symbol.C, Symbol.C, Integer.parseInt(request.getParameter("tCC")));
		scorer.setEntry(Symbol.G, Symbol.G, Integer.parseInt(request.getParameter("tGG")));
		scorer.setEntry(Symbol.T, Symbol.T, Integer.parseInt(request.getParameter("tTT")));

		scorer.setEntry(Symbol.A, Symbol.C, -Integer.parseInt(request.getParameter("tAC")));
		scorer.setEntry(Symbol.A, Symbol.G, -Integer.parseInt(request.getParameter("tAG")));
		scorer.setEntry(Symbol.A, Symbol.T, -Integer.parseInt(request.getParameter("tAT")));
		scorer.setEntry(Symbol.A, Symbol.INDEL, -Integer.parseInt(request.getParameter("tA-")));

		scorer.setEntry(Symbol.C, Symbol.G, -Integer.parseInt(request.getParameter("tCG")));
		scorer.setEntry(Symbol.C, Symbol.T, -Integer.parseInt(request.getParameter("tCT")));
		scorer.setEntry(Symbol.C, Symbol.INDEL, -Integer.parseInt(request.getParameter("tC-")));

		scorer.setEntry(Symbol.G, Symbol.T, -Integer.parseInt(request.getParameter("tGT")));
		scorer.setEntry(Symbol.G, Symbol.INDEL, -Integer.parseInt(request.getParameter("tG-")));

		scorer.setEntry(Symbol.T, Symbol.INDEL, -Integer.parseInt(request.getParameter("tT-")));
	}
}
