package tylerpaul.bio.algs.phylogeny;

import java.util.List;

public interface ITreeBuilder {
	TreeNode build(List<Taxon> taxons);
}
