<%@page import="model.bean.UserType"%>
<%@page import="model.bean.Category"%>
<%@page import="library.DayFormat"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
<!--sidebar start-->
<%@include file="/templates/admin/inc/left_bar.jsp"%>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
<!--main content start-->
<section id="main-content">
	<div class="wrapper">
		<h3>
			<i class="fa fa-angle-right"></i> Trang quản lý
		</h3>

		<!-- BASIC FORM ELELEMNTS -->
		<div class="row mt">
			<div class="col-lg-12">
				<div class="form-panel">
					<h4 class="mb">
						<i class="fa fa-angle-right"></i> Thêm quản trị viên
					</h4>
					<%
						if("0".equals(request.getParameter("err"))){
							out.print("<p style=\"color: red; margin-top: 10px; font-weight: bold;\">Tên người dùng vượt quá số ký tự cho phép</p>");
						}
						if("1".equals(request.getParameter("err"))){
							out.print("<p style=\"color: red; margin-top: 10px; font-weight: bold;\">Xác nhận password không trùng khớp</p>");
						}
						if("2".equals(request.getParameter("err"))){
							out.print("<p style=\"color: red; margin-top: 10px; font-weight: bold;\">Xác nhận email không trùng khớp</p>");
						}
						if("3".equals(request.getParameter("err"))){
							out.print("<p style=\"color: red; margin-top: 10px; font-weight: bold;\">Fullname vượt quá số ký tự cho phép</p>");
						}
						if("5".equals(request.getParameter("err"))){
							out.print("<p style=\"color: red; margin-top: 10px; font-weight: bold;\">Người dùng đã tồn tại</p>");
						}
					%>
					<div class="content">
						<%
							String name = request.getParameter("username");
							String fullname = request.getParameter("fullname");
							String password = request.getParameter("password");
							String repassword = request.getParameter("repassword");
							String email = request.getParameter("email");
							String reemail = request.getParameter("reemail");
						%>
						<form action="<%=request.getContextPath() %>/admin/user/add" method="post" id="form">
							<input type="hidden" name="_token"
								value="MOaYyUrvRnBsirDUE1UafJLNLyZleKA4qOJZfg4G">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label>Tên đăng nhập (*)</label> <input type="text"
											name="username" value="<%if(name != null){out.print(name);} %>"
											class="form-control border-input" placeholder="abcxyz">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label for="fullname">Nhập tên đầy đủ</label> <input
											type="text" class="form-control border-input" value="<%if(fullname != null){out.print(fullname);} %>"
											name="fullname" id="fullname" placeholder="Nguyễn Văn A">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label for="capbac">Cấp bậc</label> 
										<select class="form-control border-input" name="capbac" id="capbac" style="width: 100%;">
											<%
												if(request.getAttribute("listUserType") != null){
													ArrayList<UserType> listUserTypes = (ArrayList<UserType>)request.getAttribute("listUserType");
													for(UserType userType : listUserTypes){
											%>
											<option value="<%=userType.getIdType()%>"><%=userType.getNameType() %></option>
											<%}} %>
										</select>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="password">Nhập mật khẩu</label> <input
											type="password" class="form-control border-input"
											name="password" id="password" placeholder="******" value="<%if(password != null){out.print(password);} %>">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="password">Nhập lại mật khẩu</label> <input
											type="password" class="form-control border-input" value="<%if(repassword != null){out.print(repassword);} %>"
											name="repassword" id="repassword" placeholder="******">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="email">Nhập email</label> <input type="email"
											class="form-control border-input" name="email" id="email"
											value="<%if(email != null){out.print(email);} %>" placeholder="abcxyz@gmail.com">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="reemail">Nhập lại email</label> <input
											type="email" class="form-control border-input" name="reemail"
											id="reemail" value="<%if(reemail != null){out.print(reemail);} %>"
											placeholder="abcxyz@gmail.com">
									</div>
								</div>
							</div>

							<div class="text-center">
								<input type="submit" class="btn btn-info btn-fill btn-wd"
									value="Thực hiện" />
							</div>
							<div class="clearfix"></div>
						</form>
					</div>
				</div>
			</div>
			<!-- col-lg-12-->
		</div>
		<!-- /row -->

	</div>
	<%@include file="/templates/admin/inc/footer.jsp" %>
</section>

<!--main content end-->
</section>

<!-- js placed at the end of the document so the pages load faster -->
<script
	src="<%=request.getContextPath()%>/templates/admin/assets/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript"
	src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script
	src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery.scrollTo.min.js"></script>
<script
	src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery.nicescroll.js"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery.sparkline.js"></script>


<!--common script for all pages-->
<script
	src="<%=request.getContextPath()%>/templates/admin/assets/js/common-scripts.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/templates/admin/assets/js/gritter/js/jquery.gritter.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/templates/admin/assets/js/gritter-conf.js"></script>

</body>
</html>
