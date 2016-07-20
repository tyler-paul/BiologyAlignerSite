package tylerpaul.bio.algs.alignment;

import java.util.List;

import tylerpaul.bio.models.SequenceAlignment;

public interface IAligner {
	public void align(List<String> sequences, List<String> sequenceNames, boolean localAlignment, SequenceAlignment alignment);
}
