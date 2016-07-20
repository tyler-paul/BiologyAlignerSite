package tylerpaul.bio.algs.alignment.multiple;


public class ProfileScoreMatrix implements IProfileScorer {
	private int[][] matrix;
	
	public ProfileScoreMatrix(int mu, int sigma) {
		
		matrix = new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i < 5 && j < 5 && i != j)
					matrix[i][j] = -mu;
				else if (i == 5 || j == 5)
					matrix[i][j] = -sigma;
				else //if i < 5 && j < 5 && i == j
					matrix[i][j] = 1;
			}
		}
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
