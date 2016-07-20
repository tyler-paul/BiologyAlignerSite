package tylerpaul.bio.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sequence_alignment")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	name="discriminator",
	discriminatorType=DiscriminatorType.STRING
)

public class SequenceAlignment {
	@Id
	@GeneratedValue
	@Column(name="sequence_alignment_id")
	private int id;
	@Column(name="parts")
	@OneToMany(mappedBy = "sequenceAlignment", cascade = CascadeType.ALL)
	private List<SequenceAlignmentPart> parts;
	@ManyToOne
	@JoinColumn(name = "user_name")
	private User owner;
	@Column(name="discriminator", nullable=false, updatable=false, insertable=false)
	private String discriminator;
	@Column(name="status")
	private String status;
	@Column(name="description")
	private String description;
	@Column(name="is_local")
	private boolean isLocal;
	
	public SequenceAlignment() {
		this.parts = new ArrayList<SequenceAlignmentPart>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<SequenceAlignmentPart> getParts() {
		return parts;
	}
	public List<String> getPartStrings() {
		List<String> partStrings = new ArrayList<String>();
		for (SequenceAlignmentPart part : getParts())
			partStrings.add(part.getData());
		return partStrings;
	}
	public List<String> getPartNames() {
		List<String> partNames = new ArrayList<String>();
		for (SequenceAlignmentPart part : getParts())
			partNames.add(part.getTaxon());
		return partNames;
	}
	public void setParts(List<SequenceAlignmentPart> parts) {
		this.parts = parts;
	}
	public void addPart(SequenceAlignmentPart part) {
		parts.add(part);
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getDiscriminator() {
		return discriminator;
	}
	public void setDiscriminator(String discriminator) {
		this.discriminator = discriminator;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean getIsLocal() {
		return isLocal;
	}
	public void setIsLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}
}
