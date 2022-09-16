<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Model.cart.db.CartDto"%>
<%@ page import="Config.Validation"%>
<%@ page import="Model.users.db.LoginDto"%>
<%
Validation v = new Validation();
List<CartDto> dtoList = (List<CartDto>) session.getAttribute("cartInfo");


int sumPrice = 0;
for (int i = 0; i < dtoList.size(); i++) {
	CartDto c = dtoList.get(i);
	sumPrice += c.getPrice() * c.getNumber();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="css/index.css">
<title>HOGEHOGESHOP -カート確認-</title>
</head>
<body>
	<header><%@ include file="/include/header.jsp"%></header>

	<h1>カート内容</h1>

	<p>
		現在の合計：<%=sumPrice%>円
	</p>
	<button onclick="location.href='HomeServlet'">トップに戻る</button>
	
	<%
	for (int i = 0; i < dtoList.size(); i++) {
		CartDto c = dtoList.get(i);
	%>

	<div class="container">
		<div class="flex-cart">
			<%
			if (c.getPicture() != null) {
			%>
			<img src="<%="img/" + v.h(c.getPicture())%>" width="150px;"
				height="150px;">
			<%
			} else {
			%>
			<img src="img/noimage.png" width="150px; " height="150px;">
			<%
			}
			%>
			<form action="ExecuteUpdateCartServlet" method="get">
				<input type="hidden" name="itemId" value="<%=c.getItem_id()%>">
				<span>商品名：</span><%=v.h(c.getName())%>
				<span>価格：</span><%=v.h(String.valueOf(c.getPrice()))%>
				<span>個数：</span>
				<%
				if (c.getNumber() == 0) {
				%>
				在庫不足
				<%
				} else {
				%>
				<input class="stock-number" name="stock" type="number" min="1"
					max="<%=c.getStock()%>"
					value="<%=v.h(String.valueOf(c.getNumber()))%>"> <span>合計価格：</span><%=c.getPrice() * c.getNumber()%>
				<button type="submit">個数変更</button>
				<%
				}
				%>
			</form>
			<button
				onclick="location.href='ExecuteDeleteCartServlet?cartId=<%=c.getId()%>'">削除</button>
		</div>
	</div>
	<%
}
%>
	<button onclick="location.href='CheckCartServlet'" id="1">購入する</button>

	<% if (dtoList.size() == 0) { %>
	<p>該当商品がありません</p>
	<%} %>
	<%
	for (int i = 0; i < dtoList.size(); i++) {
		CartDto c = dtoList.get(i);
	%>
	<script type="text/javascript">
		var myStock =
	<%=c.getNumber()%>
		var button = document.getElementById("1");
		if (myStock == 0) {
			button.disabled = true;
		}
	</script>
	<%
	}
	%>
	<script type="text/javascript">
		var button = document.getElementById("1");
		if (
	<%=dtoList.size()%>
		== 0) {
			button.disabled = true;
		}
	</script>
	<footer><%@ include file="/include/footer.jsp"%></footer>
</body>
</html>