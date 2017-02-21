package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class InsertServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String stringId = req.getParameter("stringId");
		//retrieve previously defined hashmap
		HashMap<Integer, String> hmap = (HashMap<Integer, String>)req.getSession().getAttribute("rulesHmap");
		String errorOrSuccessMessage = null;
		Controller controller = new Controller();
		
		//if 'insert single trigger' has been pressed, retrieve the value and insert the trigger.
		if(stringId != null && !stringId.equalsIgnoreCase("null")&& !stringId.equals("") &&!stringId.equals(" ")){
			int hmapKey = Integer.parseInt(stringId);
			String trigger = hmap.get(hmapKey);
			errorOrSuccessMessage = controller.insertTrigger(trigger);
			
		}
		//else ('insert all'), loop through the HashMap and insert every trigger
		else{ 
			Set set = hmap.entrySet();
			Iterator iterator = set.iterator();
			while(iterator.hasNext()) {
				Map.Entry mentry = (Map.Entry)iterator.next();
				errorOrSuccessMessage = controller.insertTrigger(mentry.getValue().toString());
			}
			
		}
		//error or succes message (either 'successfull inserted' or a stacktrace for front-end
		req.getSession().setAttribute("errorOrSuccess", errorOrSuccessMessage);
		req.getRequestDispatcher("inserted.jsp").forward(req, resp);
	}

}
