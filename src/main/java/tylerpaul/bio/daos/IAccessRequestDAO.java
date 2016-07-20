package tylerpaul.bio.daos;

import java.util.List;

import tylerpaul.bio.models.AccessRequest;

public interface IAccessRequestDAO {

	void addRequest(AccessRequest accessRequest);

	List<AccessRequest> getRequests();

	AccessRequest getRequest(int accessRequestID);

	void deleteRequest(AccessRequest accessRequest);

}