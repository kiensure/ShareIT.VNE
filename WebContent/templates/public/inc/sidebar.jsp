<%@page import="model.dao.AdvertisementsDao"%>
<%@page import="model.bean.Advertisements"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div id="sidebar" class="pull-left">
					<%
						ArrayList<Advertisements> listQC = (new AdvertisementsDao().getItems());
						Random rd = new Random();
						int randAds1 = 0;
						int randAds2 = 0;
						while(true){
							randAds1 = rd.nextInt(listQC.size());
							randAds2 = rd.nextInt(listQC.size());
							if(listQC.get(randAds1).getLocation() == 3 && listQC.get(randAds2).getLocation() == 3){
								break;
							}
						}
					%>
			<div style="margin-bottom: 10px;"><a target="_blank" href="http://vinaenter.edu.vn"><img style="width: 226px; height: 646px;" alt="" src="<%=request.getContextPath() %>/files/<%=listQC.get(randAds1).getPicture()%>"></a></div>
			<div style="margin-bottom : 10px;"><a target="_blank" href="http://vinaenter.edu.vn"><img style="width: 226px; height: 646px;" alt="" src="<%=request.getContextPath() %>/files/<%=listQC.get(randAds2).getPicture()%>"></a></div>
			<div class="widget widget-list-news">
				<div class="accordion-defaul">
				</div>
				<div class="accordion-defaul">
				</div>
			</div>
			<!-- widget-list-news -->
		</div>
		<!-- sidebar -->