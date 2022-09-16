package Controller.items;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Config.Validation;
import Model.favorite.FavoriteBusinessLogic;
import Model.favorite.db.FavoriteDto;
import Model.items.ItemsBusinessLogic;
import Model.items.db.ItemsDto;
import Model.reviews.ReviewsBusinessLogic;
import Model.reviews.db.ReviewsDto;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class ItemsView
 */
@WebServlet("/ExecuteSelectItemServlet")
public class ExecuteSelectItemServlet extends HttpServlet {
	private static final long serialVersionUitemId = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteSelectItemServlet() {
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
		int count = 0;
		if (v.ValidationNumber(request.getParameter("itemId"))) {
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			ItemsBusinessLogic iVBL = new ItemsBusinessLogic();
			ReviewsBusinessLogic rBL = new ReviewsBusinessLogic();
			FavoriteBusinessLogic fBL = new FavoriteBusinessLogic();
			HttpSession session = request.getSession();
			
			if (session.getAttribute("loginData") != null) {
				LoginDto dto = (LoginDto)session.getAttribute("loginData");
				List<FavoriteDto> favoriteDtoList = fBL.executeSelect(dto.getId());
				
				for (int i=0; i<favoriteDtoList.size(); i++) {
					FavoriteDto d = favoriteDtoList.get(i);
					if (d.getItem_id() == itemId ) {
						request.setAttribute("already", count);
					}
				}
			}

			ItemsDto dto = iVBL.executeSelect(itemId);
			List<ReviewsDto> reviewDto = rBL.executeSelect(itemId);
			List<ReviewsDto> reviewAllDto = rBL.executeAllSelect(itemId);
					

			//		System.out.println(dto.getName());
			request.setAttribute("itemData", dto);
			request.setAttribute("reviewsInfo", reviewDto);
			request.setAttribute("allReviewsInfo", reviewAllDto);
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/productDetails.jsp");
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
