package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthUtil;
import model.bean.News;
import model.dao.NewsDao;

@MultipartConfig
public class AdminDelNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDao newsDao;

	public AdminDelNewsController() {
		super();
		newsDao = new NewsDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		
		if(AuthUtil.checkAdminAndMod(request, response) != null){
			// get dữ liệu
			int idNews = 0;
			try {
				idNews = Integer.parseInt(request.getParameter("nid"));
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/admin/news");
				return;
			}

			// đường dẫn lưu file
			final String path = request.getServletContext().getRealPath("/files");

			// lấy ảnh cũ
			News objOld = newsDao.getItem(idNews);

			// kiểm tra news(id) null
			if (objOld == null) {
				response.sendRedirect(request.getContextPath() + "/admin/news");
				return;
			}

			// đường dẫn ảnh
			String urlFileDel = path + File.separator + objOld.getPicture();

			File delFile = new File(urlFileDel);

			if (newsDao.delItem(idNews) > 0) {
				// tồn tại ảnh trong DB
				if (!"".equals(objOld.getPicture())) {
					// xoá ảnh cũ
					delFile.delete();
				}
				// if(delFile.isFile() && delFile.exists()){
				// delFile.delete();
				// }
				// in ra xoá news thành công
				response.sendRedirect(request.getContextPath() + "/admin/news?msg=3");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/news?err=2");
				return;
			}
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/news");
			return;
		}
	}

}
