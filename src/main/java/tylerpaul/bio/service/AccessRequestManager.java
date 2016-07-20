package tylerpaul.bio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tylerpaul.bio.daos.IAccessRequestDAO;
import tylerpaul.bio.daos.IUserDAO;
import tylerpaul.bio.models.AccessRequest;
import tylerpaul.bio.models.User;
import tylerpaul.bio.util.Hasher;

@Service
public class AccessRequestManager implements IAccessRequestManager {
	@Autowired
	private IAccessRequestDAO accessRequestDAO;
	@Autowired
	private IUserDAO userDAO;
	
	@Override
	@Transactional
	public void addRequest(AccessRequest accessRequest) {
		accessRequestDAO.addRequest(accessRequest);
	}

	@Override
	@Transactional
	public List<AccessRequest> getRequests() {
		return accessRequestDAO.getRequests();
	}

	@Override
	@Transactional
	public AccessRequest getRequest(int accessRequestID) {
		return accessRequestDAO.getRequest(accessRequestID);
	}

	@Override
	@Transactional
	public void deleteRequest(AccessRequest accessRequest) {
		accessRequestDAO.deleteRequest(accessRequest);
	}
	
	@Override
	@Transactional
	public void confirmRequest(int accessRequestID) {
		AccessRequest accessRequest = accessRequestDAO.getRequest(accessRequestID);
		User user = new User();
		user.setUsername(accessRequest.getUsername());
		user.setPassword(Hasher.md5Hash(accessRequest.getPassword()));
		userDAO.addUser(user);
		accessRequestDAO.deleteRequest(accessRequest);
	}
	
	@Override
	@Transactional
	public void denyRequest(int accessRequestID) {
		AccessRequest accessRequest = accessRequestDAO.getRequest(accessRequestID);
		accessRequestDAO.deleteRequest(accessRequest);
	}
}
