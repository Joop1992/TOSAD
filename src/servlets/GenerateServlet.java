package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.GeneratorController;


public class GenerateServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("selectedRule");
		String language = req.getParameter("language").toUpperCase();

		List<String> generatedCodes = null;
		GeneratorController gc = new GeneratorController();
		if(code == null || code.equals("null")){
			code = "";
		}
		generatedCodes = gc.generate(new GeneratorController.GenerateParameter(code, language));
		
	/*	
		try {
			//Define in which class the to-be invoked method is
			Class<?> clas = Class.forName("controller.ControllerService");
			//Define the name of the method (generic) and the parameter
			Method method = clas.getDeclaredMethod("generate" + language, String.class);
			//if there is no code specified, make it an empty string
			if(code == null || code.equals("null")){
				code = "";
			}
			//invoke the method with our GeneratorController Object and Trigger-Code
			Object o = method.invoke(null, code);
			//parse the return value of the invoked method to an ArrayList
			generatedCodes = (ArrayList<String>) o;
		} catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}
		*/
		
		//create an Id for the multiple triggers
		int stringId = 0;

		/* create a hashmap with a Trigger Id (stringId) and the trigger code (genCode)
		** we place this hashmap in the session, this way we can retrieve this map with
		** the Insert servlet. The front-end can now pass the Key of the hashmap on in 
		** the link to this insert servlet, and show the value on screen.
		** this way the insert servlet has the key and can access the value and insert
		** that into the tool database
		*/ 
		HashMap<Integer, String> hmap = new HashMap<Integer, String>();
		for (String genCode : generatedCodes) {
			stringId++;
			hmap.put(stringId, genCode);
		}

		req.getSession().setAttribute("rulesHmap", hmap);
		req.getRequestDispatcher("result.jsp").forward(req, resp);

	}

}
