package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.AuthUtil;
import library.StringLibrary;
import model.bean.User;
import model.bean.UserType;
import model.dao.UserDao;
import model.dao.UserTypeDao;

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private UserTypeDao userTypeDao;
	
	public AdminAddUserController() {
		super();
		userDao = new UserDao();
		userTypeDao = new UserTypeDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		
		HttpSession session = request.getSession();
		User userLogin = (User)session.getAttribute("userLogin");
		if(userLogin.getIdTypeUser() == 1){
			ArrayList<UserType> listUserTypes = userTypeDao.getItems();
			request.setAttribute("listUserType", listUserTypes);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addUser.jsp");
			rd.forward(request, response);
			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/user");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		
		ArrayList<UserType> listUserTypes = userTypeDao.getItems();
		request.setAttribute("listUserType", listUserTypes);
		
		String username = request.getParameter("username");
		if(username.length() > 100){
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addUser.jsp?err=0");
			rd.forward(request, response);
			return;
		}
		
		String password = StringLibrary.md5(request.getParameter("password"));
		String repassword = StringLibrary.md5(request.getParameter("repassword"));
		if(!repassword.equals(password)){
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addUser.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		
		String email = request.getParameter("email");
		String reemail = request.getParameter("reemail");
		if(!email.equals(reemail)){
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addUser.jsp?err=2");
			rd.forward(request, response);
			return;
		}
		
		String fullname = request.getParameter("fullname");
		if(fullname.length() > 100){
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addUser.jsp?err=3");
			rd.forward(request, response);
			return;
		}
		
		int idUserType = 0;
		try {
			idUserType = Integer.parseInt(request.getParameter("capbac"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addUser.jsp?err=4");
			rd.forward(request, response);
			return;
		}
		
		User item = new User(0, username, password, fullname, email, idUserType, "", true);
		// kiểm tra tồn tại user
		if (userDao.checkUser(username) != null) {
			// đã tồn tại
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addUser.jsp?err=5");
			rd.forward(request, response);
			return;
		} else {
			// chưa tồn tại
			if (userDao.addItem(item) > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/user?msg=1");
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/addUser.jsp?err=6");
				rd.forward(request, response);
				return;
			}
		}
		
	}

}
