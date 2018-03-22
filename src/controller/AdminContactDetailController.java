package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthUtil;
import model.bean.Contact;
import model.dao.ContactDao;


public class AdminContactDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDao contactDao;
       
    public AdminContactDetailController() {
        super();
        contactDao = new ContactDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		
		int conid = 0;
		try {
			conid = Integer.parseInt(request.getParameter("conid"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/contact");
			return;
		}
		
		Contact objContact = contactDao.getItem(conid);
		
		if(objContact != null){
			
			if(contactDao.editItemChangeStatus(objContact) > 0){
				request.setAttribute("objContact", objContact);
				RequestDispatcher rd = request.getRequestDispatcher("/admin/contactdetail.jsp");
				rd.forward(request, response);
				return;
			}else{
				response.sendRedirect(request.getContextPath() + "/admin/contact");
				return;
			}
			
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/contact");
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
