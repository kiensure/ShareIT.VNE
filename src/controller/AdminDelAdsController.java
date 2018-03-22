package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthUtil;
import model.bean.Advertisements;
import model.dao.AdvertisementsDao;

public class AdminDelAdsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AdvertisementsDao advertisementsDao;
    
    public AdminDelAdsController() {
        super();
        advertisementsDao = new AdvertisementsDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			System.out.println(aid);
			// đường dẫn lưu file
			final String path = request.getServletContext().getRealPath("/files");

			// lấy ảnh cũ
			Advertisements objOld = advertisementsDao.getItem(aid);

			if (objOld == null) {
				response.sendRedirect(request.getContextPath() + "/admin/ads");
				return;
			}

			// đường dẫn ảnh
			String urlFileDel = path + File.separator + objOld.getPicture();

			File delFile = new File(urlFileDel);

			if (advertisementsDao.delItem(aid) > 0) {
				// tồn tại ảnh trong DB
				if (!"".equals(objOld.getPicture())) {
					// xoá ảnh cũ
					delFile.delete();
				}
				// if(delFile.isFile() && delFile.exists()){
				// delFile.delete();
				// }
				// in ra xoá news thành công
				response.sendRedirect(request.getContextPath() + "/admin/ads?msg=3");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/ads?err=1");
				return;
			}
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/ads");
			return;
		}
	}

}
