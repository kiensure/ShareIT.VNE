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
<div id="content" class="container clearfix">
	<div class="main-top">
		<div class="col-area-2 pull-left">
			<div class="widget newsletter clearfix">
				<i class="icon-eye pull-left"></i>
				<h2 class="pull-left"><span>Cổng chia sẻ thông tin công nghệ </span>Cập nhật hằng ngày những tin tức mới nhất về thế giới công nghệ</h2>
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
				  <li class="active">Chi Tiết</li>
				</ol>
				<span></span>
			</div>	
			<!-- kp-breadcrumb -->
			<div class="page-content clearfix">
				<%
					News objNews = (News)request.getAttribute("objnews");
				%>				
				<article>
					<img src="<%=request.getContextPath() %>/files/<%=objNews.getPicture() %>" alt="" />
					<h4><%=objNews.getName() %></h4>
					<ul class="list-inline kp-metadata clearfix">
						<li class="kp-view"><span class="icon-calendar pull-left"></span><%=DayFormat.fD(objNews.getDate_Create()) %></li>
					</ul>
					<h3><%=objNews.getPreview() %></h3>
					<p><%=objNews.getDetail() %></p>
					<div class="clearfix"></div>
					<div class="clearfix"></div>
					<div class="bottom-article">
						<div style="margin-bottom: 10px; font-size: 20px; color: #e64946; font-weight: 700; text-transform: uppercase; ">Có thể bạn muốn xem</div>
						<div class="row">
							<%
								if(request.getAttribute("listNewsLQ") != null){
									ArrayList<News> listLQ = (ArrayList<News>)request.getAttribute("listNewsLQ");
									if(listLQ.size() > 0){
										for(News objNewsLQ : listLQ){
							%>
							<div class="col-4">
								<div class="kp-next">
									<h4><a href="<%=request.getContextPath()%>/tin-tuc/<%=StringUntil.createSlugURL(objNewsLQ.getName()) %>-<%=objNewsLQ.getIdNews()%>.html"><%=objNewsLQ.getName() %></a></h4>
									<p><%=objNewsLQ.getPreview() %></p>
								</div>
							</div>
							<%}}} %>
						</div>
					</div>
					<!-- bottom article -->
				</article>
				<div class="kp-comment">
					<%
						ArrayList<Comment> listCmtParent = (ArrayList<Comment>)request.getAttribute("listCmtParent");
						ArrayList<Comment> listCmt = (ArrayList<Comment>)request.getAttribute("listCmt");
						int countCmt = (Integer)request.getAttribute("countCmt");
					%>
					<h2><span><%=countCmt %> comments</span></h2>
					<ul class="list-comments list-unstyled">
						<%
							for(Comment objCmt : listCmtParent){
						%>
						<li>
							<div class="detail-comment">
								<img src="<%=request.getContextPath() %>/templates/public/placeholders/avartar/img-2.jpg" class="pull-left" alt="" />
								<div class="item-right">
									<header class="clearfix">
										<div class="pull-left">
											<h4><%=objCmt.getFullName() %></h4>
											<span><%=DayFormat.fD(objCmt.getDate_Create()) %></span>
										</div>
										<p class="pull-right"><span><a onclick="openform(<%=objCmt.getIdComment() %>)" href="javascript:void(0)">Reply</a></span></p>
									</header>
									<p><%=objCmt.getContent() %> </p>
								</div>
							</div>
							<div id="open-form-<%=objCmt.getIdComment() %>" style="display: none;">
				            <div class="comment" style="padding: 2em 0; ">
				              <div class="form-reply" style="margin: 1em 0 0 5em;">
				                <form id="formreply" action="javascript:void(0)" class="contact_form form-<%=objCmt.getIdComment() %>" method="post">
				                  <input type="text" name="hotenr" class="textbox" placeholder="Nhập họ tên">
				                  <input type="text" name="emailr" class="textbox" placeholder="Nhập email">
				                  <input type="text" name="websiter" class="textbox" placeholder="Nhập website">
				                  <textarea name="contentr" placeholder="Nội dung bình luận"></textarea>
				                  <div class="form-groupr">
				                    <div class="rowr">
				                      <div class="col-md-12 thong-bao-<%=objCmt.getIdComment()%>">
				                      </div>
				                    </div>
				                  </div>
				                  <div class="form-groupr">
				                    <div class="rowr">
				                      <div style="width: 50%" class="col-md-6">
				                        <input type="submit" value="Phản hồi" onclick="reply(<%=objCmt.getIdComment()%>)">
				                        <input type="reset" value="Đóng" onclick="closeform(<%=objCmt.getIdComment()%>)">
				                        <a class="loading" style="display:none">
				                          <img src="<%=request.getContextPath() %>/templates/public/images/loading.gif" width="40px">
				                        </a>
				                      </div>
				                    </div>
				                  </div>
				                </form>
				              </div>
				            </div>
				          </div>
				          <%
				          	for(Comment objCmtChild : listCmt){
				          		if(objCmtChild.getIdParent() == objCmt.getIdComment()){
				          %>
							<ul class="list-comments list-unstyled">
								<li>
									<div class="detail-comment">
										<img src="<%=request.getContextPath() %>/templates/public/placeholders/avartar/img-2.jpg" class="pull-left" alt="" />
										<div class="item-right">
											<header class="clearfix">
												<div class="pull-left">
													<h4><%=objCmtChild.getFullName() %></h4>
													<span>10 September, 2013 at 6:00 pm</span>
												</div>
												<p class="pull-right"><span><a onclick="openform(<%=objCmt.getIdComment() %>)" href="javascript:void(0)">Reply</a></span></p>
											</header>
											<p><%=objCmtChild.getContent() %></p>
										</div>
									</div>
								</li>
							</ul>
						<%}} %>
						</li>
						<%} %>
					</ul>
					<div class="clearfix"></div>							
				</div>
				<!-- kp-comment -->
				<div class="kp-form-comment">
					<h2>Để lại comment</h2>
					<form action="javascript:void(0)" method="post" class="form-comment" id="comment" >
						<div class="row">
							<div class="col-6">
								<div class="form-group">
									<label for="s" class="kp-label">Họ tên<span> *</span></label>
									<input type="text" id="s" name="hoten" class="form-control" />
								</div>
								<div class="form-group">
									<label for="d" class="kp-label">Email<span> *</span></label>
									<input type="text" id="d" name="email" class="form-control" />
								</div>
								<div class="form-group">
									<label for="f" class="kp-label">Website</label>
									<input type="text" id="f" name="website" class="form-control" />
								</div>
							</div>
							<div class="col-6">
								<div class="form-group">
									<label for="a" class="kp-label">Message<span> *</span></label>
									<textarea id="a" name="content" class="form-control"></textarea>
								</div>
							</div>
							<div style="clear: both;"></div>
							<div class="thong-bao"></div>
							<input type="submit" value="GỬI COMMENT" id="input-submit" class="btn"/>
						</div>

					</form>
					<div id="response"></div>
				</div>
				<!-- kp-form-comment -->
			</div>
			<!-- list-post -->
			
		</div>
		<!-- col-area-3 -->
	</div>
	<!-- main-content -->
	<%@include file="/templates/public/inc/sidebar.jsp" %>
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

