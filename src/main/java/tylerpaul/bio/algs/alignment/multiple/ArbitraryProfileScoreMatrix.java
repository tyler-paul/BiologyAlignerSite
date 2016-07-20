package tylerpaul.bio.algs.alignment.multiple;


import tylerpaul.bio.algs.alignment.ISettable;
import tylerpaul.bio.algs.alignment.Symbol;

public class ArbitraryProfileScoreMatrix implements IProfileScorer, ISettable {
	private int[][] matrix;
	
	public ArbitraryProfileScoreMatrix() {
		matrix = new int[5][5];
	}
	
	public void setEntry(Symbol a, Symbol b, int score) {
		matrix[a.ordinal()][b.ordinal()] = score;
		matrix[b.ordinal()][a.ordinal()] = score;
	}
	
	@Override
	public float getScore(ProfileChar pc1, ProfileChar pc2) {
		return pc1.getPA()*(pc2.getPA()*matrix[0][0] + pc2.getPC()*matrix[0][1] + pc2.getPG()*matrix[0][2] + pc2.getPT()*matrix[0][3] + pc2.getPIndel()*matrix[0][4]) + 
					pc1.getPC()*(pc2.getPA()*matrix[1][0] + pc2.getPC()*matrix[1][1] + pc2.getPG()*matrix[1][2] + pc2.getPT()*matrix[1][3] + pc2.getPIndel()*matrix[1][4]) +
					pc1.getPG()*(pc2.getPA()*matrix[2][0] + pc2.getPC()*matrix[2][1] + pc2.getPG()*matrix[2][2] + pc2.getPT()*matrix[2][3] + pc2.getPIndel()*matrix[2][4]) +
					pc1.getPT()*(pc2.getPA()*matrix[3][0] + pc2.getPC()*matrix[3][1] + pc2.getPG()*matrix[3][2] + pc2.getPT()*matrix[3][3] + pc2.getPIndel()*matrix[3][4]) +
					pc1.getPIndel()*(pc2.getPA()*matrix[4][0] + pc2.getPC()*matrix[4][1] + pc2.getPG()*matrix[4][2] + pc2.getPT()*matrix[4][3] + pc2.getPIndel()*matrix[4][4]);
	}
	
	@Override
	public float getScoreAgainstIndel(ProfileChar pc1) {
		return pc1.getPA()*matrix[0][4] + pc1.getPC()*matrix[1][4] + pc1.getPG()*matrix[2][4] + pc1.getPT()*matrix[3][4] + pc1.getPIndel()*matrix[4][4];
	}
}

