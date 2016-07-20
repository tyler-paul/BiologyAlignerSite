package tylerpaul.bio.daos;

import tylerpaul.bio.models.User;

public interface IUserDAO {

	void addUser(User user);

	User getUser(String username);

	User authenticateUser(String username, String password) throws Exception;

}