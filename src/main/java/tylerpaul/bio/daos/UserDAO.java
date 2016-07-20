package tylerpaul.bio.daos;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tylerpaul.bio.models.User;
import tylerpaul.bio.models.UserRole;
import tylerpaul.bio.util.Hasher;

@Repository
public class UserDAO implements IUserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();

		session.save(user);
		UserRole defaultRole = new UserRole();
		defaultRole.setUsername(user.getUsername());
		defaultRole.setRolename("regular_user");
		session.save(defaultRole);
	}
	
	@Override
	public User getUser(String username) {
		Session session = sessionFactory.getCurrentSession();

		String hql = "FROM User user WHERE user.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		User user = (User) query.list().get(0);
		
		return user;
	}

	@Override
	public User authenticateUser(String username, String password) throws Exception {
		Session session = sessionFactory.getCurrentSession();

		String hql = "FROM User user WHERE user.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		User user = (User) query.list().get(0);

		String hash = Hasher.md5Hash(password);
		if (hash == null || !user.getPassword().equals(hash))
			throw new Exception();

		return user;
	}
}
