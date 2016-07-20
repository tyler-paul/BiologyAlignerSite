package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.daos.IAccessRequestDAO;
import tylerpaul.bio.service.IAccessRequestManager;

@Component
public class ViewAccessRequestsCommand implements ICommand {
	@Autowired
	private IAccessRequestManager accessRequestManager;
	
	public ViewAccessRequestsCommand() {
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("requests", accessRequestManager.getRequests());
		return "/jsp/confirmUsers.jsp";
	}

}
