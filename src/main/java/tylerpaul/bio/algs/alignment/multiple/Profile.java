package tylerpaul.bio.algs.alignment.multiple;

import java.util.List;

public class Profile {
	private List<ProfileChar> data;
	private ProfileContext context; //the sequences that this profile represents
	
	public Profile(List<ProfileChar> data, ProfileContext context) {
		this.data = data;
		this.context = context;
	}
	
	public Profile(List<ProfileChar> data) {
		this.data = data;
		this.context = new ProfileContext();
	}
	
	public List<ProfileChar> getData() {
		return data;
	}
	public void setData(List<ProfileChar> data) {
		this.data = data;
	}
	public ProfileContext getContext() {
		return context;
	}
	public void setContext(ProfileContext context) {
		this.context = context;
	}
}
