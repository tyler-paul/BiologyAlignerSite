package tylerpaul.bio.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import tylerpaul.bio.commands.*;

@WebServlet("/Controller")
public class Controller extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private Map<String, ICommand> commandMap;

	public Controller() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		action = Character.toLowerCase(action.charAt(0)) + action.substring(1, action.length()) + "Command";
		String dispathString = commandMap.get(action).execute(request, response);
		RequestDispatcher view = request.getRequestDispatcher(dispathString);
		view.forward(request, response);
	}
}
