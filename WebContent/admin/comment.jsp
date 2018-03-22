<%@page import="model.dao.NewsDao"%>
<%@page import="model.bean.Comment"%>
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
	                  	  	  <h4><i class="fa fa-angle-right"></i> Bình luận</h4>
	                  	  	  <hr>
		                      <table class="table">
		                          <thead>
		                          <tr>
		                              <th style="width:4%; text-align: center;">ID</th>
		                              <th>Tên bài viết</th>
		                              <th style="width:15%; text-align: center;">Họ và tên</th>
		                              <th style="width:12%; text-align: center;">Email</th>
		                              <th style="width:10%">Website</th>
		                              <th >Bình luận</th>
		                              <th style="width:8%; text-align: center;">Trạng thái</th>
		                              <th style="width:8%; text-align: center;">Chức năng</th>
		                          </tr>
		                          </thead>
		                          <tbody>
		                          <%
		                          		ArrayList<Comment> listCmt = (ArrayList<Comment>)request.getAttribute("listComments");
		                          		for(Comment objCmt : listCmt){
		                          %>
		                          <tr>
		                              <td style="text-align: center;"><%=objCmt.getIdComment() %></td>
		                              <%
		                              	News objNewsCmt = (new NewsDao().getItem(objCmt.getIdNews()));
		                              %>
		                              <td><a target="_blank" href="<%=request.getContextPath()%>/detail?nid=<%=objNewsCmt.getIdNews()%>"><%=objNewsCmt.getName() %></a></td>
		                              <td style="text-align: center;"><%=objCmt.getFullName() %></td>
		                              <td style="text-align: center;"><%=objCmt.getEmail() %></td>
		                              <td><%=objCmt.getWebsite() %></td>
		                              <td><%=objCmt.getContent() %></td>
		                              <td id="tin-<%=objCmt.getIdComment()%>">
		                              	<a onclick="return active(<%=objCmt.getIdComment()%>, <%=objCmt.isActive()%>)" <%if(objInfoUser.getIdTypeUser() != 1 && objInfoUser.getIdTypeUser() != 2 ){out.print("class='noclick'");} %> style="margin-left: 15px" href="javascript:void(0)" title="" >
		                              		<%
		                              		if(objCmt.isActive()){
		                              		%>
		                              		<i style="font-size: 2em; color: green;" class="fa fa-eye fa-2"></i>
		                              		<%}else{ %>
		                              		<i style="font-size: 2em; color: red;" class="fa fa-eye-slash"></i>
		                              		<%} %>
		                              	</a>
		                              </td>
		                              <td>
		                              <a <%if(objInfoUser.getIdTypeUser() != 1 && objInfoUser.getIdTypeUser() != 2){out.print("class='noclick'");} %> style="text-align: center;" href="<%=request.getContextPath() %>/admin/comment/del?cmtid=<%=objCmt.getIdComment()%>" onclick="return confirmAction()">
		                              		<i <%if(objInfoUser.getIdTypeUser() != 1 && objInfoUser.getIdTypeUser() != 2){out.print("style='font-size: 1.5em ;color: red;' ");}else{out.print("style='font-size: 1.5em;'");} %> class="fa fa-trash-o"></i> Xóa</a>
		                              </td>
		                          </tr>
		                          <%} %>
		                          </tbody>
		                      </table>
		                      <script type="text/javascript">
							    function confirmAction(){
							      return confirm('Bạn có chắc muốn xóa?');
							    }
							  </script>
							  <script type="text/javascript">
								function active(id, status){
									$.ajax({
										// trang chuyển đến
										url: '<%=request.getContextPath()%>/admin/comment', 
										type: 'POST', // phương thức: post/get
										cache: false,
										data: {
												//Dữ liệu gửi đi -> key:value
												aid: id,
												astatus: status
												},
										success: function(data){
											// Xử lý thành công
											$("#tin-"+id).html(data);
										},
										error: function (){
										// Xử lý nếu có lỗi
										alert("Có lỗi trong quá trình xử lý!");
										}
									});
								}
								
							</script>
		                      <div class="text-center">
							  </div>
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
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.scrollTo.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.sparkline.js"></script>

	 <!--script for this page-->
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery-ui-1.9.2.custom.min.js"></script>

	<!--custom switch-->
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/bootstrap-switch.js"></script>
	
	<!--custom tagsinput-->
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.tagsinput.js"></script>
	
	<!--custom checkbox & radio-->
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/bootstrap-daterangepicker/daterangepicker.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
	
	
	<script type="text/javascript"  src="<%=request.getContextPath() %>/templates/admin/assets/js/form-component.js"></script>    
    
    
  	<script>
      	//custom select box

      	$(function(){
          	$('select.styled').customSelect();
      	});
  	</script>
	
    <!--common script for all pages-->
    <script type="text/javascript"  src="<%=request.getContextPath() %>/templates/admin/assets/js/common-scripts.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/gritter-conf.js"></script>

  </body>
</html>
