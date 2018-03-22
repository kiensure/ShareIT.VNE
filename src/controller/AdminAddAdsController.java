package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import library.AuthUtil;
import library.StringUntil;
import model.bean.Advertisements;
import model.bean.LocationAds;
import model.dao.AdvertisementsDao;
import model.dao.LocalAdsDao;

@MultipartConfig
public class AdminAddAdsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdvertisementsDao advertisementsDao;
	private LocalAdsDao localAdsDao;

	public AdminAddAdsController() {
		super();
		advertisementsDao = new AdvertisementsDao();
		localAdsDao = new LocalAdsDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}
		if(AuthUtil.checkAdminAndMod(request, response)!= null){
			ArrayList<LocationAds> listLocation = localAdsDao.getItems();
			request.setAttribute("listLocation", listLocation);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/addAds.jsp");
			rd.forward(request, response);
			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/ads");
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		if (!AuthUtil.checkLogin(request, response)) {
			return;
		}

		if(AuthUtil.checkAdminAndMod(request, response)!= null){
			String name = request.getParameter("tenads");
			String link = request.getParameter("link");
			
			int location = 0;
			location = Integer.parseInt(request.getParameter("locationads"));
			
			// đường dẫn lưu file
			final String path = request.getServletContext().getRealPath("/files");
			File dirPath = new File(path);
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
			
			Part part = request.getPart("picture");

			// lấy tên file

			// đổi tên file
			String filename = "";
			if (!"".equals(StringUntil.getFileName(part))) {
				filename = StringUntil.renameFile(StringUntil.getFileName(part));
			}
			
			Advertisements objAds = new Advertisements(0, location, name, filename, link);
			
			if (advertisementsDao.addItem(objAds) > 0) {
				// ghi file
				if(!"".equals(filename)){
					String filePath = path + File.separator + filename;
					part.write(filePath);
				}
				response.sendRedirect(request.getContextPath() + "/admin/ads?msg=1");
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/admin/addAds.jsp?err=1");
				rd.forward(request, response);
				return;
			}
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/ads");
			return;
		}
	}
}
