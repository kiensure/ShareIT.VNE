<%@page import="library.DayFormat"%>
<%@page import="model.dao.CommentDao"%>
<%@page import="model.bean.Comment"%>
<%@page import="model.dao.UserDao"%>
<%@page import="model.bean.User"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/templates/public/inc/head.jsp" %>
<body class="kp-single page-1-sidebar">
<%@include file="/templates/public/inc/header.jsp" %>
<!-- header-page -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script src="<%=request.getContextPath() %>/templates/public/js/superfish.js"></script>
<script src="<%=request.getContextPath() %>/templates/public/js/jquery.carousel-6.2.1.js"></script>
<script src="<%=request.getContextPath() %>/templates/public/js/jflickrfeed.min.js"></script>
<script src="<%=request.getContextPath() %>/templates/public/js/tweetable.jquery.js"></script>
<script src="<%=request.getContextPath() %>/templates/public/js/jquery.timeago.js"></script>
<script src="<%=request.getContextPath() %>/templates/public/js/jquery.form.js"></script>
<script src="<%=request.getContextPath() %>/templates/public/js/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath() %>/templates/public/js/custom.js"></script>
<div id="content" class="container clearfix">
	<div class="main-top">
		<div class="clearfix"></div>
	</div>
	<!-- main-top -->
	<div id="main-content">
		<div class="col-area-3 pull-left">
			<div class="kp-breadcrumb">
				<ol class="breadcrumb">
				  <li><a href="<%=request.getContextPath()%>/">Trang Chủ</a></li>
				  <li class="active">Liên hệ</li>
				</ol>
				<span></span>
			</div>	
			<!-- kp-breadcrumb -->
			<div class="page-content clearfix">
				<div class="kp-form-comment">
					<h2>ĐỂ LẠI TIN NHẮN</h2>
					<br/>
					<form action="javascript:void(0)" method="post" class="form-comment" id="contact" >
						<div class="row">
							<div class="col-9">
								<div class="form-group">
									<label>Họ và tên</label>
									<input type="text" id="s" name="hoten" class="form-control" placeholder="Họ và tên"/>
								</div>
								<div class="form-group">
									<label>Email</label>
									<input type="text" id="d" name="email" class="form-control" placeholder="Email" />
								</div>
								<div class="form-group">
									<label>Số điện thoại</label>
									<input type="text" id="f" name="phone" class="form-control" placeholder="Số điện thoại"/>
								</div>
								<div class="form-group">
									<label>Nội dung</label>
									<textarea id="a" name="content" class="form-control" placeholder="Nội dung"></textarea>
								</div>
								<input type="submit" value="GỬI" id="input-submit" class="btn"/>
							</div>
							<div style="clear: both;"></div>
							<div class="thong-bao"></div>
						</div>
					</form>
					<script type="text/javascript">
					  $(function(){
					    $("#contact").on('submit', function(){
					      var hoten = $( "input[name='hoten']").val();
					      var email = $( "input[name='email']").val();
					      var phone = $( "input[name='phone']").val();
					     // var _token = $( "input[name='_token']").val();
					      var content = $( "textarea[name='content']").val();
					      //$.ajaxSetup({
					      //  headers: {
					      //    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
					      //  }
					      //});
					      $.ajax({
					        url: "<%=request.getContextPath()%>/contact",
					        type: 'POST',
					        cache: false,
					        data: {
					          ahoten: hoten,
					          aemail: email,
					          aphone: phone,
					          acontent: content,
					          //token: _token
					        },
					        success: function(data){
					          $('.thong-bao').html(data);
					        },
					        error: function (){
					          alert('Có lỗi');
					        }
					      });   
					    });
					  });
					</script>
					<div id="response"></div>
				</div>
				<!-- kp-form-comment -->
			</div>
			<!-- list-post -->
			
		</div>
		<!-- col-area-3 -->
	</div>
	<!-- main-content -->
</div>
<!-- container -->
	<footer id="page-footer">
	<div class="container">
		<p class="copy-right pull-left"> Copyright 2013 - <span>Kopasoft</span>.  All Rights Reserved</p>
		<ul class="pull-right list-inline">
			<li><a href="#" class="icon-facebook"></a></li>
			<li><a href="#" class="icon-flickr"></a></li>
			<li><a href="#" class="icon-vimeo"></a></li>
			<li><a href="#" class="icon-dribbble"></a></li>
			<li><a href="#" class="icon-twitter"></a></li>
		</ul>
	</div>
</footer>
<!-- page footer -->
<span class="back-to-top icon-arrow-up"></span>

</body>
</html>