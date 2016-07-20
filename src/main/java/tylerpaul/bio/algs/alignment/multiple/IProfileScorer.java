package tylerpaul.bio.algs.alignment.multiple;

public interface IProfileScorer {
	public float getScore(ProfileChar a, ProfileChar b);
	public float getScoreAgainstIndel(ProfileChar pc1);
}
