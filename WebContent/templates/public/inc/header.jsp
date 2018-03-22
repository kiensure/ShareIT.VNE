<%@page import="library.StringUntil"%>
<%@page import="model.dao.AdvertisementsDao"%>
<%@page import="model.bean.Advertisements"%>
<%@page import="model.bean.News"%>
<%@page import="library.DisplayMenuHTML"%>
<%@page import="model.dao.CategoryDao"%>
<%@page import="model.dao.NewsDao"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header id="header-page">
	<%
		ArrayList<Advertisements> listAds = (new AdvertisementsDao().getItems());
	%>
  	<div class="top-header">
  		<div class="container">
	  		<div id="logo">
	  			<a href="<%=request.getContextPath()%>/"><img width="130px" height="26px" src="<%=request.getContextPath() %>/templates/public/images/logoit.png" alt="logo" /></a>
	  		</div>
	  		<nav class="pull-right">
	  			<%
		  			CategoryDao categoryDao = new CategoryDao();
		  			ArrayList<Category> listCat = categoryDao.getItems();
	  			%>
				<%DisplayMenuHTML.show(request,out, listCat, 0, "", 0);%>
	  		</nav>
	  		<div class="clearfix"></div>
  		</div>
  		<!-- container -->
  	</div>
  	<!-- top-header -->
  	<div class="bottom-header">
  	<div class="container">
  		<div class="top-news pull-left">
  			<span>BÀI VIẾT MỚI</span>
  			<div class="list_carousel">
			  <ul class="carousel-1">
			  <%
			  	 ArrayList<News> listNewsNew = (new NewsDao().getItemsNew());
			     for(News objNewsNewTop : listNewsNew){
			  %>
			    <li><a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(objNewsNewTop.getName()) %>-<%=objNewsNewTop.getIdNews()%>.html"><%=objNewsNewTop.getName() %></a></li>
			  <%} %>
			  </ul>
			  <div class="clearfix"></div>
			</div>
			<!-- list_carousel -->
  		</div>
  		<!-- top news -->
		<div class="search-box pull-right">
			<%
				if(request.getParameter("search-text")!=null){
					String keyword = request.getParameter("search-text");
				}
			%>
			<form action="<%=request.getContextPath() %>/search" method="post" >
				<div class="form-group">					
					<input type="text" name="search-text" class="form-control" value="" placeholder="Nhập nội dung cần tìm ..."/>
				</div>
				<button type="submit"><span class="icon-search"></span></button>
			</form>
		</div>
		</div>
  		<!-- container -->
  	</div>
  	<!-- bottom-header -->
</header>
<!-- header-page -->