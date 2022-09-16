<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.users.db.LoginDto"%>
<%@ page import="java.util.HashMap" %>
<%
HashMap<String, String> createData = (HashMap<String, String>) session.getAttribute("createDataHash");
%>
<%

String name = createData.get("name");
String name_kana = createData.get("name_kana");
String nickname = createData.get("nickname");
String zipcode = createData.get("zipcode");
String address = createData.get("address");
String tell = createData.get("tell");
String email = createData.get("email");

String sex;
if (createData.get("sex").equals("1")) {
	sex = "男性";
} else if (createData.get("sex").equals("2")) {
	sex = "女性";
} else {
	sex = "その他";
}

String birthday;
if (createData.get("birthday").equals("0000-00-00")) {
	birthday = "設定なし";
} else {
	birthday = createData.get("birthday");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGESHOP -登録情報確認-</title>
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
		<a href="LoginServlet">▷ログイン画面へ</a> <a href="HomeServlet">▷サイトトップへ</a>
	</div>
	<div class="imageContainer">
		<img src="images/step2.png" width="50%" height="50%">
	</div>
	<h3 class="subtitle">登録情報確認</h3>
	<div class="box">
		<table class="createTable">
			<tr>
				<th>名前</th>
				<td><%= name %></td>
			</tr>
			<tr>
				<th>名前(フリガナ)</th>
				<td><%= name_kana %></td>
			</tr>
			<tr>
				<th>ニックネーム</th>
				<td><%= nickname %></td>
			</tr>
			<tr>
				<th>性別</th>
				<td><%= sex %></td>
			</tr>
			<tr>
				<th>誕生日</th>
				<td><%= birthday %></td>
			</tr>
			<tr>
				<th>住所</th>
				<td>〒<%= zipcode %><br><%= address %></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><%= tell %></td>
			</tr>
			<tr>
				<th>メールアドレス</th>
				<td><%= email %></td>
		</table>
	</div>
	<div class="anoter">
		<a href='CreateAccountServlet' class="btn btn--orange btn--radius anoter-button">変更する</a>
		<a href='ExecuteInsertAccountServlet' class="btn btn--orange btn--radius anoter-button">登録する</a>
	</div>
</body>
</html>