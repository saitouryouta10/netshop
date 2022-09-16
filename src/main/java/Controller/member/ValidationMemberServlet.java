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
 * Servlet implementation class ValidationMemberServlet
 */
@WebServlet("/ValidationMemberServlet")
public class ValidationMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/createAccount.jsp");
		dispatch.forward(request, response);
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

		if (session.getAttribute("loginData") != null) {

			LoginBusinessLogic lBL = new LoginBusinessLogic();
			LoginDto dto = (LoginDto)session.getAttribute("loginData");
			Validation lb = new Validation();
			
			String name = request.getParameter("name");
			String name_kana = request.getParameter("name_kana");
			String nickname = request.getParameter("nickname");
			String sex = request.getParameter("sex");
			String birthday = request.getParameter("date");
			String zipcode = request.getParameter("zipcode");
			String address = request.getParameter("address");
			String tell = request.getParameter("tell");
			String email = request.getParameter("email");

			if (lb.ValidationName(name) &&
					lb.ValidationNameKana(name_kana) &&
					lb.ValidationNickName(nickname) &&
					lb.ValidationZipcode(zipcode) &&
					lb.ValidationAddress(address) &&
					lb.ValidationTell(tell)) {

				dto.setName(name);
				dto.setName_kana(name_kana);
				dto.setNickname(nickname);

				if (lb.ValidationSex(sex)) {
					dto.setSex(Integer.parseInt(sex));
				} else {
					dto.setSex(0);
				}

				if (lb.ValidationBirthday(birthday)) {
					dto.setBirthday(birthday);
				} else {
					dto.setBirthday("0000-00-00");
				}

				dto.setZipcode(zipcode);
				dto.setAddress(address);
				dto.setTell(tell);
				dto.setEmail(email);
				
				boolean isSuccess = lBL.executeUpdate(dto);
				
				if (isSuccess) {
					RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/memberSuccess.jsp");
					dispatch.forward(request, response);
				} else {
					response.sendRedirect("htmls/error.html");
				}
			} else {
				
				String e = "error";
				request.setAttribute("error", e);
				RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/changeMemberInfo.jsp");
				dispatch.forward(request, response);
			}
			
		} else {
			response.sendRedirect("LoginServlet");
		}

	}

}
