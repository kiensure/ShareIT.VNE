package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contact;
import model.dao.ContactDao;

public class PublicContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDao contactDao;
       
    public PublicContactController() {
        super();
        contactDao = new ContactDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/contact.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("ahoten");
		String email = request.getParameter("aemail");
		String phone = request.getParameter("aphone");
		String content = request.getParameter("acontent");
		Timestamp date = new Timestamp(new Date().getTime());
	
		Contact objContact = new Contact(0, name, phone, email, date, content ,false);
		
		if(contactDao.addItem(objContact) > 0){
			out.print("<div class='alert alert-success'><p style='text-align: center; margin: auto; color: green;'><strong>Cảm ơn bạn đã phản hồi. Chúng tôi sẽ trả lời bạn sớm nhất có thể </strong></p></p></div>");
		}
	}

}
