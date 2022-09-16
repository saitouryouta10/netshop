package Controller.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Config.Validation;
import Model.cart.CartBusinessLogic;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class UpdateCartNumber
 */
@WebServlet("/ExecuteUpdateCartServlet")
public class ExecuteUpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteUpdateCartServlet() {
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

			/**
			 * newNumber: 変更後の購入個数
			 * itemId: itemid
			 * isSuccess: update　フラグ
			 */
			Validation v = new Validation();
			if (v.ValidationNumber(request.getParameter("stock"))) {
				int newNumber = Integer.parseInt(request.getParameter("stock"));
				int itemId = Integer.parseInt(request.getParameter("itemId"));
				LoginDto dto = (LoginDto) session.getAttribute("loginData");
				boolean isSuccess = true;

				CartBusinessLogic cBL = new CartBusinessLogic();
				isSuccess = cBL.executeUpdate(itemId, newNumber, dto.getId());

				if (isSuccess) {
					response.sendRedirect("ExecuteSelectCartServlet");
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
