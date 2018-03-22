package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthUtil;
import model.bean.Category;
import model.dao.CategoryDao;


public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao categoryDao;
       
    public AdminAddCatController() {
        super();
        categoryDao = new CategoryDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		
		if(AuthUtil.checkAdminAndMod(request, response)!= null){
			ArrayList<Category> listCat = categoryDao.getItems();
			request.setAttribute("listCat", listCat);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addCat.jsp");
			rd.forward(request, response);
			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/cat");
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
		
		if(AuthUtil.checkAdminAndMod(request, response)!= null){
			ArrayList<Category> listCat = categoryDao.getItems();
			request.setAttribute("listCat", listCat);
			
			String name = request.getParameter("tendanhmuc");
			if(name.length() > 50){
				RequestDispatcher rd = request.getRequestDispatcher("/admin/addCat.jsp?err=0");
				rd.forward(request, response);
				return;
			}
			int idPa = 0;
			try {
				idPa = Integer.parseInt(request.getParameter("danhmuc"));
			} catch (NumberFormatException e) {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/addCat.jsp");
				rd.forward(request, response);
				return;
			}
			
			int displayIndex = categoryDao.getIndexMaxOfParent(idPa) + 1;
			
			Category objCat = new Category(0, name, idPa, displayIndex, false);
			
			if(categoryDao.addItem(objCat) > 0){
				response.sendRedirect(request.getContextPath() + "/admin/cat?msg=1");
				return;
			}else{
				RequestDispatcher rd = request.getRequestDispatcher("/admin/addCat.jsp?err=1");
				rd.forward(request, response);
				return;
			}
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/cat");
			return;
		}
		
	}

}
