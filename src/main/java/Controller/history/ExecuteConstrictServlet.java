package Controller.history;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.history.HistoryBusinessLogic;
import Model.history.db.HistoryDto;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class ExecuteConstrictServlet
 */
@WebServlet("/ExecuteConstrictServlet")
public class ExecuteConstrictServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteConstrictServlet() {
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
			
			LoginDto userDto = (LoginDto)session.getAttribute("loginData");
			String time = request.getParameter("time");
			String money = request.getParameter("money");
			
			HistoryBusinessLogic hBL = new HistoryBusinessLogic();
			List<HistoryDto> dto = hBL.executeSelectConstrict(time, money, userDto.getId());
			
			request.setAttribute("historyInfo", dto);
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/history.jsp");
			dispatch.forward(request, response);
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
