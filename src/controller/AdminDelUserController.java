package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.AuthUtil;
import model.bean.User;
import model.dao.UserDao;

public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public AdminDelUserController() {
        super();
        userDao = new UserDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		int idUser = 0;
		try {
			idUser = Integer.parseInt(request.getParameter("uid"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/user");
			return;
		}
		User objUser = userDao.getItem(idUser);
		if(objUser == null){
			response.sendRedirect(request.getContextPath() + "/admin/user");
			return;
		}
		HttpSession session = request.getSession();
		User userinfo = (User)session.getAttribute("userLogin");
		if("admin".equals(objUser.getName())){
			// ko cho xoá
			response.sendRedirect(request.getContextPath() + "/admin/user?err=0");
		} else {
			if(userinfo.getName().equals("admin")){
				if(userDao.delItem(idUser) > 0){
					response.sendRedirect(request.getContextPath() + "/admin/user?msg=3");
					return;
				} else {
					response.sendRedirect(request.getContextPath() + "/admin/user?err=1");
					return;
				}
			}else{
				response.sendRedirect(request.getContextPath() + "/admin/user?err=2");
				return;
			}
		}
		
	}

}
