package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.bean.News;
import model.dao.CommentDao;
import model.dao.NewsDao;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NewsDao newsDao;  
    private CommentDao cmtDao;
    
    public PublicDetailController() {
        super();
        newsDao = new NewsDao();
        cmtDao = new CommentDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nid = 0;
		try {
			nid = Integer.parseInt(request.getParameter("nid"));
		} catch (NumberFormatException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
			rd.forward(request, response);
			return;
		}
		
		int countViews = newsDao.countViews(nid);
		News objnews = newsDao.getItem(nid);
		if(objnews != null){
			request.setAttribute("objnews", objnews);
			// lấy tin liên quan
			ArrayList<News> listNewsLQ = newsDao.getTinLienQuan(objnews.getIdCat(),nid);
			// lấy cmt cha trong news id = nid
			ArrayList<Comment> listCmtParentByIdNews = cmtDao.getItemsParentByIdNews(nid);
			// lấy tất cả cmt dc active
			ArrayList<Comment> listCmt = cmtDao.getItemsByIdNews(nid);
			int countCmt = cmtDao.countCmtByNews(nid);
			request.setAttribute("listNewsLQ", listNewsLQ);
			request.setAttribute("countCmt", countCmt);
			request.setAttribute("listCmtParent", listCmtParentByIdNews);
			request.setAttribute("listCmt", listCmt);
			countViews++;
			if(newsDao.updateViews(countViews ,objnews) > 0){
				RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
				rd.forward(request, response);
			}
		}else{
			response.sendRedirect(request.getContextPath() + "");
			return;
		}
	}

}
