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

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private UserTypeDao userTypeDao;

	public AdminEditUserController() {
		super();
		userDao = new UserDao();
		userTypeDao = new UserTypeDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		
		ArrayList<UserType> listUserType = userTypeDao.getItems();
		request.setAttribute("listUserType", listUserType);
		
		
		User objUserLogin = (User)session.getAttribute("userLogin");
		
		String idText = request.getParameter("uid");
		int id = 0;

		try {
			id = Integer.parseInt(idText);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/user");
			return;
		}
		// kiểm tra admin và user
		if ("admin".equals(objUserLogin.getName()) || id == objUserLogin.getIdUser()) {
			// nếu là admin || id(url) = id(user_session)
			// lấy đối tượng cần sửa
			User item = userDao.getItem(id);
			if (item != null) {
				//
				request.setAttribute("user", item);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/editUser.jsp");
				rd.forward(request, response);
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/user");
				return;
			}
		} else {
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
		ArrayList<UserType> listUserType = userTypeDao.getItems();
		request.setAttribute("listUserType", listUserType);
		
		String idUser = request.getParameter("uid");
		int id = 0;

		try {
			id = Integer.parseInt(idUser);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/user");
			return;
		}
		
		User item = userDao.getItem(id);
		if(item == null){
			response.sendRedirect(request.getContextPath() + "/admin/user");
			return;
		}else{
			request.setAttribute("user", item);
		}
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String reemail = request.getParameter("reemail");
		
		if(!password.equals(repassword)){
			RequestDispatcher rd = request.getRequestDispatcher("/admin/editUser.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		if(!email.equals(reemail)){
			RequestDispatcher rd = request.getRequestDispatcher("/admin/editUser.jsp?err=2");
			rd.forward(request, response);
			return;
		}
		
		if ("".equals(password)) {
			password = userDao.getItem(id).getPassword();
		} else {
			password = StringLibrary.md5(password);
		}
		
		int idUserType = 0;
		try {
			idUserType = Integer.parseInt(request.getParameter("capbac"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/editUser.jsp?err=0");
			rd.forward(request, response);
			return;
		}
		
		User user = new User(id, "", password, fullname, email, idUserType, "", true);

		if (userDao.editItem(user) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=2");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/editUser.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
