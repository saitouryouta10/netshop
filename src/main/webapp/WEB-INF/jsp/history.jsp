<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Model.history.db.HistoryDto"%>
<%
List<HistoryDto> dtoList = (List<HistoryDto>) request.getAttribute("historyInfo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGESHOP -注文履歴-</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
<header><%@ include file="/include/header.jsp" %></header>
	<h2>注文履歴</h2>
	<a href="SendConstrictServlet">絞り込み＞</a>
	<% if (dtoList.size() == 0) { %>
	<p>該当商品がありません</p>
	<%} %>
	<%
	for (int i = 0; i < dtoList.size(); i++) {
	%>
	<div class="container">
		<div>

			<%
			HistoryDto h = dtoList.get(i);
			%>
			<%
			if (h.getPicture() != null) {
			%>
			<img src="<%="img/" + h.getPicture()%>" width="150px"
				height="150px">
			<%
			} else {
			%>
			<img src="img/noimage.png" width="150px" height="150px">
			<%
			}
			%>

			<p><span>商品名：</span><%=h.getName()%></p>
			<p><span>値段：</span><%=h.getPrice()%></p>
			<p><span>購入日：</span><%=h.getCreated()%></p>

			<a href="ExecuteSelectReviewServlet?itemId=<%= h.getItem_id() %>">商品レビューを書く</a> 
			<a href="ExecuteSelectItemServlet?itemId=<%= h.getItem_id() %>">再度購入</a>

		</div>
	</div>
	<%
	}
	%>
		<footer><%@ include file="/include/footer.jsp" %></footer>
</body>
</html>