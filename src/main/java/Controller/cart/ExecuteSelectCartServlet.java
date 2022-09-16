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

import Model.cart.CartBusinessLogic;
import Model.cart.db.CartDto;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class CheckCart
 */
@WebServlet("/ExecuteSelectCartServlet")
public class ExecuteSelectCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteSelectCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//		文字コードの設定
		response.setContentType("text/html; charaset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//		セッションの取得(userId)
		HttpSession session = request.getSession();

		if (session.getAttribute("loginData") != null) {
			
			LoginDto userDto = (LoginDto) session.getAttribute("loginData");
			int userId = userDto.getId();

			//		ユーザのカート情報を全部取得
			CartBusinessLogic cBL = new CartBusinessLogic();
			List<CartDto> dto = cBL.executeSelect(userId);
			for (int i=0; i<dto.size(); i++) {
				CartDto d = dto.get(i);
				if (d.getStock() < d.getNumber()) {
					d.setNumber(0);
				}
			}

			//		セッションにカート情報を格納し遷移
			session.setAttribute("cartInfo", dto);
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/changeCartContents.jsp");
			dispatch.forward(request, response);
			
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
