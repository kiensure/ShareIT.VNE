<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp" %>
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <%@include file="/templates/admin/inc/left_bar.jsp" %>
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <div class="wrapper">

              
          </div>
          <%@include file="/templates/admin/inc/footer.jsp" %>
      </section>

      <!--main content end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery-1.8.3.min.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.scrollTo.min.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.sparkline.js"></script>


    <!--common script for all pages-->
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/common-scripts.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/admin/assets/js/gritter-conf.js"></script>

  </body>
</html>
