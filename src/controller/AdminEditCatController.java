package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthUtil;
import library.SortCat;
import model.bean.Category;
import model.dao.CategoryDao;


public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao categoryDao;
       
    public AdminEditCatController() {
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
			int idCat = 0;
			try {
				idCat = Integer.parseInt(request.getParameter("cid"));
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/admin/cat");
				return;
			}
			
			Category objCat = categoryDao.getItem(idCat);
			if(objCat != null){
				
				ArrayList<Category> listCat = categoryDao.getItemsEdit(idCat);
				SortCat.sort(listCat);
				request.setAttribute("listCat", listCat);
				
				ArrayList<Category> listDisplayIndex = categoryDao.getItemsByIdPaAndSortByDisplayIndex(objCat.getIdParent());
				request.setAttribute("listDisplayIndex", listDisplayIndex);
				request.setAttribute("objCat", objCat);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/editCat.jsp");
				rd.forward(request, response);
				return;
			}else{
				response.sendRedirect(request.getContextPath() + "/admin/cat");
				return;
			}
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
		
		ArrayList<Category> listCat = categoryDao.getParentItems();
		request.setAttribute("listCat", listCat);
		
		int idCat = 0;
		try {
			idCat = Integer.parseInt(request.getParameter("cid"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/cat");
			return;
		}
		
		Category objCat = categoryDao.getItem(idCat);
		
		if(objCat == null){
			response.sendRedirect(request.getContextPath() + "/admin/cat");
			return;
		}
		
		String name = request.getParameter("tendanhmuc");
		if(name.length() > 50){
			RequestDispatcher rd = request.getRequestDispatcher("/admin/editCat.jsp?err=0");
			rd.forward(request, response);
			return;
		}
		
		int idParent = 0;
		try {
			idParent = Integer.parseInt(request.getParameter("danhmuc"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/cat");
			return;
		}
		
		int displayIndex = 0;
		try {
			displayIndex = Integer.parseInt(request.getParameter("indexDisplay"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/cat");
			return;
		}
		
		Category objCatChange = new Category(idCat, name, idParent, displayIndex, objCat.isActive());
		Category objCatBeingChanged = categoryDao.getItemByDisplayIndexAndParent(displayIndex, objCat.getIdParent());
		objCatBeingChanged.setDisplayIndex(objCat.getDisplayIndex());
		
		if(categoryDao.editItem(objCatChange) > 0){
			if(categoryDao.editItemDisplayIndex(objCatBeingChanged) > 0){
				response.sendRedirect(request.getContextPath() + "/admin/cat?msg=2");
				return;
			}else{
				response.sendRedirect(request.getContextPath() + "/admin/cat");
				return;
			}
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/admin/editCat.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
