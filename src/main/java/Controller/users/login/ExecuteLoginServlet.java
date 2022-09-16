package Controller.users.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.users.LoginBusinessLogic;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class ExecuteLogin
 */
@WebServlet("/ExecuteLoginServlet")
public class ExecuteLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html; charaset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		if (session.getAttribute("loginData") == null) {
			
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");

			LoginBusinessLogic lBL = new LoginBusinessLogic();
			LoginDto userDto = lBL.executeLogin(email, pass);

			if (userDto.getName() != null) {

				session.setAttribute("loginData", userDto);
				response.sendRedirect("HomeServlet");

			} else {
				String missmatch = "no";
				request.setAttribute("missmatch", missmatch);
				RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
				dispatch.forward(request, response);

			}
		} else {
			response.sendRedirect("HomeServlet");
		}

	}

}
