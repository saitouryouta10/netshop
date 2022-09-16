package Controller.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.cart.CartBusinessLogic;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class ExecuteDeleteCart
 */
@WebServlet("/ExecuteDeleteCartServlet")
public class ExecuteDeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteDeleteCartServlet() {
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
		HttpSession session = request.getSession();

		if (session.getAttribute("loginData") != null) {

			//		削除対象のカートIDと前画面で取得したユーザID(ほかのユーザのものを表示させないため)
			//			Todo: userIdのハッシュ化
			int cartId = Integer.parseInt(request.getParameter("cartId"));

			//		セッションでユーザIDの取得
			LoginDto sessionUserDto = (LoginDto) session.getAttribute("loginData");
			int userId = sessionUserDto.getId();

			//		正しければ削除を実行し遷移
			CartBusinessLogic cBL = new CartBusinessLogic();
			boolean isDelete = cBL.executeDlete(cartId, userId);

			if (isDelete) {
				response.sendRedirect("ExecuteSelectCartServlet");
			} else {
				response.sendRedirect("htmls/error.html");
			}
		} else {
			response.sendRedirect("htmls/error.html");
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
