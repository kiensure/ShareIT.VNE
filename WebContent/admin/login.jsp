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

    <title>DASHGUM - Bootstrap Admin Template</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath() %>/templates/admin/assets/css/bootstrap1.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=request.getContextPath() %>/templates/admin/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath() %>/templates/admin/assets/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/templates/admin/assets/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

	  <div id="login-page">
	  	<div class="container">
	  		  
		      <form class="form-login" action="<%=request.getContextPath()%>/admin/login" method="post">
		        <h2 class="form-login-heading">sign in now</h2>
		        <%
			 		String usename = request.getParameter("username");
			 		String password = request.getParameter("password");
			 		if("1".equals(request.getParameter("msg")))
			 			out.print("<p style=\"color:red; text-align: center; margin-top: 10px; font-weight:bold\">Sai tên đăng nhập hoặc mật khẩu</p>");
			 		if("0".equals(request.getParameter("err")))
			 			out.print("<p style=\"color:red; text-align: center; margin-top: 10px; font-weight:bold\">Bạn phải đăng nhập để sử dụng chức năng này</p>");
		 	    %>
		        <div class="login-wrap">
		            <input name="username" type="text" class="form-control" placeholder="User ID" autofocus value="<%if(usename != null) {out.print(usename);} %>">
		            <br>
		            <input name="password" type="password" class="form-control" placeholder="Password" value="<%if(usename != null) {out.print(password);} %>">
		            <label style="margin-top: 10px">
		            </label>
		            <button class="btn btn-theme btn-block" type="submit"><i class="fa fa-lock"></i> SIGN IN</button>
		            <hr>
		            
		            <div class="login-social-link centered">
		
		        </div>
		
		          <!-- Modal -->
		          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Forgot Password ?</h4>
		                      </div>
		                      <div class="modal-body">
		                          <p>Enter your e-mail address below to reset your password.</p>
		                          <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">
		
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
		                          <button class="btn btn-theme" type="button">Submit</button>
		                      </div>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		
		      </form>	  	
	  	
	  	</div>
	  </div>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/bootstrap.min.js"></script>

    <!--BACKSTRETCH-->
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.backstretch.min.js"></script>
    <script>
        $.backstretch(["<%=request.getContextPath()%>/templates/admin/assets/img/login-bg1.jpg","<%=request.getContextPath()%>/templates/admin/assets/img/login-bg2.jpg"], {duration: 5000, fade: 750,speed: 1500});
    </script>

  </body>
</html>
