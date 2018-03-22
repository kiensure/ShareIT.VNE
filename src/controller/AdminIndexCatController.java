package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDao categoryDao;
	
    public AdminIndexCatController() {
        super();
        categoryDao = new CategoryDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		ArrayList<Category> listCat = categoryDao.getItems();
		request.setAttribute("listCat", SortCat.sort(listCat));
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/indexCat.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("aid") != null && request.getParameter("astatus") != null){
			int id = Integer.parseInt(request.getParameter("aid"));
			boolean status = Boolean.valueOf(request.getParameter("astatus"));
			if(status == false){
				status = true;
				Category objCat = new Category(id, "", 0, 0, status);
				if(categoryDao.editItemChangeStatus(objCat,status) > 0){
					out.print("<a style='margin-left: 15px' href='javascript:void(0)' title='' onclick='return active("+ id + ","+ status +" )'><i style='font-size: 2em; color: green;' class='fa fa-eye fa-2'></i></a>");
				}
			} else{
				status = false;
				Category objCat = new Category(id, "", 0, 0, status);
				if(categoryDao.editItemChangeStatus(objCat,status) > 0){
					out.print("<a style='margin-left: 15px' href='javascript:void(0)' title='' onclick='return active("+ id + ","+ status +" )'><i style='font-size: 2em; color: red;' class='fa fa-eye-slash'></i></a>");
				}
			}
		}
	}

}
