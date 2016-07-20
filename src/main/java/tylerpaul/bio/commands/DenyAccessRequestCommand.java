package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.service.IAccessRequestManager;


@Component
public class DenyAccessRequestCommand implements ICommand {
	@Autowired
	private IAccessRequestManager accessRequestManager;
	
	public DenyAccessRequestCommand() {
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int accessRequestID = Integer.parseInt(request.getParameter("id"));
		accessRequestManager.denyRequest(accessRequestID);
		
		return "/Controller?action=ViewAccessRequests";
	}

}