<script type="text/javascript">
  $(function(){
    $("#comment").on('submit', function(){
      var hoten = $( "input[name='hoten']").val();
      var email = $( "input[name='email']").val();
      var website = $( "input[name='website']").val();
     // var _token = $( "input[name='_token']").val();
      var content = $( "textarea[name='content']").val();
      var news_id = <%=objNews.getIdNews()%>;
      $('#loadingmessage').show();
      //$.ajaxSetup({
      //  headers: {
      //    'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
      //  }
      //});
      $.ajax({
        url: "<%=request.getContextPath()%>/admin/postcmt",
        type: 'POST',
        cache: false,
        data: {
          ahoten: hoten,
          aemail: email,
          awebsite: website,
          acontent: content,
          anews_id: news_id,
          //token: _token
        },
        success: function(data){
          $('#loadingmessage').hide();
          $('.thong-bao').html(data);
        },
        error: function (){
          alert('Có lỗi');
        }
      });   
    });
  });
</script>
<script type="text/javascript">
	  function reply(id_cmt){
	    var hoten = $('#open-form-'+ id_cmt + " input[name='hotenr']").val();
	    var email = $('#open-form-'+ id_cmt +  " input[name='emailr']").val();
	    var website = $('#open-form-'+ id_cmt +  " input[name='websiter']").val();
	    //var _token = $('#open-form-'+ id_cmt +  " input[name='_token']").val();
	    var content = $('#open-form-'+ id_cmt +  " textarea[name='contentr']").val();
	    
	    var news_id = <%=objNews.getIdNews()%>;
	
	    $('.loading').show();
	    //$.ajaxSetup({
	     // headers: {
	     //   'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
	     // }
	   // });
	    $.ajax({
	      url: '<%=request.getContextPath()%>/admin/replycmt',
	      type: 'POST',
	      cache: false,
	      data: {
	        ahoten: hoten,
	        aemail: email,
	        awebsite: website,
	        acontent: content,
	        anews_id: news_id,
	        aid_cmt: id_cmt,
	        //token: _token
	      },
	      success: function(data){
	        $('.loading').hide();
	        $('.thong-bao-'+id_cmt).html(data);
	      },
	      error: function (){
	        alert('Có lỗi');
	      }
	    });  
	  }
</script>
<script type="text/javascript">
  function openform(id){
	  	var jsArray = [];
	  	<%for(int i=0;i<listCmt.size();i++){%>
	  	    jsArray.push("<%= listCmt.get(i).getIdComment()%>");
	  	<%}%>
	  	for (var i = 0; i < jsArray.length; i++) {
	  		$('#open-form-'+jsArray[i]).hide();
		}
        $('#open-form-'+id).show();
    $('.form-'+id+ " input[name='hotenr']").focus();
  }
</script>
<script type="text/javascript">
  function closeform(id){
		var jsArray = [];
		<%for(int i=0;i<listCmt.size();i++){%>
	    	jsArray.push("<%= listCmt.get(i).getIdComment()%>");
		<%}%>
		for (var i = 0; i < jsArray.length; i++) {
			$('#open-form-'+jsArray[i]).hide();
		}
}
</script>
</body>
</html>