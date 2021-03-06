package library;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;


public class AuthUtil {
	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response){
		HttpSession session =  request.getSession();
		
		if(session.getAttribute("userLogin") == null){
			try {
				response.sendRedirect(request.getContextPath() + "/admin/login?err=0");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		// đã login
		return true;
	}
	
	public static User checkAdminAndMod(HttpServletRequest request, HttpServletResponse response){
		HttpSession session =  request.getSession();
		User userLogin = (User)session.getAttribute("userLogin");
		if(userLogin.getIdTypeUser() == 1 || userLogin.getIdTypeUser() == 2)
			return userLogin;
		return null;
	}
}
