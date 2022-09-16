<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.users.db.LoginDto"%>
<%@ page import="Config.Validation"%>
<%
Validation v = new Validation();
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
	<p>
		<span>名前</span><br> <span><%=v.h(dto.getName())%></span>
	</p>

	<p>
		<span>名前(フリガナ)</span><br> <span><%=v.h(dto.getName_kana())%></span>
	</p>

	<p>
		<span>ニックネーム</span><br> <span><%=v.h(dto.getNickname())%></span>
	</p>

	<p>
		<span>性別</span><br> <span> <%=dto.getSex() == 1 ? "男性" : ""%>
			<%=dto.getSex() == 2 ? "女性" : ""%> <%=dto.getSex() == 0 ? "その他" : ""%>
		</span>
	</p>

	<p>
		<span>生年月日</span><br> <span> <%=dto.getBirthday().equals("0000-00-00") ? "設定なし" : v.h(dto.getBirthday())%>
		</span>
	</p>

	<p>
		<span>住所</span><br> <span><%=v.h(dto.getZipcode())%></span><br>
		<span><%=v.h(dto.getAddress())%></span>
	</p>

	<p>
		<span>電話番号</span><br> <span><%=v.h(dto.getTell())%></span>
	</p>

	<p>
		<span>メールアドレス</span><br> <span><%=v.h(dto.getEmail())%></span>
	</p>

	<a href="SendMemberInfoServlet">戻る</a>
	<footer><%@ include file="/include/footer.jsp"%></footer>
</body>
</html>