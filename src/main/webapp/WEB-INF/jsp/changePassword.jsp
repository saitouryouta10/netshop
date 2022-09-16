<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String message = "";
if (request.getAttribute("error") != null) {
	message = "error";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGESHOP -パスワード変更-</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>



	<header><%@ include file="/include/header.jsp"%></header>
	<h2>パスワード変更</h2>
	<%
	if (message.equals("error")) {
	%>
	<p>入力に誤りがあります</p>
	<%
	}
	%>
	<form action="ValidationPasswordServlet" method="post">
		<p>以前のパスワードを入力してください</p>
		<input type="password" name="oldPassword" required>
		<p>新しいパスワード</p>
		<input type="password" name="newPassword" required>
		<p>もう一度新しいパスワードを入力してください</p>
		<input type="password" name="newPasswordConf" required> <a
			href="SendMemberInfoServlet">戻る</a>
		<button type="submit">OK</button>
	</form>
	<footer><%@ include file="/include/footer.jsp"%></footer>
</body>
</html>