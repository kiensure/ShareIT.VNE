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
	                  	  	  <h4><i class="fa fa-angle-right"></i> Bài viết</h4>
	                  	  	  <h4>
	                  	  	  	<a class="" href="<%=request.getContextPath()%>/admin/news/add">
		                          <i class="fa fa-plus-square-o"></i>
		                          <span>Thêm</span>
		                      	</a>
	                  	  	  </h4>
	                  	  	  <%
	                  	  	  	if("1".equals(request.getParameter("msg"))){
	                  	  	  		out.print("<p style='margin: 10px;color: green; font-weight: bold; font-size: 20px'>Thêm tin thành công</p>");
	                  	  	  	}
	                  	  	  	if("2".equals(request.getParameter("msg"))){
	                  	  	  		out.print("<p style='margin: 10px;color: green; font-weight: bold; font-size: 20px'>Sửa tin thành công</p>");
	                  	  	  	}
		                  	  	if("3".equals(request.getParameter("msg"))){
	                  	  	  		out.print("<p style='margin: 10px;color: green; font-weight: bold; font-size: 20px'>Xoá tin thành công</p>");
	                  	  	  	}
		                  	  	if("1".equals(request.getParameter("err"))){
	                  	  	  		out.print("<p style='margin: 10px;color: red; font-weight: bold; font-size: 20px'>Xoá tin thất bại</p>");
	                  	  	  	}
	                  	  	  %>
	                  	  	  
	                  	  	  <form action="javascrip:void(0)" method="post">
		                  	  	  <i style="font-size: 2em; padding: 10px" class="fa fa-search"></i>
		                  	  	  <input id="nameS" name="name" style="width: 300px;" type="text" id="searchInput" onkeyup="myFunction()" placeholder="Tên tin..">
		                  	  	  <label>Ngày đăng: </label>
		                  	  	  <input id="dateS" name="date" style="width: 300px" type="date" id="dateSearch" >
		                  	  	  <label>Trạng thái: </label>
		                  	  	  <select id="statusS" name="status">
		                  	  	  	<option value="2">Tất cả</option>
		                  	  	  	<option value="0">Ẩn</option>
		                  	  	  	<option value="1">Kích hoạt</option>
		                  	  	  </select>
		                  	  	  <input onclick="return search()" type="submit" value="Tìm kiếm">
	                  	  	  </form>
	                  	  	  <script type="text/javascript">
							 // Biến dùng kiểm tra nếu đang gửi ajax thì ko thực hiện gửi thêm
							    var is_busy = false;
							         
							    // Biến lưu trữ trang hiện tại
							    var page = 1;
							     
							    // Biến lưu trữ rạng thái phân trang 
							    var stopped = false;
							     
							    $(document).ready(function()
							    {    
							        // Khi kéo scroll thì xử lý
							        $(window).scroll(function() 
							        {
							            // Element append nội dung
							            $element = $('#tbodysearch');
							             
							            // ELement hiển thị chữ loadding
							            $loadding = $('#loadding');
							             
							            // Nếu màn hình đang ở dưới cuối thẻ thì thực hiện ajax
							            if($(window).scrollTop() + $(window).height() >= $element.height()) 
							            {
							                // Nếu đang gửi ajax thì ngưng
							                if (is_busy == true){
							                    return false;
							                }
							                 
							                // Nếu hết dữ liệu thì ngưng
							                if (stopped == true){
							                    return false;
							                }
							                 
							                // Thiết lập đang gửi ajax
							                is_busy = true;
							                 
							                // Tăng số trang lên 1
							                page++;
							                 
							                // Hiển thị loadding
							                $loadding.removeClass('hidden');
							                 
							                // Gửi Ajax
							                $.ajax(
							                {
							                    type        : 'post',
							                    dataType    : 'text',
							                    url         : '<%=request.getContextPath()%>/admin/search',
							                    data        : {page : page},
							                    success     : function (result)
							                    {
							                        $element.append(result);
							                    }
							                })
							                .always(function()
							                {
							                    // Sau khi thực hiện xong ajax thì ẩn hidden và cho trạng thái gửi ajax = false
							                    $loadding.addClass('hidden');
							                    is_busy = false;
							                });
							                return false;
							            }
							        });
							    });
							    </script>
	                  	  	  <script type="text/javascript">
	                  	  		function search(){
		                  	  		var name = $("#nameS").val();
		                  	  		var date = $("#dateS").val();
		                  	  		var status = $("#statusS").val();
			                  	  	$.ajax({
										// trang chuyển đến
										url: '<%=request.getContextPath()%>/admin/search', 
										type: 'POST', // phương thức: post/get
										cache: false,
										data: {
												//Dữ liệu gửi đi -> key:value
												aname: name,
												adate: date,
												astatus: status
												},
										success: function(data){
											// Xử lý thành công
											$("#pagination").css("display","none");
											$("#formgopage").css("display","none");
											$("#tbodysearch").html(data);
										},
										error: function (){
										// Xử lý nếu có lỗi
										alert("Có lỗi trong quá trình xử lý!");
										}
									});
		                  	  		
		                  	  	  }
	                  	  	  	
	                  	  	  </script>
	                  	  	  <hr>
	                  	  	  <%
	                  	  	  	User objUserLogin = (User)session.getAttribute("userLogin");
	                  	  	  %>
		                      <table class="table" id="myTable">
		                          <thead>
		                          <tr>
		                              <th style="width:4%; text-align: center;">ID</th>
		                              <th>Tên bài viết</th>
		                              <th style="width:10%; text-align: center;">Hình ảnh</th>
		                              <th style="width:8%; text-align: center;">Ngày tạo</th>
		                              <th style="width:8%">Danh mục</th>
		                              <th style="width:8%">Slide</th>
		                              <th style="width:8%">Trạng thái</th>
		                              <th style="width:8%">Nổi bật</th>
		                              <th style="width:12%; text-align: center;">Chức năng</th>
		                          </tr>
		                          </thead>
		                          <tbody id="tbodysearch">
		                          <%
		                          	if(request.getAttribute("list")!=null){
		                          		@SuppressWarnings("unchecked")
		                          		ArrayList<News> listNews = (ArrayList<News>)request.getAttribute("list");
		                         		for(News objNews : listNews){
		                          %>
		                          <tr>
		                              <td style="text-align: center;"><%=objNews.getIdNews() %></td>
		                              <td><a target="_blank" href="<%=request.getContextPath()%>/detail?nid=<%=objNews.getIdNews()%>"><%=objNews.getName() %></a></td>
		                              <td style="text-align: center;">
		                              	<%
											if(!"".equals(objNews.getPicture())){
										%>
										<img style="width:100px; height: 60px;border-radius:4px;" src="<%=request.getContextPath()%>/files/<%=objNews.getPicture()%>" />
										<%}else{ %>
										<img style="width:100px; height: 60px;border-radius:4px;" src="<%=request.getContextPath() %>/templates/admin/assets/img/no-img.png" alt="" />
										<%} %>
		                              </td>
		                              <td style="text-align: center;"><%=DayFormat.fD(objNews.getDate_Create()) %></td>
		                              <td><%=objNews.getCatName() %></td>
		                              <td id="isSlide-<%=objNews.getIdNews()%>">
		                              	<%
		                              		String checked="";
		                              		if(objNews.isIs_Slide()){
		                              			checked = "checked";
		                              		}
		                              	%>
		                              	<a onclick="return activeSlide(<%=objNews.getIdNews() %>, <%=objNews.isIs_Slide() %>)" <%if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 ){out.print("class='noclick'");} %> style="margin-left: 15px" href="javascript:void(0)">
		                              		<input id="checkSlide" <%=checked %> type="checkbox" />
		                              	</a>
		                              </td>
		                              <td id="tin-<%=objNews.getIdNews()%>" >
		                              	<a onclick="return active(<%=objNews.getIdNews()%>, <%=objNews.isActive()%>)" <%if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 ){out.print("class='noclick'");} %> style="margin-left: 15px" href="javascript:void(0)" title="" >
		                              		<%
		                              		if(objNews.isActive()){
		                              		%>
		                              		<i style="font-size: 2em; color: green;" class="fa fa-eye fa-2"></i>
		                              		<%}else{ %>
		                              		<i style="font-size: 2em; color: red;" class="fa fa-eye-slash"></i>
		                              		<%} %>
		                              	</a>
		                              </td>
		                              <td id="hotnews-<%=objNews.getIdNews()%>" >
		                              	<a onclick="return activeHotNews(<%=objNews.getIdNews()%>, <%=objNews.isHotNew()%>)" <%if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 ){out.print("class='noclick'");} %> style="margin-left: 15px" href="javascript:void(0)" title="" >
		                              		<%
		                              		if(objNews.isHotNew()){
		                              		%>
		                              		<i style="font-size: 2em; color: green;" class="fa fa-check"></i>
		                              		<%}else{ %>
		                              		<i style="font-size: 2em; color: red;" class="fa fa-times"></i>
		                              		<%} %>
		                              	</a>
		                              </td>
		                              <td><a <%if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 && objUserLogin.getIdUser() != objNews.getIdUser()){out.print("class='noclick'");} %> style="text-align: center;" href="<%=request.getContextPath() %>/admin/news/edit?nid=<%=objNews.getIdNews()%>">
		                              		<i <%if(objUserLogin.getIdUser() != objNews.getIdUser() && objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2){out.print("style='font-size: 1.5em ;color: red;' ");}else{out.print("style='font-size: 1.5em;'");} %> class="fa fa-wrench"></i> Sửa</a> &nbsp;||&nbsp; 
		                              	<a <%if(objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2 && objUserLogin.getIdUser() != objNews.getIdUser()){out.print("class='noclick'");} %> style="text-align: center;" href="<%=request.getContextPath() %>/admin/news/del?nid=<%=objNews.getIdNews()%>" onclick="return confirmAction()">
		                              		<i <%if(objUserLogin.getIdUser() != objNews.getIdUser() && objUserLogin.getIdTypeUser() != 1 && objUserLogin.getIdTypeUser() != 2){out.print("style='font-size: 1.5em ;color: red;' ");}else{out.print("style='font-size: 1.5em;'");} %> class="fa fa-trash-o"></i> Xóa</a>
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
								
								function activeHotNews(id, ishotnews){
									$.ajax({
										// trang chuyển đến
										url: '<%=request.getContextPath()%>/admin/news', 
										type: 'POST', // phương thức: post/get
										cache: false,
										data: {
												//Dữ liệu gửi đi -> key:value
												aidhotnews: id,
												aishotnews: ishotnews
												},
										success: function(data){
											// Xử lý thành công
											$("#hotnews-"+id).html(data);
										},
										error: function (){
										// Xử lý nếu có lỗi
										alert("Có lỗi trong quá trình xử lý!");
										}
									});
								}
								
								function activeSlide(id, islide){
									$.ajax({
										// trang chuyển đến
										url: '<%=request.getContextPath()%>/admin/news', 
										type: 'POST', // phương thức: post/get
										cache: false,
										data: {
												//Dữ liệu gửi đi -> key:value
												aidsilde: id,
												aisSlide: islide
												},
										success: function(data){
											// Xử lý thành công
											$("#isSlide-"+id).html(data);
										},
										error: function (){
										// Xử lý nếu có lỗi
										alert("Có lỗi trong quá trình xử lý!");
										}
									});
								}
								
							</script>
		                      <div class="text-center">
		                      			<%
			                      			int sumPage = (Integer)request.getAttribute("sumPage");
											int current_page = (Integer)request.getAttribute("current_page");
											String active = "";
											String disable = "";
		                      			%>
										<ul class="pagination" id="pagination">
										<%
											if(current_page == 1){
										%>
											<li class="noclick"><span><i class="fa fa-backward"></i></span></li>
										<%}else{%>
											<li><a href="<%=request.getContextPath() %>/admin/news?page=<%=1%>" rel="previous" > <i class="fa fa-fast-backward"></i></a></li>
											<li><a href="<%=request.getContextPath() %>/admin/news?page=<%=current_page - 1%>" rel="previous" > <i class="fa fa-backward"></i></a></li>
										<%}%>
										<%
											int begin = current_page - 2;
											int end = current_page + 2;
											if(begin < 1){
												begin = 1;
											}
											if(end > sumPage){
												end = sumPage;
											}
											for(int i = begin; i <= end; i++){
												if(current_page == i){
													active = "class='active'";
												} else {
													active = "";
												}
										%>
											<li <%=active %>>
												<a href="<%=request.getContextPath()%>/admin/news?page=<%=i%>" ><%=i %></a>
											</li>
										<%} %>
										<%
											if(current_page == sumPage){
										%>
											<li class="noclick" ><span><i class="fa fa-forward"></i></span></li>
										<%}else{ %>
											<li><a href="<%=request.getContextPath() %>/admin/news?page=<%=current_page + 1%>" rel="next" > <i class="fa fa-forward"></i></a></li>
											<li><a href="<%=request.getContextPath() %>/admin/news?page=<%=sumPage%>" rel="next" > <i class="fa fa-fast-forward"></i></a></li>
										<%} %>
										</ul>
										<form method="get" id="formgopage">
											<label style="color: green;font-weight: bold;">Nhập trang muốn đến:</label>
											<input id="pagew" style="width: 40px" type="text" value="" />
											<a id="go" href="#" onclick="return test()"><i style="font-size: 1.2em" class="fa fa-arrow-right"></i></a>
											<span id="noti" style="display: none; color: red;font-weight: bold;">Không có trang này</span>
										</form>
										
										<script type="text/javascript">
											function test(){
													var page = $("#pagew").val();
													if(page > <%=sumPage%> || page < 1 || isNaN(page)){
														$("#noti").css("display","block");
														return false;
													}
													var url = "<%=request.getContextPath() %>/admin/news?page=";
													var d = url + page;
													$("#go").attr("href",d);
											}
										</script>
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
