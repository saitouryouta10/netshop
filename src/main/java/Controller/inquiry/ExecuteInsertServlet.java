package Controller.inquiry;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.inquiry.InquiryBusinessLogic;
import Model.inquiry.db.InquiryDto;

/**
 * Servlet implementation class ExecuteInsertServlet
 */
@WebServlet("/ExecuteInsertServlet")
public class ExecuteInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charaset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("inquiryData") != null) {
			
			HashMap<String, String> inquiryData = (HashMap<String, String>)session.getAttribute("inquiryData");
			InquiryDto dto = new InquiryDto();
			InquiryBusinessLogic iBL = new InquiryBusinessLogic();
			
			dto.setName(inquiryData.get("name"));
			dto.setEmail(inquiryData.get("email"));
			dto.setKenmei(inquiryData.get("title"));
			dto.setInquery_post(inquiryData.get("inquiry"));
			
			boolean isSuccess = iBL.executeInsert(dto);
			
			if (isSuccess) {
				session.invalidate();
				response.sendRedirect("htmls/inquirySuccess.html");
			} else {
				response.sendRedirect("htmls/error.html");
			}
			
			
			
			

		} else {
			String error = "error";
			request.setAttribute("error", error);
			RequestDispatcher dispatch = request.getRequestDispatcher("SendInquiryServlet");
			dispatch.forward(request, response);
		}
	}

}
