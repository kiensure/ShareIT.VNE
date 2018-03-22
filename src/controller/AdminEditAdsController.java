package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
public class AdminEditAdsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdvertisementsDao advertisementsDao;
	private LocalAdsDao localAdsDao;
	
    public AdminEditAdsController() {
        super();
        advertisementsDao = new AdvertisementsDao();
        localAdsDao = new LocalAdsDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		
		if(AuthUtil.checkAdminAndMod(request, response)!= null){
			int aid = 0;
			try {
				aid = Integer.parseInt(request.getParameter("aid"));
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/admin/ads");
				return;
			}
			
			Advertisements objAds = advertisementsDao.getItem(aid);
			
			if(objAds != null){
				ArrayList<LocationAds> listLocation = localAdsDao.getItems();
				request.setAttribute("listLocation", listLocation);
				request.setAttribute("objAds", objAds);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/editAds.jsp");
				rd.forward(request, response);
				return;
			}else{
				response.sendRedirect(request.getContextPath() + "/admin/ads");
				return;
			}
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/ads");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		
		int aid = 0;
		try {
			aid = Integer.parseInt(request.getParameter("aid"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/ads");
			return;
		}
		
		Advertisements objAds = advertisementsDao.getItem(aid);
		
		if(objAds != null){
			request.setAttribute("objAds", objAds);
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/ads");
			return;
		}
		
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

				String filename = StringUntil.getFileName(part);

				String picture = "";
				if (!"".equals(filename)) { // có chọn ảnh để sửa
					if (!"".equals(advertisementsDao.getItem(aid).getPicture())) {
						// xoá hình ảnh cũ
						String urlFileDel = path + File.separator + advertisementsDao.getItem(aid).getPicture();
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
					picture = advertisementsDao.getItem(aid).getPicture();
				}
		
				Advertisements itemAds = new Advertisements(aid, location, name, picture, link);
				
				if (advertisementsDao.editItem(itemAds) > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/ads?msg=2");
					return;
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/admin/editAds.jsp?err=0");
					rd.forward(request, response);
					return;
				}
		
	}

}
