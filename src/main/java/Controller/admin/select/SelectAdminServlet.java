package Controller.admin.select;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.items.ItemsBusinessLogic;
import Model.items.db.ItemsDto;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class SelectAdminServlet
 */
@WebServlet("/SelectAdminServlet")
public class SelectAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charaset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if (session.getAttribute("loginData") != null) {
			LoginDto dto = (LoginDto) session.getAttribute("loginData");
			if (dto.getId() == 1) {
				
				ItemsBusinessLogic iBL = new ItemsBusinessLogic();
				List<ItemsDto> dtoList = iBL.executeAllSelect();
				
				request.setAttribute("itemInfo", dtoList);
				RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/admin/itemSelect.jsp");
				dispatch.forward(request, response);
			} else {
				response.sendRedirect("HomeServlet");
			}
		} else {
			response.sendRedirect("LoginServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
