package tylerpaul.bio.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "user_name")
	private String username;
	@Column(name = "user_pass")
	private String password;
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private Set<Sequence> sequences;
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private Set<SequenceAlignment> alignments;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Sequence> getSequences() {
		return sequences;
	}

	public void setSequences(Set<Sequence> sequences) {
		this.sequences = sequences;
	}

	public Set<SequenceAlignment> getAlignments() {
		return alignments;
	}

	public void setAlignments(Set<SequenceAlignment> alignments) {
		this.alignments = alignments;
	}
}
