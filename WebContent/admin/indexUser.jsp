<%@page import="model.bean.User"%>
<%@page import="library.DayFormat"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@include file="/templates/admin/inc/header.jsp" %>
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <%@include file="/templates/admin/inc/left_bar.jsp" %>   
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <div class="wrapper">
			  <h3><i class="fa fa-angle-right"></i> Trang quản lý</h3>
              <div class="row">
                  
                  <div class="col-md-12">
	                  	  <div class="content-panel">
	                  	  	  <h4><i class="fa fa-angle-right"></i> Quản trị viên</h4>
	                  	  	  <h4>
	                  	  	  	<a class="" href="<%=request.getContextPath()%>/admin/user/add">
		                          <i class="fa fa-plus-square-o"></i>
		                          <span>Thêm</span>
		                      	</a>
							  <%
							  	if("0".equals(request.getParameter("err"))){
									out.print("<p style=\"color: red; margin-top: 10px; font-weight: bold;\">Không có quyền xoá admin</p>");
								}
								if("1".equals(request.getParameter("err"))){
									out.print("<p style=\"color: red; margin-top: 10px; font-weight: bold;\">Xoá thất bại</p>");
								}
								if("1".equals(request.getParameter("msg"))){
									out.print("<p style=\"color: green; margin-top: 10px; font-weight: bold;\">Thêm user thành công</p>");
								}
								if("2".equals(request.getParameter("msg"))){
									out.print("<p style=\"color: green; margin-top: 10px; font-weight: bold;\">Sửa user thành công</p>");
								}
								if("3".equals(request.getParameter("msg"))){
									out.print("<p style=\"color: green; margin-top: 10px; font-weight: bold;\">Xoá user thành công</p>");
								}
							  %>
	                  	  	  </h4>
	                  	  	  <hr>
		                      <table class="table">
		                          <thead>
		                          <tr>
		                              <th>ID</th>
		                              <th>Họ tên</th>	
		                              <th>Fullname</th>
		                              <th>Email</th>
		                              <th>Cấp bậc</th>
		                              <th>Chức năng</th>
		                          </tr>
		                          </thead>
		                          <tbody>
		                          <%
		                          	User userLogin = (User)session.getAttribute("userLogin");
		                          	if(request.getAttribute("listUser")!=null){
		                          		@SuppressWarnings("unchecked")
		                          		ArrayList<User> listUser = (ArrayList<User>)request.getAttribute("listUser");
		                         		for(User objUser : listUser){
		                          %>
		                          <tr>
		                              <td><%=objUser.getIdUser() %></td>
		                              <td><%=objUser.getName() %></td>
		                              <td><%=objUser.getFullname() %></td>
		                              <td><%=objUser.getEmail() %></td>
		                              <td><%=objUser.getTypeUser() %></td>
		                              <td><a <%if(userLogin.getIdTypeUser() != 1 && userLogin.getIdUser() != objUser.getIdUser()){out.print("class='noclick'");} %> href="<%=request.getContextPath() %>/admin/user/edit?uid=<%=objUser.getIdUser()%>">
		                              		<i <%if(userLogin.getIdUser() != objUser.getIdUser() && userLogin.getIdTypeUser() != 1){out.print("style='font-size: 1.5em ;color: red;' ");}else{out.print("style='font-size: 1.5em;'");} %> class="fa fa-wrench"></i> Sửa</a> &nbsp;||&nbsp; 
		                              	<a <%if(userLogin.getIdTypeUser() != 1){out.print("class='noclick'");} %> href="<%=request.getContextPath() %>/admin/user/del?uid=<%=objUser.getIdUser()%>" onclick="return confirmAction()">
		                              		<i <%if(userLogin.getIdTypeUser() != 1){out.print("style='font-size: 1.5em ;color: red;' ");}else{out.print("style='font-size: 1.5em;'");} %> class="fa fa-trash-o"></i> Xóa</a>
		                              </td>
		                          </tr>
		                          <%}} %>
		                          </tbody>
		                      </table>
		                      <script type="text/javascript">
							    function confirmAction(){
							      return confirm('Bạn có chắc muốn xóa?');
							    }
							  </script>
	                  	  </div><!--/content-panel -->
	                  </div><!-- /col-md-12 -->
                  
                  
                  
      <!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->                  
                  
              </div><!--/row -->
          </div>
          <%@include file="/templates/admin/inc/footer.jsp" %>
      </section>

      <!--main content end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery-1.8.3.min.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.scrollTo.min.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.sparkline.js"></script>


    <!--common script for all pages-->
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/common-scripts.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/gritter-conf.js"></script>

  </body>
</html>
