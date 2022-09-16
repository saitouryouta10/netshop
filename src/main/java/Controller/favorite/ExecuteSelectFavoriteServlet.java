package Controller.favorite;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.favorite.FavoriteBusinessLogic;
import Model.favorite.db.FavoriteDto;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class ExecuteSelectFavoriteServlet
 */
@WebServlet("/ExecuteSelectFavoriteServlet")
public class ExecuteSelectFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteSelectFavoriteServlet() {
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

		if (session.getAttribute("loginData") != null) {

			LoginDto userDto = (LoginDto) session.getAttribute("loginData");

			FavoriteBusinessLogic fBL = new FavoriteBusinessLogic();

			List<FavoriteDto> dtoList = fBL.executeSelect(userDto.getId());

			request.setAttribute("favoriteInfo", dtoList);
			RequestDispatcher dispatch = request
					.getRequestDispatcher("WEB-INF/jsp/favorite.jsp");
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
