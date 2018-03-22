<%@page import="library.StringUntil"%>
<%@page import="library.DayFormat"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/public/inc/head.jsp"%>
<body class="kp-blog">
	<%@include file="/templates/public/inc/header.jsp"%>
	<!-- header-page -->
	<div id="content" class="container clearfix">
		<div class="main-top">
			<div class="clearfix"></div>
		</div>
		<!-- main-top -->
		<div id="main-content">
			<%
				ArrayList<News> listAllNews = (ArrayList<News>)request.getAttribute("listAllNews");
				Category objCat = (Category)request.getAttribute("objCat");
			%>
			<div class="col-area-4 pull-left" style="margin-right: 20px">
				<div class="widget popular-post">
					<h2 class="widget-title">
						<span class="icon-list pull-left"></span> Xem nhiều
					</h2>
					<ul class="list-unstyled">
						<%
							ArrayList<News> listMostViews = (ArrayList<News>)request.getAttribute("listMostViews");
							for(News newsMostView : listMostViews ){
						%>
						<li>
							<div class="item clearfix">
								<a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(newsMostView.getName()) %>-<%=newsMostView.getIdNews()%>.html"> <img src="<%=request.getContextPath() %>/files/<%=newsMostView.getPicture()%>" alt="" />
								</a>
								<div class="item-content">
									<header class="header-item">
										<div class="item-right">
											<h4>
												<a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(newsMostView.getName()) %>-<%=newsMostView.getIdNews()%>.html"><%=newsMostView.getName() %></a>
											</h4>
										</div>
									</header>
									<p><%=newsMostView.getPreview() %></p>
								</div>
							</div> <!-- item -->
						</li>
					<%} %>
					</ul>
				</div>
				<!-- widget-popular post -->

			</div>
			<div class="col-area-3 pull-left" style="margin-right: 0px">
				<header>
					<h1>
						<a id="mainContent_ctl00_Breadcumb1_hlTitle" href="/cong-nghe.html"><%=objCat.getName() %></a>
					</h1>
					<%
						ArrayList<Category> listCatChild = (ArrayList<Category>)request.getAttribute("listCat");
						for(Category objCatChild : listCatChild){
							if(objCatChild.getIdParent() == objCat.getIdCat()){
					%>
					<p>
						<a href="<%=request.getContextPath() %>/danh-muc/<%=StringUntil.createSlugURL(objCatChild.getName()) %>-<%=objCatChild.getIdCat()%>.html" title="<%=objCatChild.getName()%>"> <%=objCatChild.getName() %></a>
					</p>
					<%}} %>
				</header>
				<section class="featuredc">
					<!-- Tin Nổi bật Chuyên mục -->
					<%
						ArrayList<News> listHotNews = (ArrayList<News>)request.getAttribute("listHotNews");
						for(int i = 0; i < listHotNews.size(); i++){
							if(i == 0){
					%>
					<article class="featured videoc" topic-id="2002,2121,2369">
						<p class="typec"></p>
						<div class="coverc">
							<a
								href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(listHotNews.get(i).getName()) %>-<%=listHotNews.get(i).getIdNews()%>.html">
								<img
								src="<%=request.getContextPath() %>/files/<%=listHotNews.get(i).getPicture() %>"
								alt="Ben trong mo dao Bitcoin ca nhan 2 ty dong tai Viet Nam hinh anh"
								title="Bên trong mỏ đào Bitcoin cá nhân 2 tỷ đồng tại Việt Nam hình ảnh">
							</a>
						</div>
						<header>
							<p class="titlec">
								<a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(listHotNews.get(i).getName()) %>-<%=listHotNews.get(i).getIdNews()%>.html">
									<%=listHotNews.get(i).getName() %> </a>
							</p>
							<time datetime="2017-07-17 06:39+0700"></time>
							<p class="summaryc"><%=listHotNews.get(i).getPreview() %></p>
						</header>
					</article>
					<%}else{ %>
					<article class=" hasvideo">
						<div class="cover">
							<a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(listHotNews.get(i).getName()) %>-<%=listHotNews.get(i).getIdNews()%>.html">
								<img style="width: 151px;height: 112px" src="<%=request.getContextPath() %>/files/<%=listHotNews.get(i).getPicture() %>"
								alt="May tinh Apple: Cong nghe 2013, gia 2017 hinh anh"
								title="Máy tính Apple: Công nghệ 2013, giá 2017 hình ảnh">
							</a>
						</div>
						<header>
							<p class="title">
								<a
									href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(listHotNews.get(i).getName()) %>-<%=listHotNews.get(i).getIdNews()%>.html">
									<%=listHotNews.get(i).getName() %> </a>
							</p>
							<time datetime="2017-07-17 09:06+0700"></time>
						</header>
					</article>
					<%}} %>
				</section>
				<div class="kp-breadcrumb">
					<span></span>
				</div>
				<!-- breadcrumb -->
				<div class="list-posts clearfix">
					<ul class="list-unstyled" id="contentNews">
						<%
							ArrayList<News> listNews = (ArrayList<News>)request.getAttribute("listNews");
							for(News objNews : listNews){
						%>
						<li class="clearfix">
						<div class="item clearfix">
				    		<a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(objNews.getName()) %>-<%=objNews.getIdNews()%>.html">
				    			<img src="<%=request.getContextPath()%>/files/<%=objNews.getPicture() %>" alt="" />
				    			<div class="mask">
				    				<span class="icon-link"></span>
				    			</div>
				    		</a>
				    		<div class="item-content">
				    			<header class="header-item">
					    			<span class="icon-image pull-left"></span>
					    			<div class="item-right">
					    				<h4><a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(objNews.getName()) %>-<%=objNews.getIdNews()%>.html"><%=objNews.getName() %></a></h4>
					    			</div>
					    			<div class="clearfix"></div>
					    			<ul class="list-inline kp-metadata clearfix">
										<li class="kp-view"><span class="icon-calendar pull-left"></span><%=DayFormat.fD(objNews.getDate_Create()) %></li>
									</ul>
					    		</header>
					    		<p><%=objNews.getPreview() %></p>
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

			<!-- col-area-4 -->
		</div>
		<!-- main-content -->
	<%@include file="/templates/public/inc/sidebar.jsp" %>
	</div>
	<!-- container -->
	<footer id="page-footer">
		<div class="container">
			<p class="copy-right pull-left">
				Copyright 2013 - <span>Kopasoft</span>. All Rights Reserved
			</p>
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
	<script type="text/javascript">
 // Biến dùng kiểm tra nếu đang gửi ajax thì ko thực hiện gửi thêm
    var is_busy = false;
         
    // Biến lưu trữ trang hiện tại
    var page = 1;
     
    // Biến lưu trữ rạng thái phân trang 
    var stopped = false;
     
    // 
    var cid = <%=objCat.getIdCat()%>;
    $(document).ready(function()
    {    
        // Khi kéo scroll thì xử lý
        $(window).scroll(function() 
        {
            // Element append nội dung
            $element = $('#contentNews');
             
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
                    url         : '<%=request.getContextPath()%>/cat',
                    data        : {page : page, cid : cid},
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
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/templates/public/js/superfish.js"></script>
	<script
		src="<%=request.getContextPath()%>/templates/public/js/jquery.carousel-6.2.1.js"></script>
	<script
		src="<%=request.getContextPath()%>/templates/public/js/jflickrfeed.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/templates/public/js/tweetable.jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/templates/public/js/jquery.timeago.js"></script>
	<script
		src="<%=request.getContextPath()%>/templates/public/js/jquery.form.js"></script>
	<script
		src="<%=request.getContextPath()%>/templates/public/js/jquery.validate.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/templates/public/js/custom.js"></script>
</body>
</html>