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
import Model.reviews.db.ReviewsDto;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class ExecuteInsertReviewServlet
 */
@WebServlet("/ExecuteInsertReviewServlet")
public class ExecuteInsertReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteInsertReviewServlet() {
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
			LoginDto userDto = (LoginDto) session.getAttribute("loginData");
			ReviewsDto reviewDto = new ReviewsDto();
			boolean isSuccess = true;
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			
			if (v.ValidationComment(request.getParameter("comment"), Integer.parseInt(request.getParameter("star")))) {
				String comment = request.getParameter("comment");
				int star = Integer.parseInt(request.getParameter("star"));
				int userId = userDto.getId();

				reviewDto.setComment(comment);
				reviewDto.setStar(star);
				reviewDto.setItem_id(itemId);
				reviewDto.setUser_id(userId);

				ReviewsBusinessLogic rBL = new ReviewsBusinessLogic();
				isSuccess = rBL.executeInsert(reviewDto);

				if (isSuccess) {

					RequestDispatcher dispatch = request
							.getRequestDispatcher("ExecuteSelectReviewServlet?itemId=" + itemId);
					dispatch.forward(request, response);

				} else {
					response.sendRedirect("htmls/error.html");
				}
			} else {
				response.sendRedirect("ExecuteSelectReviewServlet?itemId=" + itemId);
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
