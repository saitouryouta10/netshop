package Controller.items;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.items.ItemsBusinessLogic;
import Model.items.db.ItemsDto;

/**
 * Servlet implementation class Home
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
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

		ItemsBusinessLogic iBL = new ItemsBusinessLogic();

		List<ItemsDto> itemsDto = new ArrayList<>();

		if (request.getParameter("search") != null) {

			String itemName = request.getParameter("search");
			itemsDto = iBL.executeSearchSelect(itemName);

		} else {
			itemsDto = iBL.executeAllSelect();

		}
		request.setAttribute("allItemInfo", itemsDto);
		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
		dispatch.forward(request, response);
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
