package tylerpaul.site.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tylerpaul.site.daos.ICategoryDAO;
import tylerpaul.site.daos.IImageDAO;
import tylerpaul.site.daos.IUserDAO;
import tylerpaul.site.daos.UserDAO;
import tylerpaul.site.util.BeanGetter;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserDAO userDAO;

	public UserController() {
		super();
		userDAO = new UserDAO();//(IUserDAO)BeanGetter.getBean("userDAO");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("Logout")) {
			request.getSession().removeAttribute("user");
			RequestDispatcher view = request.getRequestDispatcher("/Index");
			view.forward(request, response);
		} else if (action.equals("Login")) {
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			view.forward(request, response);
		} else if (action.equals("Add")) {
			RequestDispatcher view = request.getRequestDispatcher("addUser.jsp");
			view.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("Add")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			userDAO.addUser(username, password);
		}
		else if (action.equals("Authenticate")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			boolean isAuthenticated = userDAO.authenticateUser(username, password);
			if (isAuthenticated) {
				request.getSession().setAttribute("user", username);
			}
		}
		else if (action.equals("Logout")) {
			request.getSession().removeAttribute("user");
		}
		RequestDispatcher view = request.getRequestDispatcher("/Index");
		view.forward(request, response);
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
