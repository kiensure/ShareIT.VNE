package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Advertisements;
import model.bean.Category;
import model.bean.News;
import model.dao.AdvertisementsDao;
import model.dao.CategoryDao;
import model.dao.NewsDao;

public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NewsDao newsDao;   
    private CategoryDao categoryDao;
    private AdvertisementsDao adsDao;
    
    public PublicIndexController() {
        super();
        newsDao = new NewsDao();
        categoryDao = new CategoryDao();
        adsDao = new AdvertisementsDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		ArrayList<News> listNewsSlide = newsDao.getItemsSlide();
		request.setAttribute("listNewsSlide", listNewsSlide);
		// lấy tin tức xem nhiều trong 3 ngày gần nhất
		ArrayList<News> listNewsHot =  newsDao.getItemsHot();
		request.setAttribute("listNewsHot", listNewsHot);
		// danh mục hiển thị trong trang chủ
		ArrayList<Category> listCatIndexPublic = categoryDao.getItemsIndex();
		request.setAttribute("listCatIndexPublic", listCatIndexPublic);
		
		ArrayList<Advertisements> listQC = adsDao.getItems();
		request.setAttribute("listQC", listQC);
		
		for (Category category : listCatIndexPublic) {
			ArrayList<News> listNewsByIDCat = newsDao.getItemsByIDCat(category.getIdCat());
			request.setAttribute("listNewsByIDCat"+category.getIdCat(), listNewsByIDCat);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		return;
	}

}
