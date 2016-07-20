package tylerpaul.bio.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tylerpaul.bio.models.AccessRequest;

@Repository
public class AccessRequestDAO implements IAccessRequestDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addRequest(AccessRequest accessRequest) {
		Session session = sessionFactory.getCurrentSession();
		
		session.save(accessRequest);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccessRequest> getRequests() {
		Session session = sessionFactory.getCurrentSession();
		
		List<AccessRequest> requests;
		String hql = "FROM AccessRequest";
		Query query = session.createQuery(hql);
		requests = query.list();
		
		return requests;
	}
	
	@Override
	public AccessRequest getRequest(int accessRequestID) {
		Session session = sessionFactory.getCurrentSession();
		
		AccessRequest accessRequest = null;
		String hql = "FROM AccessRequest accessRequest WHERE accessRequest.id = :accessRequestID";
		Query query = session.createQuery(hql);
		query.setParameter("accessRequestID", accessRequestID);
		accessRequest = (AccessRequest) query.list().get(0);

		return accessRequest;
	}
	
	@Override
	public void deleteRequest(AccessRequest accessRequest) {
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(accessRequest);
	}
}
