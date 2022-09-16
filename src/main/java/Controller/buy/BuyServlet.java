package Controller.buy;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.buy.BuyBusinessLogic;
import Model.cart.db.CartDto;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyServlet() {
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
		
		HttpSession session = request.getSession();

		if (session.getAttribute("loginData") != null && session.getAttribute("cartInfo") != null ) {
			boolean isSuccess = true;

			BuyBusinessLogic bBL = new BuyBusinessLogic();
			List<CartDto> dto = (List<CartDto>) session.getAttribute("cartInfo");
			isSuccess = bBL.executeBuy(dto);

			if (isSuccess) {
				session.removeAttribute("cartInfo");
				request.setAttribute("cartInfo", dto);
				RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/buySuccess.jsp");
				dispatch.forward(request, response);
			} else {
				response.sendRedirect("htmls/error.jsp");
			}
		} else {
			response.sendRedirect("HomeServlet");
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
