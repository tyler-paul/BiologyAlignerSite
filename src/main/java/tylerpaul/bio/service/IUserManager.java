package tylerpaul.bio.service;

import tylerpaul.bio.models.User;

public interface IUserManager {
	public void addUser(User user);
	public User getUser(String username);
}
