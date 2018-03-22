package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.StringUntil;
import model.bean.News;
import model.dao.NewsDao;

public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NewsDao newsDao;   
    
    public PublicSearchController() {
        super();
        newsDao = new NewsDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String keyword = request.getParameter("search-text");
		
		if("".equals(keyword)){
			response.sendRedirect(request.getContextPath()+"/");
			return;
		}
		
		ArrayList<News> listResult = newsDao.searchItem(StringUntil.createSlug(keyword));
					
		request.setAttribute("listResult", listResult);
		
		RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
		rd.forward(request, response);
		return;
	}

}
