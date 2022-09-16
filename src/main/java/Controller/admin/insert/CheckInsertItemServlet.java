package Controller.admin.insert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Config.Validation;
import Model.users.db.LoginDto;

/**
 * Servlet implementation class CheckInsertItemServlet
 */
@WebServlet("/CheckInsertItemServlet")
@MultipartConfig
public class CheckInsertItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckInsertItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charaset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if (session.getAttribute("loginData") != null) {
			LoginDto dto = (LoginDto) session.getAttribute("loginData");
			if (dto.getId() == 1) {

				Validation v = new Validation();
				String name = request.getParameter("name");
				String price = request.getParameter("price");
				String stock = request.getParameter("stock");
				
				//				画像関係
				String filename = "";
				try {
					Part part = request.getPart("image");
					filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					String path = getServletContext().getRealPath("/img");
					part.write(path + File.separator + filename);
				
				} catch (IOException e) {
					e.printStackTrace();
					String error = "error";
					request.setAttribute("error", error);
					RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/admin/itemInsert.jsp");
					dispatch.forward(request, response);
				
				}
					String setumei = request.getParameter("setumei");
					String syousai = request.getParameter("syousai");

					HashMap<String, String> itemData = new HashMap<>();

					itemData.put("name", name);
					itemData.put("price", price);
					itemData.put("stock", stock);
					itemData.put("filename", filename);
					itemData.put("setumei", setumei);
					itemData.put("syousai", syousai);
					session.setAttribute("itemData", itemData);

			
				if (v.ValidationNumber(price) && v.ValidationNumber(stock) && v.ValidationPicture(filename.substring(filename.lastIndexOf(".")))
						&& name != null && setumei != null && syousai != null && name != "" && setumei != null
						&& syousai != null) {

						RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/admin/checkItem.jsp");
						dispatch.forward(request, response);
						
				} else {
					String error = "error";
					request.setAttribute("error", error);
					RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/admin/itemInsert.jsp");
					dispatch.forward(request, response);

				}
			} else {
				response.sendRedirect("HomeServlet");
			}
		} else {
			response.sendRedirect("LoginServlet");
		}
	}

}
