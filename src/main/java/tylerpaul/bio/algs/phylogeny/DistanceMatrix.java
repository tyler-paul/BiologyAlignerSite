package tylerpaul.bio.algs.phylogeny;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistanceMatrix {
	public Map<TaxonPair, Float> matrix; //TODO make private again
	private List<Taxon> currentTaxons;
	
	public DistanceMatrix(List<Taxon> taxons) {
		matrix = new HashMap<TaxonPair, Float>();
		currentTaxons = new ArrayList<Taxon>();
		currentTaxons.addAll(taxons);
		
		for (int i = 0; i < taxons.size() - 1; i++) {
			Taxon t1 = taxons.get(i);
			for (int j = i + 1; j < taxons.size(); j++) {
				Taxon t2 = taxons.get(j);
				float dist = (float)(t1.getSequence().length() - numMatches(t1, t2));
				matrix.put(new TaxonPair(t1, t2), dist);
				matrix.put(new TaxonPair(t2, t1), dist);
			}
		}
		//for (Taxon taxon : taxons)
		//	matrix.put(new TaxonPair(taxon, taxon), 0.0f);
	}
	
	private int numMatches(Taxon t1, Taxon t2) {
		int matches = 0;
		String s1 = t1.getSequence();
		String s2 = t2.getSequence();
		
		for (int i = 0; i < s1.length(); i++)
			if (s1.charAt(i) == s2.charAt(i))
				matches++;
		
		return matches;
	}
	
	public float d(Taxon t1, Taxon t2) {
		return matrix.get(new TaxonPair(t1, t2));
	}
	
	public Taxon mergeTaxons(Taxon t1, Taxon t2) {
//		System.out.println("removed" + (t1.getName() != null ? t1.getName() : "null"));
//		System.out.println("removed" + (t2.getName() != null ? t2.getName() : "null"));
		
		Taxon newTaxon = new Taxon();
		newTaxon.setName(t1.getName() + " and " + t2.getName());

		//add new entries
		currentTaxons.remove(t1);
		currentTaxons.remove(t2);
		for (Taxon taxon : currentTaxons) {
			float d_newTaxon_to_Taxon = (d(t1, taxon) + d(t2, taxon) - d(t1, t2)) / 2;
			matrix.put(new TaxonPair(taxon, newTaxon), d_newTaxon_to_Taxon);
			matrix.put(new TaxonPair(newTaxon, taxon), d_newTaxon_to_Taxon);
		}
		//matrix.put(new TaxonPair(newTaxon, newTaxon), 0.0f);
		currentTaxons.add(newTaxon);
		
		//remove old entries
		List<TaxonPair> toRemove = new ArrayList<TaxonPair>();
		for (TaxonPair tp : matrix.keySet()) {
			if (tp.t1 == t1 || tp.t2 == t2 || tp.t1 == t2 || tp.t2 == t1)
				toRemove.add(tp);
		}
		matrix.keySet().removeAll(toRemove);

//		System.out.println("entries " + matrix.keySet().size());
		
		return newTaxon;
	}
	
	public List<Taxon> getCurrentTaxons() {
		return currentTaxons;
	}

}

class TaxonPair {
	public Taxon t1;
	public Taxon t2;
	
	TaxonPair(Taxon t1, Taxon t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((t1 == null) ? 0 : t1.hashCode());
		result = prime * result + ((t2 == null) ? 0 : t2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof TaxonPair))
			return false;
		TaxonPair pair = (TaxonPair)obj;
		if (this.t1 == pair.t1 && this.t2 == pair.t2)
			return true;
		else
			return false;
	}
}
