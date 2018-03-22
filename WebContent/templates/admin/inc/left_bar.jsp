<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
              	  <%
	                  	User objInfoUser = (User)session.getAttribute("userLogin");
	              %>
              	  <p class="centered"><a href="<%=request.getContextPath()%>/admin"><img src="<%=request.getContextPath() %>/templates/admin/assets/img/avatar.png" class="img-circle" width="60"></a></p>
              	  <h5 class="centered"><%=objInfoUser.getFullname() %></h5>
              	  	
                  <li class="mt">
                      <a class="" href="<%=request.getContextPath()%>/admin/news">
                          <i class="fa fa-list-alt"></i>
                          <span>Bài viết</span>
                      </a>
                  </li>
                  
                  <li class="mt">
                      <a class="" href="<%=request.getContextPath()%>/admin/cat">
                          <i class="fa fa-book"></i>
                          <span>Danh mục</span>
                      </a>
                  </li>
                  
                  <li class="mt">
                      <a class="" href="<%=request.getContextPath()%>/admin/user">
                          <i class="fa fa-user"></i>
                          <span>Quản trị viên</span>
                      </a>
                  </li>
                  
                  <li class="mt">
                      <a class="" href="<%=request.getContextPath()%>/admin/contact">
                          <i class="fa fa-address-book-o"></i>
                          <span>Liên hệ</span>
                      </a>
                  </li>
                  
                  <li class="mt">
                      <a class="" href="<%=request.getContextPath()%>/admin/comment">
                          <i class="fa fa-comment"></i>
                          <span>Bình luận</span>
                      </a>
                  </li>
                  
                  <li class="mt">
                      <a class="" href="<%=request.getContextPath()%>/admin/ads">
                          <i class="fa fa-money"></i>
                          <span>Quảng cáo</span>
                      </a>
                  </li>
                  
                  <!-- <li class="sub-menu">
                      <a href="<%=request.getContextPath()%>/admin/cat" >
                          <i class="fa fa-book"></i>
                          <span>Danh mục</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="general.html">Danh mục</a></li>
                          <li><a  href="buttons.html">Buttons</a></li>
                          <li><a  href="panels.html">Panels</a></li>
                      </ul>
                  </li> -->

              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->