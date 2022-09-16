<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="Model.cart.db.CartDto"%>
<%@ page import="Config.Validation"%>
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
<title>HOGEHOGESHOP -最終確認-</title>
</head>
<body>
	<header><%@ include file="/include/header.jsp"%></header>
	<h1>こちらの商品を購入します</h1>
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
			<span>商品名：</span><%=v.h(c.getName())%>
			<span>価格：</span><%=v.h(String.valueOf(c.getPrice()))%>
			<span>個数：</span><%=v.h(String.valueOf(c.getNumber()))%>
			<span>合計価格：</span><%=c.getPrice() * c.getNumber()%>
		</div>
	</div>
	<%
}
%>
	<button onclick="location.href='BuyServlet'">購入確定</button>
	<footer><%@ include file="/include/footer.jsp"%></footer>
</body>
</html>