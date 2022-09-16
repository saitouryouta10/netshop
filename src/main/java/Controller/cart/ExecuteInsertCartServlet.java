package Controller.cart;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Config.Validation;
import Model.cart.CartBusinessLogic;
import Model.cart.db.CartDto;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class ExecuteAddCart
 */
@WebServlet("/ExecuteInsertCartServlet")
public class ExecuteInsertCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteInsertCartServlet() {
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

			/**
			 * isCount: 既にカートに商品が入っているか確認
			 * isSuccess: insert or update フラグ
			 */
			boolean isCount = true;
			boolean isSuccess = true;

			//			item_idと購入個数の取得
			if (!(request.getParameter("stock") == null)) {
				Validation v = new Validation();
				if (v.ValidationNumber(request.getParameter("stock"))) {

					int itemId = Integer.parseInt(request.getParameter("itemId"));
					int stock = Integer.parseInt(request.getParameter("stock"));

					//			BusinessLogicとcartのインスタンス化
					CartBusinessLogic cBL = new CartBusinessLogic();
					CartDto cartDto = new CartDto();

					//			ユーザIDの取得
					LoginDto userDto = (LoginDto) session.getAttribute("loginData");

					//			その人のカート情報の取得
					List<CartDto> cartDtoList = cBL.executeSelect(userDto.getId());

					//			既にカートに同じ商品が入っていればフラグにfalseを代入
					for (int i = 0; i < cartDtoList.size(); i++) {
						CartDto dto = cartDtoList.get(i);
						if (dto.getItem_id() == itemId) {
							isCount = false;
						}
					}

					//			フラグがたっていればinsertたっていなければupdate
					//			Todo:insertなのにselectとupdateもやっている修正したい
					if (isCount) {
						cartDto.setUser_id(userDto.getId());
						cartDto.setItem_id(itemId);
						cartDto.setNumber(stock);

						isSuccess = cBL.executeInsert(cartDto);
					} else {
						isSuccess = cBL.executeUpdate(itemId, stock, userDto.getId());
					}

					if (isSuccess) {
						response.sendRedirect("ExecuteSelectItemServlet?itemId=" + itemId);
					} else {
						response.sendRedirect("htmls/error.html");
					}
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
