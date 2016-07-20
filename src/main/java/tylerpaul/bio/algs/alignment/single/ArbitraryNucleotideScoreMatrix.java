package tylerpaul.bio.algs.alignment.single;

import java.util.HashMap;
import java.util.Map;

import tylerpaul.bio.algs.alignment.ISettable;
import tylerpaul.bio.algs.alignment.Symbol;

public class ArbitraryNucleotideScoreMatrix implements INucleotideScorer, ISettable {
	private Map<Character, Integer> map;
	private int[][] matrix;
	
	public ArbitraryNucleotideScoreMatrix() {
		matrix = new int[5][5];
		map = new HashMap<Character, Integer>();
		map.put('A', 0);
		map.put('C', 1);
		map.put('G', 2);
		map.put('T', 3);
		map.put('-', 4);
	}
	
	public void setEntry(Symbol a, Symbol b, int score) {
		matrix[a.ordinal()][b.ordinal()] = score;
		matrix[b.ordinal()][a.ordinal()] = score;
	}
	
	@Override
	public int getScore(char a, char b) {
		return matrix[map.get(Character.toUpperCase(a))][map.get(Character.toUpperCase(b))];
	}
}

