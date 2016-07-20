package tylerpaul.bio.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class LogoutCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			request.logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		return "/jsp/index.jsp";
	}

}
