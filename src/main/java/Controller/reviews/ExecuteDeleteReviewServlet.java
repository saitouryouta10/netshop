package Controller.reviews;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Config.Validation;
import Model.reviews.ReviewsBusinessLogic;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class DeleteReviews
 */
@WebServlet("/ExecuteDeleteReviewServlet")
public class ExecuteDeleteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteDeleteReviewServlet() {
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
			Validation v = new Validation();
			if (v.ValidationNumber(request.getParameter("itemId"))) {
				int reviewId = Integer.parseInt(request.getParameter("reviewId"));
				int itemId = Integer.parseInt(request.getParameter("itemId"));

				boolean isDelete = true;
				LoginDto userDto = (LoginDto) session.getAttribute("loginData");
				int userId = userDto.getId();

				ReviewsBusinessLogic rBL = new ReviewsBusinessLogic();
				isDelete = rBL.executeDelete(reviewId, userId);

				if (isDelete) {
					RequestDispatcher dispatch = request
							.getRequestDispatcher("ExecuteSelectReviewServlet?itemId=" + itemId);
					dispatch.forward(request, response);
				} else {
					response.sendRedirect("htmls/error.html");
				}

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
