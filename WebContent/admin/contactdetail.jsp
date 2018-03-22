<%@page import="model.bean.Contact"%>
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
	                  	  	  <h4><i class="fa fa-angle-right"></i>Chi tiết liên hệ</h4>
	                  	  	  <table width="800px" border="1" style="margin-left: 10px;">
	                  	  	  		<%
	                  	  	  			Contact objContact = (Contact)request.getAttribute("objContact");
	                  	  	  			if(objContact != null){
	                  	  	  		%>
									<tr>
										<td width="152px" valign="top">Họ và tên: </td>
										<td valign="top">
											<input type="text" value="<%=objContact.getName() %>" name="username" size="32"  />
										</td>
									</tr>
									
									<tr>
										<td valign="top">Email: </td>
										<td valign="top">
											<input type="text" value="<%=objContact.getEmail() %>" size="32" />
										</td>
									</tr>
									
									<tr>
										<td valign="top">Phone: </td>
										<td valign="top">
											<input type="text" value="<%=objContact.getPhone() %>" size="32" />
										</td>
									</tr>
									
									<tr>
										<td valign="top">Nội dung</td>
										<td valign="top">
											<textarea rows="6" cols="80" name="gioithieu" id="gioithieu"><%=objContact.getContent() %></textarea>
										</td>
									</tr>
									
									<tr>
										<td valign="top">Ngày: </td>
										<td valign="top">
											<input type="text" value="<%=DayFormat.fD(objContact.getDate()) %>" size="32" />
										</td>
									</tr>
									<%} %>
							  </table>
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
