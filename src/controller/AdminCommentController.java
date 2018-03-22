package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthUtil;
import model.bean.Comment;
import model.dao.CommentDao;

public class AdminCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDao cmtDao;
       
    public AdminCommentController() {
        super();
        cmtDao = new CommentDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		
		ArrayList<Comment> listComments = cmtDao.getItems();
		request.setAttribute("listComments", listComments);
			
		RequestDispatcher rd = request.getRequestDispatcher("/admin/comment.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("aid"));
		boolean status = Boolean.valueOf(request.getParameter("astatus"));
		if(status == false){
			status = true;
			Comment cmt = new Comment(id, "", "", "", "", 0, null, 0, 0, status);
			if(cmtDao.editItemByStatus(cmt,status) > 0){
				out.print("<a style='margin-left: 15px' href='javascript:void(0)' title='' onclick='return active("+ id + ","+ status +" )'><i style='font-size: 2em; color: green;' class='fa fa-eye fa-2'></i></a>");
			}
		} else{
			status = false;
			Comment cmt = new Comment(id, "", "", "", "", 0, null, 0, 0, status);
			if(cmtDao.editItemByStatus(cmt,status) > 0){
				out.print("<a style='margin-left: 15px' href='javascript:void(0)' title='' onclick='return active("+ id + ","+ status +" )'><i style='font-size: 2em; color: red;' class='fa fa-eye-slash'></i></a>");
			}
		}
	}

}
