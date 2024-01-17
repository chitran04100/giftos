package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {

			switch (action) {
			case "LOGIN": {
				logIn(request, response);
				break;
			}

			case "LOGOUT": {
				logOut(request, response);
				break;
			}

			case "REGISTER": {
				register(request, response);
				break;
			}

			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		User user = new User(userName, password);

		user.setUserName(userName);
		user.setPassword(password);

		String userValidate;

		try {
			userValidate = UserDAO.getUserByUsernameAndPassword(userName, password);

			if (userValidate.equals("SUCCESS")) {
				request.setAttribute("userName", userName);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				String errorMessage = "Username and password are incorrect. Please try again.";
				request.setAttribute("errMessage", errorMessage);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("Home");
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		User user = new User(userName, password, email);

		String registeredAccout;
		try {
			registeredAccout = UserDAO.registerUser(userName, password, email);

			switch (registeredAccout) {
			case "alreadyRegisteredUser":
				request.setAttribute("alreadyRegisteredUser",
						"Your account is already registered. Please try again with different username!");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				break;
			case "successRegistered":
				request.setAttribute("successRegistered", "Your account is registered successfully!");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				break;
			case "failRegistered":
				request.setAttribute("failRegistered", "Your account is failed to registered. Please try again!");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
