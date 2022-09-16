package Controller.inquiry;

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
 * Servlet implementation class ValidationInquiryServlet
 */
@WebServlet("/ValidationInquiryServlet")
public class ValidationInquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidationInquiryServlet() {
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

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String title = request.getParameter("title");
		String inquiry = request.getParameter("inquiry");

		Map<String, String> inquiryData = new HashMap<>();
		inquiryData.put("name", name);
		inquiryData.put("email", email);
		inquiryData.put("title", title);
		inquiryData.put("inquiry", inquiry);
		session.setAttribute("inquiryData", inquiryData);
		

		if (lb.ValidationInquiry(name, title, email, inquiry)) {
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/checkInquiry.jsp");
			dispatch.forward(request, response);
		} else {
			String e = "error";
			request.setAttribute("error", e);
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/inquiry.jsp");
			dispatch.forward(request, response);
		}

	}

}
