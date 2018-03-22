package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.AuthUtil;
import model.bean.Comment;
import model.dao.CommentDao;

public class AdminDelCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CommentDao cmtDao;
	
    public AdminDelCommentController() {
        super();
        cmtDao = new CommentDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		
		if(AuthUtil.checkAdminAndMod(request, response) != null){
			int cmtID = 0;
			try {
				cmtID = Integer.parseInt(request.getParameter("cmtid"));
			} catch (NumberFormatException e) {
				response.sendRedirect(request.getContextPath() + "/admin/comment");
				return;
			}
			Comment objCmt = cmtDao.getItem(cmtID);
			if(objCmt != null){
				if(cmtDao.delItem(cmtID) > 0){
					response.sendRedirect(request.getContextPath() + "/admin/comment?msg=1");
					return;
				}else{
					response.sendRedirect(request.getContextPath() + "/admin/comment?err=1");
					return;
				}
			}else{
				response.sendRedirect(request.getContextPath() + "/admin/comment");
				return;
			}
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/comment");
			return;
		}
	}

}
