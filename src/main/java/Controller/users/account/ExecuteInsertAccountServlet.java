package Controller.users.account;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.users.LoginBusinessLogic;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class ExecuteSaveAccount
 */
@WebServlet("/ExecuteInsertAccountServlet")
public class ExecuteInsertAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteInsertAccountServlet() {
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
		response.setContentType("text/html; character=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		if (session.getAttribute("loginData") == null) {

			if (session.getAttribute("createDataHash") != null) {
				boolean isSuccess;
				LoginBusinessLogic lBL = new LoginBusinessLogic();
				LoginDto dto = new LoginDto();
				HashMap<String, String> createdata = (HashMap<String, String>) session.getAttribute("createDataHash");

				dto.setName(createdata.get("name"));
				dto.setName_kana(createdata.get("name_kana"));
				dto.setNickname(createdata.get("nickname"));
				dto.setSex(Integer.parseInt(createdata.get("sex")));
				dto.setBirthday(createdata.get("birthday"));
				dto.setZipcode(createdata.get("zipcode"));
				dto.setAddress(createdata.get("address"));
				dto.setTell(createdata.get("tell"));
				dto.setEmail(createdata.get("email"));
				dto.setPass(createdata.get("pass"));

				isSuccess = lBL.executeCreate(dto);

				if (isSuccess) {
					session.invalidate();
					response.sendRedirect("htmls/success.html");
				} else {
					response.sendRedirect("htmls/error.html");
				}
			} else {
				response.sendRedirect("CreateAccountServlet");
			}
		} else {
			response.sendRedirect("HomeServlet");
		}

	}

}
