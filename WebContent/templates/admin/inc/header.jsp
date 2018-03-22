<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>SHAREIT ADMIN</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath() %>/templates/admin/assets/css/reset.css" rel="stylesheet">
  	<link href="<%=request.getContextPath() %>/templates/admin/assets/css/bootstrap1.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=request.getContextPath() %>/templates/admin/assets/font-awesome1/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/admin/assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/admin/assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/admin/assets/lineicons/style.css">    
    
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath() %>/templates/admin/assets/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/templates/admin/assets/css/style-responsive.css" rel="stylesheet">

	<link type="text/css" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet">
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
	
	<script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/chart-master/Chart.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/ckfinder/ckfinder.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.validate.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <style>
			.error{
				color: red;
				font-size: 15px;
			}
		</style>
  <script type="text/javascript">
			$(document).ready(function (){
				$('#form').validate({
					ignore: [],
					rules:{
						username:{ 
							required: true
						},
						password:{
							required: true,
							minlength: 6
						},
						repassword:{
							required: true,
							minlength: 6
						},
						email:{
							required: true,
							email: true
						},
						reemail:{
							required: true,
							email: true
						},
						fullname:{
							required: true,
						},
						tentin:{
							required: true
						},
						tenads:{
							required: true
						},
						link:{
							required: true
						},
						tendanhmuc:{
							required: true
						},
						mota:{
							required: function(textarea) {
								  CKEDITOR.instances[textarea.id].updateElement(); // update textarea
								  var editorcontent = textarea.value.replace(/<[^>]*>/gi, ''); // strip tags
								  return editorcontent.length === 0;
								},
						},
						chitiet:{
							required: function(textarea) {
								  CKEDITOR.instances[textarea.id].updateElement(); // update textarea
								  var editorcontent = textarea.value.replace(/<[^>]*>/gi, ''); // strip tags
								  return editorcontent.length === 0;
								},
						}
					},
					messages:{
						username:{ 
							required: "Chưa nhập username"
						},
						password:{
							required: "Chưa nhập password",
							minlength: "Password phải ít nhất 6 ký tự"
						},
						repassword:{
							required: "Chưa xác nhận password",
							minlength: "Xác nhận password phải ít nhất 6 ký tự"
						},
						email:{
							required: "Chưa nhập email",
							email: "Email không đúng định dạng"
						},
						reemail:{
							required: "Chưa xác nhận email",
							email: "Email không đúng định dạng"
						},
						fullname:{
							required: "Chưa nhập Fullname",
						},
						tentin:{
							required: "Chưa nhập tên tin"
						},
						tenads:{
							required: "Chưa nhập tên quảng cáo"
						},
						link:{
							required: "Chưa nhập link"
						},
						tendanhmuc:{
							required: "Chưa nhập tên danh mục"
						},
						mota:{
							required: "Chưa nhập mô tả"
						},
						chitiet:{
							required: "Chưa nhập chi tiết"
						}
					}
			});
		});
		</script>
  <body>

  <section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
              </div>
            <!--logo start-->
            <a target="_blank" href="<%=request.getContextPath() %>/" class="logo"><b>SHARE IT</b></a>
            <!--logo end-->
            <div class="top-menu">
            	<ul class="nav pull-right top-menu">
                    <li><a class="logout" href="<%=request.getContextPath()%>/admin/logout">Logout</a></li>
            	</ul>
            </div>
        </header>
      <!--header end-->