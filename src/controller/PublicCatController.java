package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.DayFormat;
import library.StringUntil;
import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDao;
import model.dao.NewsDao;

public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static NewsDao newsDao;  
    private static CategoryDao categoryDao;
    
    public PublicCatController() {
        super();
        newsDao = new NewsDao();
        categoryDao = new CategoryDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid = 0;
		try {
			cid = Integer.parseInt(request.getParameter("cid"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}
		// lấy tin tức xem nhiều nhất
		ArrayList<News> listMostViews = newsDao.getItemsMostViews(cid);
		request.setAttribute("listMostViews", listMostViews);
		// lấy tin tức nổi bật
		ArrayList<News> listHotNews = newsDao.getItemsHot(cid);
		request.setAttribute("listHotNews", listHotNews);
		// lấy tất cả các tin
		ArrayList<News> listAllNews = newsDao.getAllItemsByIdCat(cid);
		request.setAttribute("listAllNews", listAllNews);
		request.setAttribute("objCat", categoryDao.getItem(cid));
		ArrayList<Category> listCat = categoryDao.getItems();
		request.setAttribute("listCat", listCat);
		// tất cả tin trong danh mục
		ArrayList<News> listNews = newsDao.getItemsByIdCat(cid);
		request.setAttribute("listNews", listNews);
		
		RequestDispatcher rd = request.getRequestDispatcher("/cat.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int page = Integer.parseInt(request.getParameter("page"));
		if(page < 1){
			page = 1;
		}
		
		int cid = 0;
		cid = Integer.parseInt(request.getParameter("cid"));
		
		int limit = 5;
		
		int start = (limit * page) - limit;
		if(page == 1){
			start = 0;
		}
		// lấy tin mỗi lần cuộn
		ArrayList<News> listNews = newsDao.getItemsScroll(cid, start, limit);
		
		int total = listNews.size();
		if(total > limit){
			for (int i = 0; i < total - 1; i++) {
				out.print("<li class='clearfix'>"+
							"<div class='item clearfix'>"+
								"<a href='"+request.getContextPath()+"/tin-tuc/"+StringUntil.createSlugURL(listNews.get(i).getName()) +"-"+listNews.get(i).getIdNews()+".html'>"); 
								if(!"".equals(listNews.get(i).getPicture())){
									out.print("<img style='height: 250px' src='"+request.getContextPath()+"/files/"+listNews.get(i).getPicture()+"' alt='' />");
								}else{
									out.print("<img style='height: 250px' src='"+request.getContextPath()+"/templates/public/images/no-img.png' alt='' />");
								}
				out.print("<div class='mask'>"+
							"<span class='icon-link'></span>"+
							"</div>"+
								"</a>"+
								"<div class='item-content'>"+
									"<header class='header-item'>"+
										"<span class='icon-image pull-left'></span>"+
										"<div class='item-right'>"+
											"<h4>"+
												"<a href='href='"+request.getContextPath()+"/tin-tuc/"+StringUntil.createSlugURL(listNews.get(i).getName()) +"-"+listNews.get(i).getIdNews()+".html'>"+listNews.get(i).getName()+"</a>"+
											"</h4>"+
										"</div>"+
										"<div class='clearfix'></div>"+
										"<ul class='list-inline kp-metadata clearfix'>"+
											"<li class='kp-view'><span"+
												"class='icon-calendar pull-left'></span>"+DayFormat.fD(listNews.get(i).getDate_Create())+"</li>"+
										"</ul>"+
									"</header>"+
									"<p>"+listNews.get(i).getPreview()+"</p>"+
								"</div>"+
							"</div> <!-- item -->"+
						"</li>");
									
			}
		}else{
			for (int i = 0; i < total; i++) {
				out.print("<li class='clearfix'>"+
						"<div class='item clearfix'>"+
							"<a href='"+request.getContextPath()+"/tin-tuc/"+StringUntil.createSlugURL(listNews.get(i).getName()) +"-"+listNews.get(i).getIdNews()+".html'>"); 
							
								if(!"".equals(listNews.get(i).getPicture())){
									out.print("<img style='height: 250px' src='"+request.getContextPath()+"/files/"+listNews.get(i).getPicture()+"' alt='' />");
								}else{
									out.print("<img style='height: 250px' src='"+request.getContextPath()+"/templates/public/images/no-img.png' alt='' />");
								}
							out.print("<div class='mask'>"+
									"<span class='icon-link'></span>"+
								"</div>"+
							"</a>"+
							"<div class='item-content'>"+
								"<header class='header-item'>"+
									"<span class='icon-image pull-left'></span>"+
									"<div class='item-right'>"+
										"<h4>"+
											"<a href='"+request.getContextPath()+"/tin-tuc/"+StringUntil.createSlugURL(listNews.get(i).getName()) +"-"+listNews.get(i).getIdNews()+".html'>"+listNews.get(i).getName()+"</a>"+
										"</h4>"+
									"</div>"+
									"<div class='clearfix'></div>"+
									"<ul class='list-inline kp-metadata clearfix'>"+
										"<li class='kp-view'><span"+
											"class='icon-calendar pull-left'></span>"+DayFormat.fD(listNews.get(i).getDate_Create())+"</li>"+
									"</ul>"+
								"</header>"+
								"<p>"+listNews.get(i).getPreview()+"</p>"+
							"</div>"+
						"</div> <!-- item -->"+
					"</li>");
			}
		}
		if(total <= limit){
			out.print("<script language='javascript'>stopped = true; </script>");
		}
	}
	
	public static ArrayList<News> getAllNewsByCat(ArrayList<Category> listCat, int idPa, ArrayList<News> listAll){
		ArrayList<Category> listCatChild = new ArrayList<>();
		NewsDao newsDao = new NewsDao();
		for (Category category : listCat) {
			if(category.getIdParent() == idPa){
				listCatChild.add(category);
			}
		}
		if(listCatChild.size() > 0){
			for (Category category : listCatChild) {
				ArrayList<News> listNewsChild = newsDao.getItemsByIDCat(category.getIdCat());
				listAll.addAll(listNewsChild);
			}
			for (Category category : listCatChild) {
				getAllNewsByCat(listCat, category.getIdCat(), listAll);	
			}
		}
		return listAll;
	}
	
}
