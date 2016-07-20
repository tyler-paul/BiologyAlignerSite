package tylerpaul.bio.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import tylerpaul.bio.commands.SubmitAccessRequestCommand;

@WebServlet("/AccessRequestServlet")
public class AccessRequestServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SubmitAccessRequestCommand submitAccessRequestCommand;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dispathString = submitAccessRequestCommand.execute(request, response);
		RequestDispatcher view = request.getRequestDispatcher(dispathString);
		view.forward(request, response);
	}
}
