package tylerpaul.bio.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "user_name")
	private String username;
	@Id
	@Column(name = "role_name")
	private String rolename;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
