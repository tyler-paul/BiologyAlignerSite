package tylerpaul.bio.algs.alignment.multiple;


import tylerpaul.bio.algs.alignment.Symbol;

public class ProfileChar {
	private int numA;
	private int numC;
	private int numG;
	private int numT;
	private int numIndel;
	private float pA;
	private float pC;
	private float pG;
	private float pT;
	private float pIndel;
	private int totalSymbols;
	
	ProfileChar() {
		numA = 0; numC = 0; numG = 0; numT = 0; numIndel = 0;
		pA = 0; pC = 0; pG = 0; pT = 0; pIndel = 0;
	}
	
	ProfileChar(Symbol symbol) {
		this();
		addSymbol(symbol);
	}
	
	ProfileChar(ProfileChar a, ProfileChar b) {
		this();
		numA = a.numA + b.numA;
		numC = a.numC + b.numC;
		numG = a.numG + b.numG;
		numT = a.numT + b.numT;
		numIndel = a.numIndel + b.numIndel;
		totalSymbols = a.totalSymbols + b.totalSymbols;
		pA = ((float) numA)/totalSymbols;
		pC = ((float) numC)/totalSymbols;
		pG = ((float) numG)/totalSymbols;
		pT = ((float) numT)/totalSymbols;
		pIndel = ((float) numIndel)/totalSymbols;
	}
	
	public void addSymbol(Symbol symbol) {
		if (symbol == Symbol.A) {
			numA++;
			totalSymbols++;
			pA = ((float) numA)/totalSymbols;
		}
		else if (symbol == Symbol.C) {
			numC++;
			totalSymbols++;
			pC = ((float) numC)/totalSymbols;
		}
		else if (symbol == Symbol.G) {
			numG++;
			totalSymbols++;
			pG = ((float) numG)/totalSymbols;
		}
		else if (symbol == Symbol.T) {
			numT++;
			totalSymbols++;
			pT = ((float) numT)/totalSymbols;
		}
		else if (symbol == Symbol.INDEL) {
			numIndel++;
			totalSymbols++;
			pIndel = ((float) numIndel)/totalSymbols;
		}
	}
	
	public float getPA() {
		return pA;
	}
	public float getPC() {
		return pC;
	}
	public float getPG() {
		return pG;
	}
	public float getPT() {
		return pT;
	}
	public float getPIndel() {
		return pIndel;
	}
	public float getPSymbol(Symbol symbol) {
		if (symbol == Symbol.A)
			return pA;
		else if (symbol == Symbol.C)
			return pC;
		else if (symbol == Symbol.G)
			return pG;
		else if (symbol == Symbol.T)
			return pT;
		else// if (symbol == Symbol.INDEL)
			return pIndel;
	}
}
