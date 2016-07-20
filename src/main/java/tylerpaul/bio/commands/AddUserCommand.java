package tylerpaul.bio.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tylerpaul.bio.daos.IUserDAO;
import tylerpaul.bio.models.User;
import tylerpaul.bio.service.IUserManager;
import tylerpaul.bio.util.Hasher;

@Component
public class AddUserCommand implements ICommand {
	@Autowired
	private IUserManager userManager;
	
	public AddUserCommand() {
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = Hasher.md5Hash(request.getParameter("password"));
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		userManager.addUser(user);
		return "/jsp/index.jsp";
	}

}
