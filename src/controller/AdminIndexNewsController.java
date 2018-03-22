package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constant.Define;
import library.AuthUtil;
import model.bean.News;
import model.dao.NewsDao;

public class AdminIndexNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NewsDao newsDao;
	
    public AdminIndexNewsController() {
        super();
        newsDao = new NewsDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		int sumNews = newsDao.countNews();
		int sumPage = (int) Math.ceil((float) sumNews / Define.ROW_COUNT_ADMIN);
		int current_page = 1;
		if (request.getParameter("page") != null) {
			current_page = Integer.parseInt(request.getParameter("page"));
		}
		// tính offset
		int offset = (current_page - 1) * Define.ROW_COUNT_ADMIN;
		request.setAttribute("sumPage", sumPage);
		request.setAttribute("current_page", current_page);
		ArrayList<News> list = newsDao.getItemsPagination(offset);
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/indexNews.jsp");
		rd.forward(request, response);
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
				News news = new News(id, "", "", "", null, newsDao.getItem(id).isHotNew(), 0, "", 0, "", true, 0, status);
				if(newsDao.editItemByStatus(news,status) > 0){
					out.print("<a style='margin-left: 15px' href='javascript:void(0)' title='' onclick='return active("+ id + ","+ status +" )'><i style='font-size: 2em; color: green;' class='fa fa-eye fa-2'></i></a>");
				}
			} else{
				status = false;
				News news = new News(id, "", "", "", null, newsDao.getItem(id).isHotNew(), 0, "", 0, "", true, 0, status);
				if(newsDao.editItemByStatus(news,status) > 0){
					out.print("<a style='margin-left: 15px' href='javascript:void(0)' title='' onclick='return active("+ id + ","+ status +" )'><i style='font-size: 2em; color: red;' class='fa fa-eye-slash'></i></a>");
				}
			}
		}
		
		if(request.getParameter("aidhotnews") != null && request.getParameter("aishotnews") != null){
			int idhotnews = Integer.parseInt(request.getParameter("aidhotnews"));
			boolean ishotnews = Boolean.valueOf(request.getParameter("aishotnews"));
			if(ishotnews == false){
				ishotnews = true;
				News news = new News(idhotnews, "", "", "", null, ishotnews, 0, "", 0, "", true, 0, newsDao.getItem(idhotnews).isActive());
				if(newsDao.editItemActiveHotNews(news,ishotnews) > 0){
					out.print("<a style='margin-left: 15px' href='javascript:void(0)' title='' onclick='return activeHotNews("+ idhotnews + ","+ ishotnews +" )'><i style='font-size: 2em; color: green;' class='fa fa-check'></i></a>");
				}
			} else{
				ishotnews = false;
				News news = new News(idhotnews, "", "", "", null, ishotnews, 0, "", 0, "", true, 0, newsDao.getItem(idhotnews).isActive());
				if(newsDao.editItemActiveHotNews(news,ishotnews) > 0){
					out.print("<a style='margin-left: 15px' href='javascript:void(0)' title='' onclick='return activeHotNews("+ idhotnews + ","+ ishotnews +" )'><i style='font-size: 2em; color: red;' class='fa fa-times'></i></a>");
				}
			}
		}
		
		if(request.getParameter("aidsilde") != null && request.getParameter("aisSlide") != null){
			int aidsilde = Integer.parseInt(request.getParameter("aidsilde"));
			boolean aisSlide = Boolean.valueOf(request.getParameter("aisSlide"));
			if(aisSlide == false){
				aisSlide = true;
				News news = new News(aidsilde, "", "", "", null, false, 0, "", 0, "", aisSlide, 0, true);
				if(newsDao.editItemActiveSlide(news,aisSlide) > 0){
					out.print("<a onclick='return activeSlide("+aidsilde+", "+aisSlide+")' style='margin-left: 15px' href='javascript:void(0)'><input id='checkSlide' checked = 'checked' type='checkbox' /></a>");
				}
			} else{
				aisSlide = false;
				News news = new News(aidsilde, "", "", "", null, false, 0, "", 0, "", aisSlide, 0, true);
				if(newsDao.editItemActiveSlide(news,aisSlide) > 0){
					out.print("<a onclick='return activeSlide("+aidsilde+", "+aisSlide+")' style='margin-left: 15px' href='javascript:void(0)'><input id='checkSlide' type='checkbox' /></a>");
				}
			}
		}
	}

}
