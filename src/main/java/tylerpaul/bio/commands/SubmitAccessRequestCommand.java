package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.daos.IAccessRequestDAO;
import tylerpaul.bio.models.AccessRequest;
import tylerpaul.bio.service.IAccessRequestManager;

@Component
public class SubmitAccessRequestCommand implements ICommand {
	@Autowired
	private IAccessRequestManager accessRequestManager;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String description = request.getParameter("description");
		String email = request.getParameter("email");
		AccessRequest accessRequest = new AccessRequest();
		accessRequest.setUsername(username);
		accessRequest.setPassword(password);
		accessRequest.setDescription(description);
		accessRequest.setEmail(email);
		accessRequestManager.addRequest(accessRequest);
		return "/jsp/index.jsp";
	}

}
