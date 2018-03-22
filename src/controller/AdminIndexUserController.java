package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthUtil;
import model.bean.User;
import model.dao.UserDao;


public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
       
    public AdminIndexUserController() {
        super();
        userDao = new UserDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		ArrayList<User> listUser = userDao.getItems();
		request.setAttribute("listUser", listUser);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/indexUser.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
