package tylerpaul.bio.service;

import java.util.List;

import tylerpaul.bio.models.AccessRequest;

public interface IAccessRequestManager {
	public void addRequest(AccessRequest accessRequest);

	public List<AccessRequest> getRequests();

	public AccessRequest getRequest(int accessRequestID);

	public void deleteRequest(AccessRequest accessRequest);

	public void confirmRequest(int accessRequestID);

	public void denyRequest(int accessRequestID);
}
