package tylerpaul.bio.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sequence_alignment_part")
public class SequenceAlignmentPart {
	@Id
	@GeneratedValue
	@Column(name="sequence_alignment_part_id")
	private int id;
	@Column(name="taxon")
	private String taxon;
	@Lob
	@Column(name="data")
	private String data;
	@ManyToOne
	@JoinColumn(name = "sequence_alignment_id")
	private SequenceAlignment sequenceAlignment;
	@Column(name="start_pos")
	private int startPos;
	@Column(name="end_pos")
	private int endPos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaxon() {
		return taxon;
	}
	public void setTaxon(String taxon) {
		this.taxon = taxon;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public SequenceAlignment getSequenceAlignment() {
		return sequenceAlignment;
	}
	public void setSequenceAlignment(SequenceAlignment sequenceAlignment) {
		this.sequenceAlignment = sequenceAlignment;
	}
	public int getStartPos() {
		return startPos;
	}
	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}
	public int getEndPos() {
		return endPos;
	}
	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}
}
