package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthUtil;
import model.bean.Advertisements;
import model.dao.AdvertisementsDao;

public class AdminIndexAdsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AdvertisementsDao advertisementsDao;
    public AdminIndexAdsController() {
        super();
        advertisementsDao = new AdvertisementsDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		
		ArrayList<Advertisements> listAdvertisements = advertisementsDao.getItems();
		request.setAttribute("listAds", listAdvertisements);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/indexAds.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
