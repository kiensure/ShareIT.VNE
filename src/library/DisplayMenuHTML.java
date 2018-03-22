package library;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import model.bean.Category;
import model.dao.CategoryDao;

public class DisplayMenuHTML {
	
	public static void show(javax.servlet.http.HttpServletRequest request, javax.servlet.jsp.JspWriter out, ArrayList<Category> listCat, int idPa, String dm, int stt) throws ServletException, IOException {
		ArrayList<Category> listCatChild = new ArrayList<>();
		
//		for (Category category : listCat) {
//			if(category.getIdParent() == idPa){
//				listCatChild.add(category);
//			}
//			
//			
//		}
		listCatChild = (new CategoryDao().getItemsByIdPaAndSortByDisplayIndex(idPa));
		if(listCatChild.size() > 0){
			String ul = "<ul>";
			if(stt == 0){
				ul = "<ul class='sf-menu' id='main-menu'><li><a href ='"+request.getContextPath()+"/'>Home</a></li>";
			}else if(stt == 1){
				
			}else if(stt == 2){
				
			}
			out.println(ul);
			for (Category category : listCatChild) {
				if("liên hệ".equalsIgnoreCase(category.getName())){
					out.println("<li><a href='"+request.getContextPath()+"/contact'>"+category.getName()+"</a>");
				}else{
					out.println("<li><a href='"+request.getContextPath()+"/danh-muc/"+StringUntil.createSlugURL(category.getName())+"-"+category.getIdCat()+".html'>"+category.getName()+"</a>");
				}
				show(request,out, listCat, category.getIdCat(), "|---", ++stt);	
				out.println("</li>");
			}
			out.println("</ul>");
		}
	}
}
