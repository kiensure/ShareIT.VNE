package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.StringLibrary;
import model.bean.User;
import model.dao.UserDao;

public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao userDao;   
    
    public AdminLoginController() {
        super();
        userDao = new UserDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		User userLogin = (User)session.getAttribute("userLogin");
		if(userLogin != null){
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/login.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session =  request.getSession();
		User userLogin = (User)session.getAttribute("userLogin");
		if(userLogin != null){
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}
		
		String username = request.getParameter("username");
		String password = StringLibrary.md5(request.getParameter("password"));
		User user = userDao.checkLogin(username,password);
		
		if(user != null){
			// tk & mk chính xác
			session.setAttribute("userLogin",user);
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/login?msg=1");
			return;
		}
		
	}

}
