<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>SHARE-IT</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/reset.css" />
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,900,300italic,400italic,600italic,700italic,900italic' rel='stylesheet' type='text/css' />
  <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/icomoon.css" />
  <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/superfish.css" />
  <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/style.css" />
  <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/styleCat1.css" />
  <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/responsive.css" />
  <script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery.js" ></script>
	<!--[if lt IE 9]>
	<link rel="stylesheet" type="text/css" href="css/ie.css">
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<script src="js/PIE_IE678.js"></script>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
	<![endif]-->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/superfish.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery.carousel-6.2.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jflickrfeed.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/tweetable.jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery.timeago.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery.validate.js"></script>
	<style>
			.error{
				color: red;
				font-size: 15px;
			}
		</style>
	<script type="text/javascript">
	$(document).ready(function (){
		$('#comment').validate({
			rules:{
				hoten:{ 
					required: true
				},
				email:{
					required: true,
					email: true
				},
				website:{
					required: true,
				},
				content:{
					required: true,
					minlength: 10
				}
			},
			messages:{
				hoten:{ 
					required: "Chưa nhập họ tên"
				},
				email:{
					required: "Chưa nhập email",
					email: "Email chưa đúng định dạng"
				},
				website:{
					required: "Chưa nhập website",
				},
				content:{
					required: "Chưa nhập nội dung",
					minlength: "Nội dung phải ít nhất 10 ký tự"
				}
			}
	});
	});
	$(document).ready(function (){
		$('#formreply').validate({
			rules:{
				hotenr:{ 
					required: true
				},
				emailr:{
					required: true,
					email: true
				},
				websiter:{
					required: true,
					url: true
				},
				contentr:{
					required: true,
					minlength: 10
				}
			},
			messages:{
				hotenr:{ 
					required: "Chưa nhập họ tên"
				},
				emailr:{
					required: "Chưa nhập email",
					email: "Email chưa đúng định dạng"
				},
				websiter:{
					required: "Chưa nhập website",
					url: "Trang web chưa đúng định dạng"
				},
				contentr:{
					required: "Chưa nhập nội dung",
					minlength: "Nội dung phải ít nhất 10 ký tự"
				}
			}
	});
	});
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>