package Controller.admin.delete;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.items.ItemsBusinessLogic;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class CheckDeleteItemServlet
 */
@WebServlet("/CheckDeleteItemServlet")
public class CheckDeleteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckDeleteItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charaset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if (session.getAttribute("loginData") != null) {
			LoginDto dto = (LoginDto) session.getAttribute("loginData");
			if (dto.getId() == 1) {
				int itemId = Integer.parseInt(request.getParameter("itemId"));
				ItemsBusinessLogic iBL = new ItemsBusinessLogic();
				boolean isDelete = iBL.executeDelete(itemId);
				
				if (isDelete) {
					RequestDispatcher dispatch = request.getRequestDispatcher("DeleteAdminServlet");
					dispatch.forward(request, response);
				} else {
					response.sendRedirect("htmls/error.html");
				}

			} else {
				response.sendRedirect("HomeServlet");
			}
		} else {
			response.sendRedirect("LoginServlet");
		}		

	}
}