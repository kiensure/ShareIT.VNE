<%@page import="library.StringUntil"%>
<%@page import="java.util.Random"%>
<%@page import="model.bean.Advertisements"%>
<%@page import="library.DayFormat"%>
<%@page import="model.bean.User"%>
<%@page import="model.dao.UserDao"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/templates/public/inc/head.jsp" %>
<body class="kp-home">
<%@include file="/templates/public/inc/header.jsp" %>
<!-- header-page -->
<div id="content" class="container clearfix">
	<%
		Random rd = new Random();
		int randAdsBanTop = 0;
		int randAdsBanBottom = 0;
		while(true){
			randAdsBanTop = rd.nextInt(listAds.size());
			randAdsBanBottom = rd.nextInt(listAds.size());
			if(listAds.get(randAdsBanTop).getLocation() == 1 && listAds.get(randAdsBanBottom).getLocation() == 1){
				break;
			}
		}
	%>
	<div style="margin-bottom: 10px; " class="banner"><a target="_blank" href="<%=listAds.get(randAdsBanTop).getLink()%>"><img style="width: 1200px; height: 280px" src="<%=request.getContextPath() %>/files/<%=listAds.get(randAdsBanTop).getPicture()%>"/></a></div>
	<div class="main-top">
		<div class="col-area-1 pull-left">
			<div class="widget widget-news">
				<div class="widget-content">
					<%
						
						int randAdsLeft = 0; 
						while(true){
							randAdsLeft = rd.nextInt(listAds.size());
							if(listAds.get(randAdsLeft).getLocation() == 2){
								break;
							}
						}
					%>
					<a target="_blank" href="http://vinaenter.edu.vn"><img style="height: 430px" alt="" src="<%=request.getContextPath() %>/files/<%=listAds.get(randAdsLeft).getPicture()%>"></a>
				</div>
				<!-- widget content -->
			</div>
		</div>
		<!-- col-area-1 -->
		<div class="col-area-2 pull-left">
			<div class="widget top-slide">
				<div class="list_carousel">
				  <ul class="carousel-2">
				  	<%
						ArrayList<News> listNewsSlide = (ArrayList<News>)request.getAttribute("listNewsSlide");
				  		for(News objNewsSlide : listNewsSlide){
				  	%>
				    <li class="thumb-large" style="margin-right: 10px; margin-top: 10px;">
				    	<div class="item">
				    		<a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(objNewsSlide.getName()) %>-<%=objNewsSlide.getIdNews()%>.html">
				    			<%
				    				if(!"".equals(objNewsSlide.getPicture())){
				    			%>
				    			<img style="height: 250px" src="<%=request.getContextPath() %>/files/<%=objNewsSlide.getPicture() %>" alt="" />
				    			<%}else{ %>
				    			<img style="height: 250px" src="<%=request.getContextPath() %>/templates/public/images/no-img.png" alt="" />
				    			<%} %>
				    			<div class="mask">
				    				<span class="icon-link"></span>
				    			</div>
				    		</a>
				    		<div class="item-content">
				    			<header class="header-item">
					    			<span class="icon-image pull-left"></span>
					    			<div class="item-right">
					    				<h4 ><a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(objNewsSlide.getName()) %>-<%=objNewsSlide.getIdNews()%>.html"><%=objNewsSlide.getName() %></a></h4>
					    				<ul class="list-inline kp-metadata clearfix">
											<li class="kp-view"><span class="icon-calendar pull-left"></span><%=DayFormat.fD(objNewsSlide.getDate_Create()) %></li>
										</ul>
					    			</div>
					    		</header>
					    		<p><%=objNewsSlide.getPreview()%></p>
				    		</div>
				    	</div>
				    	<!-- item -->
				    </li>
				    <%} %>
				  </ul>
				  <a id="prev2" class="prev icon-arrow-left" href="#"></a>
				  <a id="next2" class="next icon-arrow-right" href="#"></a>
				</div>
			</div>
			<!-- top slide -->
		</div>
		<!-- col-area-2 -->
		<div class="clearfix"></div>
	</div>
	<div class="widget newsletter clearfix">
		<i class="icon-eye pull-left"></i>
		<h2 class="pull-left"><span>Cổng chia sẻ thông tin công nghệ </span>Cập nhật hằng ngày những tin tức mới nhất về thế giới công nghệ</h2>
		<!-- newsletter-form -->
	</div>
	<!-- newsletter -->
	<!-- main-top -->
	<div id="main-content">
		<%
			ArrayList<Category> listCatIndexPublic = (ArrayList<Category>)request.getAttribute("listCatIndexPublic");
		%>
		<div class="col-area-2 pull-left">
			<%
				for(Category objCatLeft : listCatIndexPublic){
			%>
			<div class="widget widget-tab-news clearfix">
				<div class="pull-left">
					<h2 class="widget-titleIndex clearfix">
						<%=objCatLeft.getName() %>
						<a href="<%=request.getContextPath()%>/danh-muc/<%=StringUntil.createSlugURL(objCatLeft.getName())%>-<%=objCatLeft.getIdCat()%>.html">Tất cả tin</a>
						<span></span>
					</h2>
					<!-- widget-title-2 -->
					<ul>
						<%
							ArrayList<News> listNewsByIDCat = (ArrayList<News>)request.getAttribute("listNewsByIDCat"+objCatLeft.getIdCat());
					  		for(News objNewsById : listNewsByIDCat){
						%>
					    <li>
						    <a href="#tabs-<%=objNewsById.getIdNews()%>">
							    <div class="item clearfix">
							    	<%
					    				if(!"".equals(objNewsById.getPicture())){
					    			%>
					    			<img src="<%=request.getContextPath() %>/files/<%=objNewsById.getPicture() %>" alt="" class="pull-left"/>
					    			<%}else{ %>
					    			<img src="<%=request.getContextPath() %>/templates/public/images/no-img.png" alt="" class="pull-left"/>
					    			<%} %>
							    
									<div class="item-right">
										<h4><%=objNewsById.getName() %></h4>
										<ul class="list-inline kp-metadata clearfix">
											<li class="kp-time"><%=DayFormat.fD(objNewsById.getDate_Create()) %></li>
											<li class="kp-view"><span class="icon-eye pull-left"></span><%=objNewsById.getViews() %></li>
										</ul>
									</div>
								</div>
							</a>
						</li>
						<%} %>
					</ul>
				</div>
				<!-- pull left -->
				<div style="width: 630px" class="pull-right">
					<%
					  	for(News objNewsByIdDetail : listNewsByIDCat){
					%>
					<div id="tabs-<%=objNewsByIdDetail.getIdNews()%>">
						<div class="item clearfix">
				    		<a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(objNewsByIdDetail.getName()) %>-<%=objNewsByIdDetail.getIdNews()%>.html">
				    			<%
					    			if(!"".equals(objNewsByIdDetail.getPicture())){
					    		%>
					    		<img src="<%=request.getContextPath() %>/files/<%=objNewsByIdDetail.getPicture() %>" alt="" />
					    		<%}else{ %>
					    		<img src="<%=request.getContextPath() %>/templates/public/images/no-img.png" alt="" />
					    		<%} %>
				    			<div class="mask">
				    				<span class="icon-link"></span>
				    			</div>
				    		</a>
				    		<div class="item-content">
				    			<header class="header-item">
					    			<span class="icon-image pull-left"></span>
					    			<div class="item-right">
					    				<h4><a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(objNewsByIdDetail.getName()) %>-<%=objNewsByIdDetail.getIdNews()%>.html"><%=objNewsByIdDetail.getName() %></a></h4>
					    				<ul class="list-inline kp-metadata clearfix">
											<li class="kp-view"><span class="icon-calendar pull-left"></span><%=DayFormat.fD(objNewsByIdDetail.getDate_Create()) %></li>
										</ul>
					    			</div>
					    		</header>
					    		<p><%=objNewsByIdDetail.getPreview() %></p>
					    		<a href="<%=request.getContextPath() %>/tin-tuc/<%=StringUntil.createSlugURL(objNewsByIdDetail.getName()) %>-<%=objNewsByIdDetail.getIdNews()%>.html" class="read-more">Read more</a>
				    		</div>
				    	</div>
				    	<!-- item -->
					</div>
					<%} %>
					<!-- tab-1 -->
				</div>
				<!-- pull-right -->
			</div>
			<!-- widget-tab-news -->
			<%} %>
		</div>
		<!-- col-area-3 -->
	</div>
	<!-- main-content -->
	<div id="sidebar" class="pull-left">
		<div class="widget widget-list-images-news">
			<div class="accordion-defaul">
			  <h2 class="widget-title"><span class="icon-compose pull-left"></span>Hot nhất</h2>
			  <div>
			    <ul class="list-unstyled">
			    		<%
				    		ArrayList<News> listNewsHotRightBar = (ArrayList<News>)request.getAttribute("listNewsHot");
				    		for(News objNewsRecent : listNewsHotRightBar){
			    		%>
						<li>
							<div class="item clearfix">
								<a href="<%=request.getContextPath() %>/tin-tuc/<%=StringUntil.createSlugURL(objNewsRecent.getName()) %>-<%=objNewsRecent.getIdNews()%>.html" class="pull-left">
									<img style="width: 50px;height: 50px" src="<%=request.getContextPath() %>/files/<%=objNewsRecent.getPicture() %>" alt="" />
								</a>
								<div class="item-right">
									<h4><a style="text-align: justify;" href="<%=request.getContextPath() %>/tin-tuc/<%=StringUntil.createSlugURL(objNewsRecent.getName()) %>-<%=objNewsRecent.getIdNews()%>.html"><%=objNewsRecent.getName() %></a></h4>
									<ul class="list-inline kp-metadata clearfix">
										<li class="kp-time"><%DayFormat.fD(objNewsRecent.getDate_Create()); %></li>
										<li class="kp-view"><span class="icon-eye pull-left"></span><%=objNewsRecent.getViews() %></li>
									</ul>
								</div>
							</div>
						</li>
						<%} %>
				</ul>
			  </div>
			</div>
		</div>
		<!-- widget-list-news -->
		<div class="widget widget-facebook">
			<h2 class="widget-title"><span class="icon-thumbs-up2 pull-left"></span>LIKE US ON FACEBOOK</h2>

			<iframe src="//www.facebook.com/plugins/likebox.php?href=http%3A%2F%2Fwww.facebook.com%2Fkopatheme&amp;width=250&amp;height=258&amp;colorscheme=light&amp;show_faces=true&amp;header=false&amp;stream=false&amp;show_border=false" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:250px; height:258px;" allowtransparency="true"></iframe>
		</div>
		<!-- widget-facebook -->
	</div>
	<!-- sidebar -->
	<div style="margin-bottom: 10px; " class="banner"><a target="_blank" href="<%=listAds.get(randAdsBanBottom).getLink()%>"><img style="width: 1200px; height: 280px" src="<%=request.getContextPath() %>/files/<%=listAds.get(randAdsBanBottom).getPicture()%>"/></a></div>
</div>
<!-- container -->
<%@include file="/templates/public/inc/footer.jsp"%>