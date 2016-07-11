package next;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DataBase;
import model.User;

@WebServlet("/user/create")
public class UserCreateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		User user = new User(userId, password, name, email);
		System.out.println("User :" + user);
		
		DataBase.addUser(user);
		
		resp.sendRedirect("/index.html");
	}
	
	
//	User user = new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"),
//			request.getParameter("email"));log.debug("user : {}",user);DataBase.addUser(user);
//
//	sendRedirect("index.html");
}
