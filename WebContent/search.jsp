<%@page import="library.DayFormat"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/head.jsp" %>
<body class="kp-blog page-1-sidebar">
<%@include file="/templates/public/inc/header.jsp" %>
<!-- header-page -->
<div id="content" class="container clearfix">
	<div class="main-top">
		<div class="col-area-2 pull-left">
			<div class="widget newsletter clearfix">
				<div class="newsletter-form" >
					<%
						String keyword = request.getParameter("search-text");
					%>
					<form action="<%=request.getContextPath() %>/search" method="post" >
						<div class="form-group">
							<label for="d" class="hide">email</label>
							<input style="width: 830px; float: left;" type="text" name="search-text" value="<%if(keyword!=null){out.print(keyword);} %>" class="form-control" id="d" placeholder="" />
						</div>
						<input style="float: right;" type="submit" name="" value="TÌM KIẾM" />
					</form>
				</div>
				<!-- newsletter-form -->
			</div>
			<!-- newsletter -->
		</div>
		<!-- col-area-2 -->
		<div class="clearfix"></div>
	</div>
	<!-- main-top -->
	<div id="main-content">
		<div class="col-area-3 pull-left">
			<div class="kp-breadcrumb">
				<ol class="breadcrumb">
				  <li><a href="<%=request.getContextPath()%>/">Trang Chủ</a></li>
				  <li class="active">Tìm Kiếm</li>
				</ol>
				<span></span>
			</div>				
			<!-- breadcrumb -->
			<div class="list-posts clearfix">				
				<ul class="list-unstyled">
					<%
						ArrayList<News> listResult = (ArrayList<News>)request.getAttribute("listResult");
						for(News objNewsSearch : listResult){
					%>
					<li class="clearfix">
						<div class="item clearfix">
				    		<a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(objNewsSearch.getName()) %>-<%=objNewsSearch.getIdNews()%>.html"">
				    			<img src="<%=request.getContextPath() %>/files/<%=objNewsSearch.getPicture() %>" alt="" />
				    			<div class="mask">
				    				<span class="icon-link"></span>
				    			</div>
				    		</a>
				    		<div class="item-content">
				    			<header class="header-item">
					    			<span class="icon-image pull-left"></span>
					    			<div class="item-right">
					    				<h4><a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(objNewsSearch.getName()) %>-<%=objNewsSearch.getIdNews()%>.html"><%=objNewsSearch.getName() %></a></h4>
					    			</div>
					    			<div class="clearfix"></div>
					    			<ul class="list-inline kp-metadata clearfix">
										<li class="kp-view"><span class="icon-calendar pull-left"></span><%=DayFormat.fD(objNewsSearch.getDate_Create()) %></li>
									</ul>
					    		</header>
					    		<p><%=objNewsSearch.getPreview() %></p>
					    		<a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(objNewsSearch.getName()) %>-<%=objNewsSearch.getIdNews()%>.html" class="read-more">Read more</a>
				    		</div>
				    	</div>
				    	<!-- item -->
					</li>
					<%} %>
				</ul>
				<div class="clearfix"></div>
			</div>
			<!-- list-post -->
		</div>
		<!-- col-area-3 -->
	</div>
	<!-- main-content -->
	<%@include file="/templates/public/inc/sidebar.jsp" %>
</div>
<!-- container -->
<%@include file="/templates/public/inc/footer.jsp" %>