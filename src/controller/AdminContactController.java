package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthUtil;
import model.bean.Contact;
import model.dao.ContactDao;

public class AdminContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDao contactDao;
       
    public AdminContactController() {
        super();
        contactDao = new ContactDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		
		ArrayList<Contact> listContact = contactDao.getItems();
		request.setAttribute("listContact", listContact);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/contact.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
