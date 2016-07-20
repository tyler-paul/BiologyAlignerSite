package tylerpaul.bio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tylerpaul.bio.daos.IUserDAO;
import tylerpaul.bio.models.User;

@Service
public class UserManager implements IUserManager {
	@Autowired
	private IUserDAO userDAO;
	
	@Override
	@Transactional
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	@Override
	@Transactional
	public User getUser(String username) {
		return userDAO.getUser(username);
	}
}
