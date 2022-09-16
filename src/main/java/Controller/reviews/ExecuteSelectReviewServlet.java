package Controller.reviews;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Config.Validation;
import Model.items.ItemsBusinessLogic;
import Model.items.db.ItemsDto;
import Model.reviews.ReviewsBusinessLogic;
import Model.reviews.db.ReviewsDto;

/**
 * Servlet implementation class AllReviewsView
 */
@WebServlet("/ExecuteSelectReviewServlet")
public class ExecuteSelectReviewServlet extends HttpServlet {
	private static final long serialVersionUitemId = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteSelectReviewServlet() {
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
		Validation v = new Validation();

		if (v.ValidationNumber(request.getParameter("itemId"))) {
			int itemId = Integer.parseInt(request.getParameter("itemId"));

			ReviewsBusinessLogic rBL = new ReviewsBusinessLogic();
			ItemsBusinessLogic iVBL = new ItemsBusinessLogic();

			List<ReviewsDto> reviewsDto = rBL.executeAllSelect(itemId);
			ItemsDto itemDto = iVBL.executeSelect(itemId);

			request.setAttribute("itemData", itemDto);
			request.setAttribute("allReviewsInfo", reviewsDto);
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/review.jsp");
			dispatch.forward(request, response);
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
