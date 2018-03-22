<%@page import="model.dao.LocalAdsDao"%>
<%@page import="model.bean.LocationAds"%>
<%@page import="model.bean.Advertisements"%>
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
	                  	  	  <h4><i class="fa fa-angle-right"></i> Quảng cáo</h4>
	                  	  	  <h4>
	                  	  	  	<a class="" href="<%=request.getContextPath()%>/admin/ads/add">
		                          <i class="fa fa-plus-square-o"></i>
		                          <span>Thêm</span>
		                      	</a>
	                  	  	  </h4>
	                  	  	  <%
	                  	  	  	if("1".equals(request.getParameter("msg"))){
	                  	  	  		out.print("<p style='margin: 10px;color: green; font-weight: bold; font-size: 20px'>Thêm quảng cáo thành công</p>");
	                  	  	  	}
	                  	  	  	if("2".equals(request.getParameter("msg"))){
	                  	  	  		out.print("<p style='margin: 10px;color: green; font-weight: bold; font-size: 20px'>Sửa quảng cáo thành công</p>");
	                  	  	  	}
		                  	  	if("3".equals(request.getParameter("msg"))){
	                  	  	  		out.print("<p style='margin: 10px;color: green; font-weight: bold; font-size: 20px'>Xoá quảng cáo thành công</p>");
	                  	  	  	}
		                  	  	if("1".equals(request.getParameter("err"))){
	                  	  	  		out.print("<p style='margin: 10px;color: red; font-weight: bold; font-size: 20px'>Xoá quảng cáo thất bại</p>");
	                  	  	  	}
	                  	  	  %>
	                  	  	  <hr>
		                      <table class="table">
		                          <thead>
		                          <tr>
		                              <th style="width:4%; text-align: center;">ID</th>
		                              <th style="width: 5%; text-align: center;">Vị trí</th>
		                              <th>Tên quảng cáo</th>
		                              <th style="width:20%; text-align: center;">Hình ảnh</th>
		                              <th style="width:25%; text-align: center;">Link</th>
		                              <th style="width:12%; text-align: center;">Chức năng</th>
		                          </tr>
		                          </thead>
		                          <tbody>
		                           	  <%
		                          	  	ArrayList<Advertisements> listAds = (ArrayList<Advertisements>)request.getAttribute("listAds");
		                          	  	for(Advertisements objAds : listAds){
		                          	  %>
		                          <tr>
		                              <td style="text-align: center;"><%=objAds.getIdAds() %></td>
		                              <%
		                              	LocationAds objLocaAds = (new LocalAdsDao().getItem(objAds.getLocation()));
		                              %>
		                              <td style="text-align: center;"><%=objLocaAds.getName() %></td>
		                              <td><%=objAds.getNameAds() %></td>
		                              <td style="text-align: center;">
		                              	<%
											if(!"".equals(objAds.getPicture())){
										%>
										<img style="width:100px; height: 60px;border-radius:4px; text-align: center;" src="<%=request.getContextPath()%>/files/<%=objAds.getPicture()%>" />
										<%}else{ %>
										<img style="width:100px; height: 60px;border-radius:4px; text-align: center;" src="<%=request.getContextPath() %>/templates/admin/assets/img/no-img.png" alt="" />
										<%} %>
		                              </td>
		                              <td style="text-align: center;">
		                              	<a  style="margin-left: 15px" href="<%=objAds.getLink() %>" title="" ><%=objAds.getLink() %></a>
		                              </td>
		                              <td>
		                              	<a <%if(objInfoUser.getIdTypeUser() != 1 && objInfoUser.getIdTypeUser() != 2){out.print("class='noclick'");} %> style="text-align: center;" href="<%=request.getContextPath() %>/admin/ads/edit?aid=<%=objAds.getIdAds()%>">
		                              		<i <%if(objInfoUser.getIdTypeUser() != 1 && objInfoUser.getIdTypeUser() != 2){out.print("style='font-size: 1.5em ;color: red;' ");}else{out.print("style='font-size: 1.5em;'");} %> class="fa fa-wrench"></i> Sửa</a> &nbsp;||&nbsp; 
		                              	<a <%if(objInfoUser.getIdTypeUser() != 1 && objInfoUser.getIdTypeUser() != 2){out.print("class='noclick'");} %> style="text-align: center;" href="<%=request.getContextPath() %>/admin/ads/del?aid=<%=objAds.getIdAds()%>" onclick="return confirmAction()">
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
										url: '<%=request.getContextPath()%>/admin/news', 
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
