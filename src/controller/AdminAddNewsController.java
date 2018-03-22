package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import library.AuthUtil;
import library.StringUntil;
import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDao;
import model.dao.NewsDao;

@MultipartConfig
public class AdminAddNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDao newsDao;
	private CategoryDao categoryDao;

	public AdminAddNewsController() {
		super();
		newsDao = new NewsDao();
		categoryDao = new CategoryDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		
		ArrayList<Category> listCat = categoryDao.getItems();
		request.setAttribute("listCat", listCat);

		RequestDispatcher rd = request.getRequestDispatcher("/admin/addNews.jsp");
		rd.forward(request, response);
		return;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		
		ArrayList<Category> listCat = categoryDao.getItems();
		request.setAttribute("listCat", listCat);
		
		// đường dẫn lưu file
		final String path = request.getServletContext().getRealPath("/files");
		File dirPath = new File(path);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}

		// get dữ liệu
		String name = request.getParameter("tentin");
		if(name.length()>255){
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addNews.jsp?err=0");
			rd.forward(request, response);
			return;
		}
		int catId = 0;
		try {
			catId = Integer.parseInt(request.getParameter("danhmuc"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/news");
			return;
		}
		String preview = request.getParameter("mota");
		String detail = request.getParameter("chitiet");

		Part part = request.getPart("picture");

		// lấy tên file

		// đổi tên file
		String filename = "";
		if (!"".equals(StringUntil.getFileName(part))) {
			filename = StringUntil.renameFile(StringUntil.getFileName(part));
		}
		Timestamp dateCreat = new Timestamp(new Date().getTime());

		boolean slide = false;
		
		if(request.getParameter("slide") != null){
			slide = true;
		}
		
		News news = new News(0, name, preview, detail, dateCreat, false, 1, filename, catId, null ,slide, 0,true);
		if (newsDao.addItem(news) > 0) {
			// ghi file
			if(!"".equals(filename)){
				String filePath = path + File.separator + filename;
				part.write(filePath);
			}
			// in ra thêm news thành công
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=1");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addNews.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		
	}

}
