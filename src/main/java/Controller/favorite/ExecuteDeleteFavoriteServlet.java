package Controller.favorite;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Config.Validation;
import Model.favorite.FavoriteBusinessLogic;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class ExecuteDeleteFavoriteServlet
 */
@WebServlet("/ExecuteDeleteFavoriteServlet")
public class ExecuteDeleteFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteDeleteFavoriteServlet() {
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
			boolean isSuccess = true;
			Validation v = new Validation();
			String URL = "";

			if (v.ValidationNumber(request.getParameter("itemId"))) {

				LoginDto userDto = (LoginDto) session.getAttribute("loginData");
				int itemId = Integer.parseInt(request.getParameter("itemId"));
				
				if (request.getParameter("type") != null && request.getParameter("type").equals("1")) {
					URL = "ExecuteSelectFavoriteServlet";
				} else {
					URL = "ExecuteSelectItemServlet";
				}

				FavoriteBusinessLogic fBL = new FavoriteBusinessLogic();

				isSuccess = fBL.executeDelete(userDto.getId(), itemId);

				if (isSuccess) {
					RequestDispatcher dispatch = request
							.getRequestDispatcher( URL + "?itemId=" + itemId);
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
