package Controller.cart;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.cart.db.CartDto;

/**
 * Servlet implementation class CheckCartServlet
 */
@WebServlet("/CheckCartServlet")
public class CheckCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charaset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//		セッションの取得(userId)
		HttpSession session = request.getSession();

		if (session.getAttribute("loginData") != null) {
			if (session.getAttribute("cartInfo") != null) {

				List<CartDto> dto = (List<CartDto>) session.getAttribute("cartInfo");
				if (dto.size() != 0) {
					for (int i = 0; i < dto.size(); i++) {
						CartDto d = dto.get(i);
						if (d.getNumber() == 0) {
							RequestDispatcher dispatch = request.getRequestDispatcher("htmls/error.html");
							dispatch.forward(request, response);
						}
					}
					RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/checkCartContents.jsp");
					dispatch.forward(request, response);
				} else {
					response.sendRedirect("htmls/error.html");
				}
			} else {
				response.sendRedirect("htmls/error.html");
			}

		} else {
			response.sendRedirect("LoginServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
