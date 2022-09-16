<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String missmatch = "";

if (request.getAttribute("missmatch") != null) {
	missmatch = "missmatch";
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGESHOP -ログイン-</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<div class="titleContainer">
		<h2 class="title">HOGEHOGESHOP</h2>
		<a href="CreateAccountServlet">▷アカウント作成へ</a> <a href="HomeServlet">▷サイトトップへ</a>
	</div>
	<div class="container">
	<h3 class="subtitle">ログイン</h3>
		<div class="input-form">
			<form action="ExecuteLoginServlet" method="post">
				<%
				if (missmatch.equals("missmatch")) {
				%>
				<p class="error-message">メールアドレスかパスワードが間違っています</p>
				<%
				}
				%>
				<div class="input-mail">
					<label for="email">メールアドレス</label> <br><input type="email" id="email"
						name="email">
				</div>
				<div class="input-pass">
					<label for="pass">パスワード</label><br> <input type="password" id="pass"
						name="pass">
				</div>
				<a href="CreateAccountServlet">アカウント未登録の方はこちら</a>
				<div class="login-submit">
					<button type="submit" class="btn btn--orange btn--radius">ログイン</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>