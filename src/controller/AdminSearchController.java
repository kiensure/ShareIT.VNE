package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.AuthUtil;
import library.DayFormat;
import model.bean.News;
import model.bean.User;
import model.dao.NewsDao;

public class AdminSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDao newsDao;
       
    public AdminSearchController() {
        super();
        newsDao = new NewsDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		if(!AuthUtil.checkLogin(request, response)){
			return;
		}
		
		User objUserLogin = (User)session.getAttribute("userLogin");
		
		String name = "", date = "";
		if(request.getParameter("aname") != null){
			name = request.getParameter("aname");
		}
		if(request.getParameter("adate") != null) {
			date = request.getParameter("adate");
		}
		
		int status = 0;
		
		try {
			status = Integer.parseInt(request.getParameter("astatus"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/news");
			return;
		}
		
		ArrayList<News> listNews = newsDao.getItems(name, date, status);
		if(listNews.size() > 0){
			for(int i = 0; i< listNews.size();i++){
				out.print("<tr>"+
			                              "<td style='text-align: center;'>"+listNews.get(i).getIdNews()+"</td>"+
			                              "<td><a target='_blank' href="+request.getContextPath()+"/detail?nid="+listNews.get(i).getIdNews()+">"+listNews.get(i).getName()+"</a></td>"+
			                              "<td style='text-align: center;'>");
											if(!"".equals(listNews.get(i).getPicture())){
												out.print("<img style='width:100px; height: 60px;border-radius:4px;' src='"+request.getContextPath()+"/files/"+listNews.get(i).getPicture()+"' />");
											}else{
												out.print("<img style='width:100px; height: 60px;border-radius:4px;' src='"+request.getContextPath()+"/templates/admin/assets/img/no-img.png' alt='' />");
											}
			                             out.print("</td>"+
			                             "<td style='text-align: center;'>"+DayFormat.fD(listNews.get(i).getDate_Create())+"</td>"+
			                              "<td>"+listNews.get(i).getCatName()+"</td>"+
			                              "<td id='isSlide-"+listNews.get(i).getIdNews()+"'>"+
			                              	"<a onclick='return activeSlide("+listNews.get(i).getIdNews()+", "+listNews.get(i).isIs_Slide()+")'");
			                              	if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2){
			                              		out.print("class='noclick'");} 
				                             out.print("style='margin-left: 15px' href='javascript:void(0)'>");
				                             if(listNews.get(i).isIs_Slide()){
				                            	 out.print("<input id='checkSlide' checked type='checkbox' />");
				                             }else{
				                            	 out.print("<input id='checkSlide' type='checkbox' />");
				                             }
			                              	out.print("</a>"+
			                              "</td>"+
			                              "<td id='tin-"+listNews.get(i).getIdNews()+"' >"+
			                              	"<a onclick='return active("+listNews.get(i).getIdNews()+", "+listNews.get(i).isActive()+")'");
			                              	if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 ){
			                              		out.print("class='noclick'");} 
			                              	out.print("style='margin-left: 15px' href='javascript:void(0)' title='' >");
			                              		if(listNews.get(i).isActive()){
			                              		out.print("<i style='font-size: 2em; color: green;' class='fa fa-eye fa-2'></i>");
			                              		}else{
			                              		out.print("<i style='font-size: 2em; color: red;' class='fa fa-eye-slash'></i>");
			                              		}
			                              		
			                              		
			                              	out.print("</a>"+
			                              "</td>"+
			                              "<td id='hotnews-"+listNews.get(i).getIdNews()+"' >"+
			                              	"<a onclick='return activeHotNews("+listNews.get(i).getIdNews()+", "+listNews.get(i).isHotNew()+")'");
			                              	if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 ){
			                              		out.print("class='noclick'");}
			                              	out.print("style='margin-left: 15px' href='javascript:void(0)' title='' >");
			                              		if(listNews.get(i).isHotNew()){
			                              		out.print("<i style='font-size: 2em; color: green;' class='fa fa-check'></i>");
			                              		}else{
			                              		out.print("<i style='font-size: 2em; color: red;' class='fa fa-times'></i>");
			                              		}
			                              	out.print("</a>"+
			                              "</td>"+
			                              "<td><a ");
			                              	if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 && objUserLogin.getIdUser() != listNews.get(i).getIdUser()){
			                              		out.print("class='noclick'");}
			                              	out.print("style='text-align: center;' href='"+request.getContextPath()+"/admin/news/edit?nid="+listNews.get(i).getIdNews()+"'>"+
			                              		"<i ");
			                              	if(objUserLogin.getIdUser() != listNews.get(i).getIdUser() && objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2){
			                              		out.print("style='font-size: 1.5em ;color: red;'");
			                              	}else{
			                              		out.print("style='font-size: 1.5em;'");} 
			                              	out.print("class='fa fa-wrench'></i> Sửa</a> &nbsp;||&nbsp; "+
			                              	"<a ");
			                              	if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 && objUserLogin.getIdUser() != listNews.get(i).getIdUser()){
			                              		out.print("class='noclick'");} 
			                              	out.print("style='text-align: center;' href='"+request.getContextPath()+"/admin/news/del?nid="+listNews.get(i).getIdNews()+"' onclick='return confirmAction()'>"+
			                              		"<i "); 
			                              		if(objUserLogin.getIdUser() != listNews.get(i).getIdUser() && objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2){
			                              			out.print("style='font-size: 1.5em ;color: red;' ");}
			                              		else{out.print("style='font-size: 1.5em;'");} out.print("class='fa fa-trash-o'></i> Xóa</a>"+
			                              "</td>"+
			                          "</tr>");
			}
		}else{
			out.print("<tr><td colspan='8' style='font-size: 20px;font-weight: bold;text-align: center;'>Không tìm thấy kết quả</td></tr>");
		}
		
		
		if(request.getAttribute("page")!=null){
		int page = Integer.parseInt(request.getParameter("page"));
		if(page < 1){
			page = 1;
		}
		
		int limit = 4;
		int start = (limit * page) - limit;
		
		ArrayList<News> listNews2 = newsDao.getItems(start,limit);
		int total = listNews2.size();
		if(total > limit){
			for (int i = 0; i < total - 1; i++) {
				out.print("<tr>"+
                        "<td style='text-align: center;'>"+listNews2.get(i).getIdNews()+"</td>"+
                        "<td>"+listNews2.get(i).getName()+"</td>"+
                        "<td style='text-align: center;'>");
							if(!"".equals(listNews2.get(i).getPicture())){
								out.print("<img style='width:100px; height: 60px;border-radius:4px;' src='"+request.getContextPath()+"/files/"+listNews2.get(i).getPicture()+"' />");
							}else{
								out.print("<img style='width:100px; height: 60px;border-radius:4px;' src='"+request.getContextPath()+"/templates/admin/assets/img/no-img.png' alt='' />");
							}
                       out.print("</td>"+
                       "<td style='text-align: center;'>"+DayFormat.fD(listNews2.get(i).getDate_Create())+"</td>"+
                        "<td>"+listNews2.get(i).getCatName()+"</td>"+
                        "<td id='tin-"+listNews2.get(i).getIdNews()+"' >"+
                        	"<a onclick='return active("+listNews2.get(i).getIdNews()+", "+listNews2.get(i).isActive()+")'");
                        	if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 ){
                        		out.print("'class='noclick' ");} 
                        	out.print("style='margin-left: 15px' href='javascript:void(0)' title='' >");
                        		if(listNews2.get(i).isActive()){
                        		out.print("<i style='font-size: 2em; color: green;' class='fa fa-eye fa-2'></i>");
                        		}else{
                        		out.print("<i style='font-size: 2em; color: red;' class='fa fa-eye-slash'></i>");
                        		}
                        		
                        		
                        	out.print("</a>"+
                        "</td>"+
                        "<td id='hotnews-"+listNews2.get(i).getIdNews()+"' >"+
                        	"<a onclick='return activeHotNews("+listNews2.get(i).getIdNews()+", "+listNews2.get(i).isHotNew()+")'");
                        	if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 ){
                        		out.print("class='noclick'");}
                        	out.print("style='margin-left: 15px' href='javascript:void(0)' title='' >");
                        		if(listNews2.get(i).isHotNew()){
                        		out.print("<i style='font-size: 2em; color: green;' class='fa fa-check'></i>");
                        		}else{
                        		out.print("<i style='font-size: 2em; color: red;' class='fa fa-times'></i>");
                        		}
                        	out.print("</a>"+
                        "</td>"+
                        "<td><a");
                        	if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 && objUserLogin.getIdUser() != listNews2.get(i).getIdUser()){
                        		out.print("class='noclick'");}
                        	out.print("style='text-align: center;' href='"+request.getContextPath()+"/admin/news/edit?nid="+listNews2.get(i).getIdNews()+"'>"+
                        		"<i");
                        	if(objUserLogin.getIdUser() != listNews2.get(i).getIdUser() && objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2){
                        		out.print("style='font-size: 1.5em ;color: red;' ");
                        	}else{
                        		out.print("style='font-size: 1.5em;'");} 
                        	out.print("class='fa fa-wrench'></i> Sửa</a> &nbsp;||&nbsp; "+
                        	"<a");
                        	if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 && objUserLogin.getIdUser() != listNews2.get(i).getIdUser()){
                        		out.print("class='noclick'");} 
                        	out.print("style='text-align: center;' href='"+request.getContextPath()+"/admin/news/del?nid="+listNews2.get(i).getIdNews()+"' onclick='return confirmAction()'>"+
                        		"<i"); 
                        		if(objUserLogin.getIdUser() != listNews2.get(i).getIdUser() && objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2){
                        			out.print("style='font-size: 1.5em ;color: red;' ");}
                        		else{out.print("style='font-size: 1.5em;'");} out.print("class='fa fa-trash-o'></i> Xóa</a>"+
                        "</td>"+
                    "</tr>");
									
			}
		}else{
			for (int i = 0; i < total; i++) {
				out.print("<tr>"+
                        "<td style='text-align: center;'>"+listNews2.get(i).getIdNews()+"</td>"+
                        "<td>"+listNews2.get(i).getName()+"</td>"+
                        "<td style='text-align: center;'>");
							if(!"".equals(listNews2.get(i).getPicture())){
								out.print("<img style='width:100px; height: 60px;border-radius:4px;' src='"+request.getContextPath()+"/files/"+listNews2.get(i).getPicture()+"' />");
							}else{
								out.print("<img style='width:100px; height: 60px;border-radius:4px;' src='"+request.getContextPath()+"/templates/admin/assets/img/no-img.png' alt='' />");
							}
                       out.print("</td>"+
                       "<td style='text-align: center;'>"+DayFormat.fD(listNews2.get(i).getDate_Create())+"</td>"+
                        "<td>"+listNews2.get(i).getCatName()+"</td>"+
                        "<td id='tin-"+listNews2.get(i).getIdNews()+"' >"+
                        	"<a onclick='return active("+listNews2.get(i).getIdNews()+", "+listNews2.get(i).isActive()+")'");
                        	if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 ){
                        		out.print("'class='noclick' ");} 
                        	out.print("style='margin-left: 15px' href='javascript:void(0)' title='' >");
                        		if(listNews2.get(i).isActive()){
                        		out.print("<i style='font-size: 2em; color: green;' class='fa fa-eye fa-2'></i>");
                        		}else{
                        		out.print("<i style='font-size: 2em; color: red;' class='fa fa-eye-slash'></i>");
                        		}
                        		
                        		
                        	out.print("</a>"+
                        "</td>"+
                        "<td id='hotnews-"+listNews2.get(i).getIdNews()+"' >"+
                        	"<a onclick='return activeHotNews("+listNews2.get(i).getIdNews()+", "+listNews2.get(i).isHotNew()+")'");
                        	if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 ){
                        		out.print("class='noclick'");}
                        	out.print("style='margin-left: 15px' href='javascript:void(0)' title='' >");
                        		if(listNews2.get(i).isHotNew()){
                        		out.print("<i style='font-size: 2em; color: green;' class='fa fa-check'></i>");
                        		}else{
                        		out.print("<i style='font-size: 2em; color: red;' class='fa fa-times'></i>");
                        		}
                        	out.print("</a>"+
                        "</td>"+
                        "<td><a");
                        	if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 && objUserLogin.getIdUser() != listNews2.get(i).getIdUser()){
                        		out.print("class='noclick'");}
                        	out.print("style='text-align: center;' href='"+request.getContextPath()+"/admin/news/edit?nid="+listNews2.get(i).getIdNews()+"'>"+
                        		"<i");
                        	if(objUserLogin.getIdUser() != listNews2.get(i).getIdUser() && objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2){
                        		out.print("style='font-size: 1.5em ;color: red;' ");
                        	}else{
                        		out.print("style='font-size: 1.5em;'");} 
                        	out.print("class='fa fa-wrench'></i> Sửa</a> &nbsp;||&nbsp; "+
                        	"<a");
                        	if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 && objUserLogin.getIdUser() != listNews2.get(i).getIdUser()){
                        		out.print("class='noclick'");} 
                        	out.print("style='text-align: center;' href='"+request.getContextPath()+"/admin/news/del?nid="+listNews2.get(i).getIdNews()+"' onclick='return confirmAction()'>"+
                        		"<i"); 
                        		if(objUserLogin.getIdUser() != listNews2.get(i).getIdUser() && objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2){
                        			out.print("style='font-size: 1.5em ;color: red;' ");}
                        		else{out.print("style='font-size: 1.5em;'");} out.print("class='fa fa-trash-o'></i> Xóa</a>"+
                        "</td>"+
                    "</tr>");
			}
		}
		if(total <= limit){
			out.print("<script language='javascript'>stopped = true; </script>");
		}
	}
	}

}
