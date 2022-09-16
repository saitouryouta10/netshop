<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.users.db.LoginDto"%>
<%@ page import="Config.Validation"%>
<%
Validation v = new Validation();

String message = "";

if (request.getAttribute("error") != null) {
	message = "error";
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
	<header><%@ include file="/include/header.jsp"%></header>
	<h2>登録情報</h2>

	<%
	if (message.equals("error")) {
	%>
	<p>入力に誤りがありました</p>
	<%
	}
	%>

	<form action="ValidationMemberServlet" method="post">
		<p>
			<span>名前</span><br> <input name="name"
				value="<%=v.h(dto.getName())%>" required>
		</p>

		<p>
			<span>名前(フリガナ)</span><br> <input name="name_kana"
				value="<%=v.h(dto.getName_kana())%>" required>
		</p>

		<p>
			<span>ニックネーム</span><br> <input name="nickname"
				value="<%=v.h(dto.getNickname())%>" required>
		</p>

		<p>
			<span>性別</span><br> 
			<input type="radio" name="sex" id="male"value="1" <%=dto.getSex() == 1 ? "checked" : ""%>> 
			<label	for="male">男性</label> 
			<input type="radio" name="sex" id="famale"value="2" <%=dto.getSex() == 2 ? "checked" : ""%>> 
			<label	for="famale">女性</label> 
			<input type="radio" name="sex" id="anoter"	value="0" <%=dto.getSex() == 0 ? "checked" : ""%>> 
			<label	for="anoter">その他</label>
		</p>

		<p>
			<span>生年月日</span><br> <input type="date" name="date"
				value="<%=v.h(dto.getBirthday())%>">
		</p>

		<p>
			<span>住所</span><br> <input name="zipcode"
				value="<%=v.h(dto.getZipcode())%>" required><br> <input
				name="address" value="<%=v.h(dto.getAddress())%>" required>
		</p>

		<p>
			<span>電話番号</span><br> <input name="tell"
				value="<%=v.h(dto.getTell())%>" required>
		</p>

		<p>
			<span>メールアドレス</span><br> <input type="email" name="email"
				value="<%=v.h(dto.getEmail())%>" required>
		</p>
		<a href="SendPasswordServlet">パスワードを変更</a> <a
			href="SendMemberInfoServlet">戻る</a>
		<button type="submit">登録する</button>
	</form>
	<footer><%@ include file="/include/footer.jsp"%></footer>
</body>
</html>