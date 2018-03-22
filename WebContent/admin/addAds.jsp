<%@page import="model.bean.LocationAds"%>
<%@page import="model.bean.Advertisements"%>
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
			  <h3><i class="fa fa-angle-right"></i> Quảng cáo</h3>
          	
          	<!-- BASIC FORM ELELEMNTS -->
          	<div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> Thêm quảng cáo</h4>
                      <form id="form" class="form-horizontal style-form" action="<%=request.getContextPath()%>/admin/ads/add" enctype="multipart/form-data" method="post">
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Tên quảng cáo:</label>
                              <div class="col-sm-10">
                                  <input name="tenads" type="text" class="form-control" value="">
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Vị trí:</label>
                              <div class="col-sm-10">
                                  <select class="form-control" name="locationads">
                                  <%
                                  	if(request.getAttribute("listLocation") != null){
                                  		@SuppressWarnings("unchecked") 
										ArrayList<LocationAds> listLocation = (ArrayList<LocationAds>) request.getAttribute("listLocation");
										if(listLocation != null && listLocation.size() > 0){
											for(LocationAds item : listLocation){							
								 %>
									<option value="<%=item.getIdLocal()%>"><%=item.getName() %></option>
								<%}}} %>
								  </select>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Link:</label>
                              <div class="col-sm-10">
                                  <input name="link" type="text" class="form-control" value="">
                              </div>
                          </div>
                          <div class="form-group">
							 <label class="col-sm-2 col-sm-2 control-label">Hình ảnh:</label>
							 <div class="col-sm-10">
							 <input type="file" name="picture" class="form-control" placeholder="Chọn ảnh" />
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
