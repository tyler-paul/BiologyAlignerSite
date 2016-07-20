package tylerpaul.bio.algs.alignment.single;

import java.util.HashMap;
import java.util.Map;

public class BasicNucleotideScoreMatrix implements INucleotideScorer {
	private Map<Character, Integer> map;
	private int[][] matrix;
	
	public BasicNucleotideScoreMatrix(int mu, int sigma) {
		map = new HashMap<Character, Integer>();
		map.put('A', 0);
		map.put('C', 1);
		map.put('G', 2);
		map.put('T', 3);
		map.put('-', 4);
		
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
	public int getScore(char a, char b) {
		return matrix[map.get(Character.toUpperCase(a))][map.get(Character.toUpperCase(b))];
	}

}
