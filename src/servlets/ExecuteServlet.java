package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class ExecuteServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Controller controller = new Controller();
		//retrieve all defined rules, pass on to front-end
		req.getSession().setAttribute("allRules", controller.getAllRules());
		
		req.getRequestDispatcher("select.jsp").forward(req, resp);
	}

}
