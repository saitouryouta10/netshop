<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Model.favorite.db.FavoriteDto"%>
<%
List<FavoriteDto> dtoList = (List<FavoriteDto>) request.getAttribute("favoriteInfo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGESHOP -お気に入り-</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
<header><%@ include file="/include/header.jsp" %></header>
	<h2>お気に入り</h2>
	<% if (dtoList.size() == 0) { %>
	<p>該当商品がありません</p>
	<%} %>
	<%
	for (int i = 0; i < dtoList.size(); i++) {
	%>
	<div class="container">
		<div>

			<%
			FavoriteDto f = dtoList.get(i);
			%>
			<%
			if (f.getPicture() != null) {
			%>
			<img src="<%="img/" + f.getPicture()%>" width="150px"
				height="150px">
			<%
			} else {
			%>
			<img src="img/noimage.png" width="150px" height="150px">
			<%
			}
			%>

			<p><span>商品名：</span><%=f.getName()%></p>
			<p><span>値段：</span><%=f.getPrice()%></p>

			<a href="ExecuteSelectItemServlet?itemId=<%= f.getItem_id() %>">商品詳細ページへ</a> 
			<a href="ExecuteDeleteFavoriteServlet?itemId=<%= f.getItem_id() %>&type=1">お気に入り解除</a>

		</div>
	</div>
	<%
	}
	%>
		<footer><%@ include file="/include/footer.jsp" %></footer>
</body>
</html>