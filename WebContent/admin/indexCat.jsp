<%@page import="java.util.Arrays"%>
<%@page import="model.dao.CategoryDao"%>
<%@page import="model.bean.Category"%>
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
	                  	  	  <h4><i class="fa fa-angle-right"></i> Danh mục</h4>
	                  	  	  <h4>
	                  	  	  	<a class="" href="<%=request.getContextPath()%>/admin/cat/add">
		                          <i class="fa fa-plus-square-o"></i>
		                          <span>Thêm</span>
		                      	</a>
	                  	  	  </h4>
	                  	  	  <%
	                  	  	  	if("1".equals(request.getParameter("msg"))){
	                  	  	  		out.print("<p style='margin: 10px;color: green; font-weight: bold; font-size: 20px'>Thêm danh mục thành công</p>");
	                  	  	  	}
	                  	  	  	if("2".equals(request.getParameter("msg"))){
	                  	  	  		out.print("<p style='margin: 10px;color: green; font-weight: bold; font-size: 20px'>Sửa danh mục thành công</p>");
	                  	  	  	}
		                  	  	if("3".equals(request.getParameter("msg"))){
	                  	  	  		out.print("<p style='margin: 10px;color: green; font-weight: bold; font-size: 20px'>Xoá danh mục thành công</p>");
	                  	  	  	}
		                  	  	if("1".equals(request.getParameter("err"))){
	                  	  	  		out.print("<p style='margin: 10px;color: red; font-weight: bold; font-size: 20px'>Xoá danh mục thất bại</p>");
	                  	  	  	}
	                  	  	  %>
	                  	  	  <hr>
	                  	  	   <%
	                  	  	  	User objUserLogin = (User)session.getAttribute("userLogin");
	                  	  	  %>
		                      <table class="table">
		                          <thead>
		                          <tr>
		                              <th style="width:4%; text-align: center;">ID</th>
		                              <th>Tên danh mục</th>
		                              <th>Danh mục cha</th>
		                              <th style="text-align: center;">Thứ tự hiển thị</th>
		                              <th>Trạng thái</th>
		                              <th style="width:12%; text-align: center;">Chức năng</th>
		                          </tr>
		                          </thead>
		                          <tbody>
		                          <%
		                          	User userLogin = (User)session.getAttribute("userLogin");
		                          	if(request.getAttribute("listCat")!=null){
		                          		ArrayList<Category> listCat = (ArrayList<Category>)request.getAttribute("listCat");
		                         		for(Category objCat : listCat){
		                          %>
		                          <tr>
		                              <td style="text-align: center;"><%=objCat.getIdCat() %></td>
									  <td>
								      		<a href="<%=request.getContextPath() %>/admin/cat/edit?cid=<%=objCat.getIdCat()%>"><%if(objCat.getIdParent() > 0){out.print("|--"+objCat.getName());}else{out.print(objCat.getName());}  %></a>
									  </td>
		                              <td>
		                              		<%
		                              			if(objCat.getIdParent() > 0){Category objCatParent = (new CategoryDao().getItem(objCat.getIdParent()));  out.print(objCatParent.getName());}
		                              		%>
		                              </td>
		                              <td style="text-align: center;">
		                              <%=objCat.getDisplayIndex() %> 
		                              <%
		                              	if(objCat.getIdParent() > 0){
		                              		out.print("trong " + (new CategoryDao().getItem(objCat.getIdParent()).getName()));
		                              	}
		                              %>
		                              </td>
		                              <td id="danhmuc-<%=objCat.getIdCat()%>">
		                              	<a onclick="return active(<%=objCat.getIdCat()%>, <%=objCat.isActive()%>)" <%if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 ){out.print("class='noclick'");} %> style="margin-left: 15px" href="javascript:void(0)" title="" >
		                              		<%
		                              		if(objCat.isActive()){
		                              		%>
		                              		<i style="font-size: 2em; color: green;" class="fa fa-eye fa-2"></i>
		                              		<%}else{ %>
		                              		<i style="font-size: 2em; color: red;" class="fa fa-eye-slash"></i>
		                              		<%} %>
		                              	</a>
		                              </td>
		                              <td>
		                              	<a <%if(userLogin.getIdTypeUser() != 1 && userLogin.getIdTypeUser() != 2){out.print("class='noclick'");} %> style="text-align: center;" href="<%=request.getContextPath() %>/admin/cat/edit?cid=<%=objCat.getIdCat()%>">
		                              		<i <%if( userLogin.getIdTypeUser() != 1 && userLogin.getIdTypeUser() != 2){out.print("style='font-size: 1.5em ;color: red;' ");}else{out.print("style='font-size: 1.5em;'");} %> class="fa fa-wrench"></i> Sửa</a> &nbsp;||&nbsp; 
		                              	<a <%if(userLogin.getIdTypeUser() != 1 && userLogin.getIdTypeUser() != 2){out.print("class='noclick'");} %> style="text-align: center;" href="<%=request.getContextPath() %>/admin/cat/del?cid=<%=objCat.getIdCat()%>" onclick="return confirmAction()">
		                              		<i <%if( userLogin.getIdTypeUser() != 1 && userLogin.getIdTypeUser() != 2){out.print("style='font-size: 1.5em ;color: red;' ");}else{out.print("style='font-size: 1.5em;'");} %> class="fa fa-trash-o"></i> Xóa</a>
		                              </td>
		                          </tr>
		                          <%}} %>
		                          </tbody>
		                      </table>
		                      <script type="text/javascript">
			                      function active(id, status){
										$.ajax({
											// trang chuyển đến
											url: '<%=request.getContextPath()%>/admin/cat', 
											type: 'POST', // phương thức: post/get
											cache: false,
											data: {
													//Dữ liệu gửi đi -> key:value
													aid: id,
													astatus: status
													},
											success: function(data){
												// Xử lý thành công
												$("#danhmuc-"+id).html(data);
											},
											error: function (){
											// Xử lý nếu có lỗi
											alert("Có lỗi trong quá trình xử lý!");
											}
										});
									}
		                      </script>
	                  	  </div><!--/content-panel -->
	                  </div><!-- /col-md-12 -->
                  
      <!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->                  
                  
              </div><! --/row -->
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
