package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import library.AuthUtil;
import library.StringUntil;
import model.bean.Category;
import model.bean.News;
import model.bean.User;
import model.dao.CategoryDao;
import model.dao.NewsDao;
import model.dao.UserDao;

@MultipartConfig
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDao newsDao;
	private CategoryDao categoryDao;
	private UserDao userDao;

	public AdminEditNewsController() {
		super();
		newsDao = new NewsDao();
		categoryDao = new CategoryDao();
		userDao = new UserDao();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		ArrayList<Category> listCat = categoryDao.getParentItems();
		request.setAttribute("listCat", listCat);
		
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		
		String idText = request.getParameter("nid");
		int id = 0;
		try {
			id = Integer.parseInt(idText);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/news");
			return;
		}

		News item = newsDao.getItem(id);
		if (item != null) {
			User userCreateItem = userDao.getItem(item.getIdUser());
			if(userLogin.getIdUser() == userCreateItem.getIdUser() || userLogin.getIdTypeUser() == 1 || userLogin.getIdTypeUser() == 2){
				request.setAttribute("news", item);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/editNews.jsp");
				rd.forward(request, response);
				return;
			}else{
				response.sendRedirect(request.getContextPath() + "/admin/news");
				return;
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/news");
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		// get dữ liệu

		int idNews = Integer.parseInt(request.getParameter("nid"));
		String preview = request.getParameter("mota");
		String detail = request.getParameter("chitiet");
		String name = request.getParameter("tentin");

		ArrayList<Category> listCat = categoryDao.getParentItems();
		request.setAttribute("listCat", listCat);

		News item = newsDao.getItem(idNews);
		
		if (item != null) {
			request.setAttribute("news", item);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/news");
			return;
		}
		Timestamp dateCreat = item.getDate_Create();
		if (name.length() > 255) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/editNews.jsp?err=0");
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

		// đường dẫn lưu file
		final String path = request.getServletContext().getRealPath("/files");
		File dirPath = new File(path);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}

		Part part = request.getPart("picture");

		// lấy tên file

		String filename = StringUntil.getFileName(part);

		String picture = "";
		if (!"".equals(filename)) { // có chọn ảnh để sửa
			if (!"".equals(newsDao.getItem(idNews).getPicture())) {
				// xoá hình ảnh cũ
				String urlFileDel = path + File.separator + newsDao.getItem(idNews).getPicture();
				File delFile = new File(urlFileDel);
				delFile.delete();
			}
			OutputStream out = null;
			InputStream filecontent = null;
			// đổi tên file
			picture = StringUntil.renameFile(filename);
			try {
				out = new FileOutputStream(new File(path + File.separator + picture));
				filecontent = part.getInputStream();

				int read = 0;
				final byte[] bytes = new byte[1024];

				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				System.out.println("UPLOAD FILE THÀNH CÔNG");
				System.out.println("ĐƯỜNG DẪN THẬT SỰ CỦA DỰ ÁN: " + dirPath);
			} catch (FileNotFoundException fne) {
				fne.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
				if (filecontent != null) {
					filecontent.close();
				}
			}
		} else {
			picture = newsDao.getItem(idNews).getPicture();
		}

		News news = new News(idNews, name, preview, detail, dateCreat, false, 1, picture, catId, "", false, item.getViews(),true);
		if (newsDao.editItem(news) > 0) {
			// in ra sửa news thành công
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=2");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/editNews.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
