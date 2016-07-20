package tylerpaul.bio.algs.alignment;

public enum Symbol {
	A('A'), C('C'), G('G'), T('T'), INDEL('-');

	private final char symbolChar;

	Symbol(char symbolChar) {
		this.symbolChar = symbolChar;
	}
	
	public char getChar() {
		return symbolChar;
	}
	
	public static Symbol getSymbol(char c) {
		if (c == 'A')
			return Symbol.A;
		else if (c == 'C')
			return Symbol.C;
		else if (c == 'G')
			return Symbol.G;
		else if (c == 'T')
			return Symbol.T;
		else// if (c == '-')
			return Symbol.INDEL;
	}
}

