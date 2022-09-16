<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="Model.users.db.LoginDto"%>
<%@ page import="Config.Validation"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGESHOP -新規登録-</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="css/index.css">
<style>
span {
	color: red;
	font-size: 0.6rem;
}
</style>
<script type="text/javascript" src="js/index.js">
	
</script>
<%
Validation v = new Validation();
String error = "";

if (request.getAttribute("error") != null && request.getAttribute("error").equals("error")) {
	error = "error";
}
String name = "";
String name_kana = "";
String nickname = "";
String sex = "";
String birthday = "";
String zipcode = "";
String address = "";
String tell = "";
String email = "";

if (session.getAttribute("createDataHash") != null) {
	Map<String, String> createData = (HashMap<String, String>) session.getAttribute("createDataHash");
	name = createData.get("name");
	name_kana = createData.get("name_kana");
	nickname = createData.get("nickname");
	sex = createData.get("sex");
	birthday = createData.get("birthday");
	zipcode = createData.get("zipcode");
	address = createData.get("address");
	tell = createData.get("tell");
	email = createData.get("email");
}
%>
</head>
<body>
	<div class="titleContainer">
		<h2 class="title">HOGEHOGESHOP</h2>
		<a href="LoginServlet">▷ログイン画面へ</a>
		<a href="HomeServlet">▷サイトトップへ</a>
	</div>
	<div class="imageContainer">
		<img src="images/step1.png" width="50%" height="50%">
	</div>
	<h3 class="subtitle">新規会員登録</h3>
	<%
	if (error.equals("error")) {
	%>
	<p class="error-message">※入力に誤りがありました。</p>
	<%
	}
	%>
	<form action="ValidationAccountServlet" method="post">
		<div class="box">
			<table class="createTable">
				<tr>
					<th>お名前<span class="badge bg-danger">必須</span></th>
					<td><input type="text" name="name" value="<%=v.h(name)%>"
						required>
						<p>例) 山田太郎 (全角)</p></td>
				<tr>
				<tr>
					<th>お名前(フリガナ)<span class="badge bg-danger">必須</span></th>
					<td><input type="text" name="kana" value="<%=v.h(name_kana)%>"
						required>
						<p>例) ヤマダタロウ (全角カタカナ)</p></td>
				<tr>
				<tr>
					<th>ニックネーム<span class="badge bg-danger">必須</span></th>
					<td><input type="text" name="nickname"
						value="<%=v.h(nickname)%>" required></td>
				<tr>
				<tr>
					<th>性別</th>
					<td><input type="radio" name="sex" id="male" value="1"
						<%=sex.equals("1") ? "checked" : ""%>> <label for="male">男性</label>
						<input type="radio" name="sex" id="famale" value="2"
						<%=sex.equals("2") ? "checked" : ""%>> <label for="famale">女性</label>
						<input type="radio" name="sex" id="anoter" value="0"
						<%=sex.equals("0") ? "checked" : ""%>> <label for="anoter">その他</label></td>
				<tr>
				<tr>
					<th>誕生日</th>
					<td><input type="date" name="date" value="<%=v.h(birthday)%>"></td>
				<tr>
				<tr>
					<th>ご住所<span class="badge bg-danger">必須</span></th>
					<td height="120px"><input name="zipcode"
						value="<%=v.h(zipcode)%>" placeholder="〒" required><br>
						<input type="text" name="address" value="<%=v.h(address)%>"
						required>
						<p>例) ○○県××市△△町</td>
				<tr>
				<tr>
					<th>携帯電話番号<span class="badge bg-danger">必須</span></th>
					<td><input type="text" name="tell" value="<%=v.h(tell)%>"
						required>
						<p>例) 000-0000-0000 (半角数字)</p></td>
				<tr>
				<tr>
					<th>メールアドレス<span class="badge bg-danger">必須</span></th>
					<td><input type="email" name="email" value="<%=v.h(email)%>"
						required>
						<p>例) hogehoge@exam.com</p></td>
				<tr>
				<tr>
					<th>パスワード<span class="badge bg-danger">必須</span></th>
					<td><input type="password" name="pass" min="8" required>
						<p>(8文字以上32文字以内)(半角英数字)</p></td>
				<tr>
				<tr>
					<th>パスワード(確認)<span class="badge bg-danger">必須</span></th>
					<td><input type="password" name="passConfirmation" min="8"
						required>
						<p>確認の為もう一度ご入力ください</p></td>
				<tr>
			</table>

		</div>

		<div class="anoter">
			<input type="checkbox" id="check" name="doui" value="1"
				onchange="changeButton()"> <label for="check">利用規約、<a
				href="#">プライバシーポリシー</a>に同意する
			</label><br>
			
			<button type="submit" id="button1" class="btn btn--orange btn--radius" disabled>同意して確認画面へ</button>
		</div>
	</form>
</body>
</html>