<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.users.db.LoginDto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGESHOP -会員情報-</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="css/index.css">

</head>
<body>
	<header><%@ include file="/include/header.jsp"%></header>
	<h2>会員情報</h2>
	<p><%=dto.getName()%>さん
	</p>

	<a href="SendCheckInfoServlet">登録情報を確認する</a>
	<br>
	<a href="SendChangeInfoServlet">登録情報を変更する</a>
	<br>
	<a href="ExecuteHistoryServlet">注文履歴</a>
	<footer><%@ include file="/include/footer.jsp"%></footer>
</body>
</html>