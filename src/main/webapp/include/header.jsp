<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.users.db.LoginDto" %>
<%
LoginDto dto = new LoginDto();
if (session.getAttribute("loginData") != null ){
	dto = (LoginDto)session.getAttribute("loginData");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="../css/index.css">
</head>
<body>
	<div class="title_header">
		<h1 >
			<a href="HomeServlet">HOGEHOGE SHOP</a>
		</h1>
	</div>
	<div class="header_list">
		<div class="serch">
			<div class="header-message">
				<div class="header-message-left">
				<% if (session.getAttribute("loginData") == null ) { %>
					<p>ゲストさんおかえりなさい</p>
				<% } else { %>
					<p><%= dto.getNickname() %>さんおかえりなさい</p>
				<%} %>
				</div>
				<div class="header-message-right">
					<form action="HomeServlet" method="get">
						<input type="text" class="text_box" name="search" maxlength="100"
							placeholder="商品名を入力してください">
						<button type="submit" class="btn btn-warning" id="serch_button">検索🔍</button>
					</form>
				</div>
			</div>

			<div class="header-link">
				<p>
					<a href="ExecuteSelectFavoriteServlet">お気に入り</a>
				</p>
				<p>
					<a href="ExecuteSelectCartServlet">カート</a>
				</p>
				<% if (session.getAttribute("loginData") == null ) { %>
				<p>
					<a href="LoginServlet">ログイン</a>
				</p>
				<% } else { %>
				<p>
					<a href="LogoutServlet">ログアウト</a>
				</p>
				<%} %>
				<p>
					<a href="SendMemberInfoServlet">会員情報</a>
				</p>
			</div>
		</div>

	</div>
</body>
</html>