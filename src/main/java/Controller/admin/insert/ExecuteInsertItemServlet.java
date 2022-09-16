package Controller.admin.insert;

import java.io.IOException;
import java.util.HashMap;

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
 * Servlet implementation class ExecuteInsertItemServlet
 */
@WebServlet("/ExecuteInsertItemServlet")
public class ExecuteInsertItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteInsertItemServlet() {
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
				HashMap<String, String> itemData = (HashMap<String, String>)session.getAttribute("itemData");
				ItemsDto itemDto = new ItemsDto();
				
				itemDto.setName(itemData.get("name"));
				itemDto.setPrice(Integer.parseInt(itemData.get("price")));
				itemDto.setRetention_stock(Integer.parseInt(itemData.get("stock")));
				itemDto.setStock(Integer.parseInt(itemData.get("stock")));
				itemDto.setSetumei(itemData.get("setumei"));
				itemDto.setSyousai(itemData.get("syousai"));
				itemDto.setPicture(itemData.get("filename"));
				
				ItemsBusinessLogic iBL = new ItemsBusinessLogic();
				boolean isSuccess = iBL.executeInsert(itemDto);
				
				if (isSuccess) {
					RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/admin/top.jsp");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
