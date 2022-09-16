package Controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Config.Validation;
import Model.users.LoginBusinessLogic;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class ValidationPasswordServlet
 */
@WebServlet("/ValidationPasswordServlet")
public class ValidationPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidationPasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		if (session.getAttribute("loginData") != null) {
			Validation v = new Validation();
			LoginDto userDto = (LoginDto) session.getAttribute("loginData");
			if (v.ValidationPass(userDto.getPass(), request.getParameter("oldPassword"))) {
				String pass = request.getParameter("newPassword");
				String passConf = request.getParameter("newPasswordConf");
				if (v.ValidationPass(pass, passConf)) {
					userDto.setPass(pass);
					LoginBusinessLogic lBL = new LoginBusinessLogic();
					boolean isSuccess = lBL.executeUpdatePass(pass, userDto.getId());
					if (isSuccess) {
						RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/memberSuccess.jsp");
						dispatch.forward(request, response);
					} else {
						response.sendRedirect("htmls/error.html");
					}
				} else {
					String error = "error";
					request.setAttribute("error", error);
					RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/changePassword.jsp");
					dispatch.forward(request, response);
				}
			} else {
				String error = "error";
				request.setAttribute("error", error);
				RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/changePassword.jsp");
				dispatch.forward(request, response);
			}

		} else {
			response.sendRedirect("LoginServlet");
		}
	}

}
