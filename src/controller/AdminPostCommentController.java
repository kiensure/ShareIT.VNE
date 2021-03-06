package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.dao.CommentDao;


public class AdminPostCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CommentDao cmtDao;   
    
    public AdminPostCommentController() {
        super();
        cmtDao = new CommentDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("ahoten");
		String email = request.getParameter("aemail");
		String website = request.getParameter("awebsite");
		String content = request.getParameter("acontent");
		Timestamp date_create = new Timestamp(new Date().getTime());
		int idNews = Integer.parseInt(request.getParameter("anews_id"));
	
		Comment objCmt = new Comment(0, name, email, website, content, 0, date_create, 0, idNews, false);
		
		if(cmtDao.addItem(objCmt) > 0){
			out.print("<div class='alert alert-success'><p style='text-align: center; margin: auto; color: green;'><strong>Cảm ơn bạn đã bình luận. Chúng tôi sẽ xem xét bình luận của bạn</strong></p></p></div>");
		}
	}

}
