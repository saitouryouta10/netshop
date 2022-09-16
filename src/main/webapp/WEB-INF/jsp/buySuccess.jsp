<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.users.db.LoginDto"%>
<%@ page import="Model.cart.db.CartDto"%>
<%@ page import="java.util.List"%>

<%
List<CartDto> cartDto = (List<CartDto>) request.getAttribute("cartInfo");
int sumPrice = 0;

for (int i = 0; i < cartDto.size(); i++) {
	CartDto d = cartDto.get(i);
	sumPrice += d.getPrice() * d.getNumber();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGESHOP -購入完了-</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="css/index.css">

</head>
<body>

	<header><%@ include file="/include/header.jsp"%></header>
	<h1>ご購入ありがとうございました。</h1>

	<p>お支払金額は</p>
	<p><%=sumPrice%>
		円です
	</p>

	<p>お客様情報</p>

	<div>
		<span>名前：<%=dto.getName()%></span>
	</div>
	<div>
		<span>住所：<%=dto.getAddress()%></span>
	</div>
	<div>
		<span>メールアドレス：<%=dto.getEmail()%></span>
	</div>
	<p>3日以内にお支払いをお願いします</p>
	<p>お問い合わせはサポートダイヤルへ(xxx-xxxx-xxxx)</p>

	<button onclick="location.href='HomeServlet'">トップに戻る</button>
	<footer><%@ include file="/include/footer.jsp"%></footer>
</body>
</html>