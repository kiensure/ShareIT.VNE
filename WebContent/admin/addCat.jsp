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
          	
          	<!-- BASIC FORM ELELEMNTS -->
          	<div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Thêm danh mục</h4>
                  	  <%
		                  	if("0".equals(request.getParameter("err"))){
								out.print("<span style=\"color: red; font-weight: bold;\">Tên danh mục vượt quá số ký tự cho phép</span>");
							}
						 	if("1".equals(request.getParameter("err"))){
								out.print("<span style=\"color: red; font-weight: bold;\">Có lỗi</span>");
							}
				 	  %>
                      <form id="form" class="form-horizontal style-form" method="post" action="<%=request.getContextPath() %>/admin/cat/add">
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Tên danh mục:</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="tendanhmuc" value="">
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Danh mục cha:</label>
                              <div class="col-sm-10">
                                  <select class="form-control" name="danhmuc">
                                  <option value="0">--Không có--</option>
                                  <%
                                  	if(request.getAttribute("listCat") != null){
                                  		@SuppressWarnings("unchecked") 
										ArrayList<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
										if(listCat != null && listCat.size() > 0){
											for(Category item : listCat){							
								 %>
									<option value="<%=item.getIdCat()%>"><%=item.getName()%></option>
								<%}}} %>
								  </select>
                              </div>
                          </div>
                          <div style="text-align: center;">
                          	<button type="submit" class="btn btn-theme">Thêm</button>
                          </div>
                          
                      </form>
                  </div>
          		</div><!-- col-lg-12-->      	
          	</div><!-- /row -->

          </div>
          <%@include file="/templates/admin/inc/footer.jsp" %>
      </section>

      <!--main content end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
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
