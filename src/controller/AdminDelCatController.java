package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthUtil;
import model.dao.CategoryDao;

public class AdminDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryDao categoryDao;
	
    public AdminDelCatController() {
        super();
        categoryDao = new CategoryDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		
		if(AuthUtil.checkAdminAndMod(request, response)!= null){
			int idCat = 0;
			try {
				idCat = Integer.parseInt(request.getParameter("cid"));
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/admin/cat");
				return;
			}
			
			if(categoryDao.getItem(idCat) == null){
				response.sendRedirect(request.getContextPath() + "/admin/cat");
				return;
			}else{
				if(categoryDao.delItem(idCat) > 0){
					response.sendRedirect(request.getContextPath() + "/admin/cat?msg=3");
					return;
				}else{
					response.sendRedirect(request.getContextPath() + "/admin/cat?err=1");
					return;
				}
			}
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/cat");
			return;
		}
	}

}
