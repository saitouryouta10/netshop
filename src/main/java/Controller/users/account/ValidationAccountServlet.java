package Controller.users.account;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Config.Validation;

/**
 * Servlet implementation class SaveAccount
 */
@WebServlet("/ValidationAccountServlet")
public class ValidationAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidationAccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html; charaset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Validation lb = new Validation();

		if (session.getAttribute("loginData") == null) {
			String name = request.getParameter("name");
			String name_kana = request.getParameter("kana");
			String nickname = request.getParameter("nickname");
			String sex = "";
			if (lb.ValidationSex(request.getParameter("sex"))) {
				sex = request.getParameter("sex");
			} else {
				sex = "0";
			}
			String birthday = "";
			if (lb.ValidationSex(request.getParameter("date"))) {
				birthday = request.getParameter("sex");
			} else {
				birthday = "0000-00-00";
			}
			String zipcode = request.getParameter("zipcode");
			String address = request.getParameter("address");
			String tell = request.getParameter("tell");
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			String passConf = request.getParameter("passConfirmation");

			Map<String, String> createData = new HashMap<>();
			createData.put("name", name);
			createData.put("name_kana", name_kana);
			createData.put("nickname", nickname);

			createData.put("sex", sex);
			createData.put("birthday", birthday);
			createData.put("zipcode", zipcode);
			createData.put("address", address);
			createData.put("tell", tell);
			createData.put("email", email);
			createData.put("pass", pass);
			session.setAttribute("createDataHash", createData);

			if (lb.ValidationName(name) &&
					lb.ValidationNameKana(name_kana) &&
					lb.ValidationNickName(nickname) &&
					lb.ValidationZipcode(zipcode) &&
					lb.ValidationAddress(address) &&
					lb.ValidationTell(tell) &&
					lb.ValidationPass(pass, passConf)) {

				RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/checkAccountContents.jsp");
				dispatch.forward(request, response);

			} else {
				String e = "error";
				request.setAttribute("error", e);
				RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/createAccount.jsp");
				dispatch.forward(request, response);
			}

		} else {
			response.sendRedirect("HomeServlet");
		}

	}

}
