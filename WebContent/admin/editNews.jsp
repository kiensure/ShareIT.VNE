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
			  <h3><i class="fa fa-angle-right"></i> Bài viết</h3>
          	
          	<!-- BASIC FORM ELELEMNTS -->
          	<div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Sửa bài viết</h4>
                  	  <%
		                  	if(request.getAttribute("news") != null){
		    					News itemNews = (News)request.getAttribute("news");
		    					if("0".equals(request.getParameter("err"))){
	                  	  	  		out.print("<p style='margin: 10px;color: red; font-weight: bold; font-size: 20px'>Tên tin quá số ký tự cho phép</p>");
	                  	  	  	}
		    					if("1".equals(request.getParameter("err"))){
	                  	  	  		out.print("<p style='margin: 10px;color: red; font-weight: bold; font-size: 20px'>Có lỗi</p>");
	                  	  	  	}
                  	  %>
                      <form id="form" class="form-horizontal style-form" action="<%=request.getContextPath()%>/admin/news/edit?nid=<%=itemNews.getIdNews()%>" enctype="multipart/form-data" method="post">
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Tên bài viết:</label>
                              <div class="col-sm-10">
                                  <input name="tentin" type="text" class="form-control" value="<%=itemNews.getName()%>">
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Danh mục:</label>
                              <div class="col-sm-10">
                                  <select class="form-control" name="danhmuc">
                                  	  <%
											if(request.getAttribute("listCat") != null){
												@SuppressWarnings("unchecked")
												ArrayList<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
												String selected = "";
												if(listCat.size() > 0){
												for(Category item : listCat){
													if(itemNews.getIdCat() == item.getIdCat()){
														selected = "selected = 'selected'";
													}else{
														selected = "";
													}
									  %>
											<option <%=selected %> value="<%=item.getIdCat()%>"><%=item.getName() %></option>
									  <%}}} %>
								  </select>
                              </div>
                          </div>
                          <div class="form-group">
							 <label class="col-sm-2 col-sm-2 control-label">Hình ảnh:</label>
							 <div class="col-sm-10">
							 <input type="file" name="picture" class="form-control" placeholder="Chọn ảnh" />
							 <%
								    if(!"".equals(itemNews.getPicture())){
							 %>
									<img style="width: 150px;height: 100px" src="<%=request.getContextPath() %>/files/<%=itemNews.getPicture() %>" alt="<%=itemNews.getPicture() %>" />
							 <%} %>
							 </div>
						  </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Mô tả:</label>
                              <div class="col-sm-10">
                                  <textarea id="mota" name="mota" rows="4" cols="" placeholder="Mô tả bài viết" class="form-control"><%=itemNews.getPreview() %></textarea>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Chi tiết:</label>
                              <div class="col-sm-10">
                                  <textarea id="chitiet" name="chitiet" rows="4" cols="" placeholder="Chi tiết bài viết" class="form-control"><%=itemNews.getDetail() %></textarea>
                              </div>
                          </div>
                          <script type="text/javascript">
								var editorMT = CKEDITOR.replace('mota');
								var editorCT = CKEDITOR.replace('chitiet');
								CKFinder.setupCKEditor(editorMT,"<%=request.getContextPath() %>/templates/admin/assets/js/ckfinder/");
								CKFinder.setupCKEditor(editorCT,"<%=request.getContextPath() %>/templates/admin/assets/js/ckfinder/");
						  </script>
                          <div style="text-align: center;">
                          	<button type="submit" class="btn btn-theme">Sửa</button>
                          </div>
                      </form>
                      <%} %>
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
