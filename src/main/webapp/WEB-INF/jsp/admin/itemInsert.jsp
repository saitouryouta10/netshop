<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="Config.Validation" %>
<%

Validation v = new Validation();

String name = "";
String price = "";
String stock = "";
String setumei = "";
String syousai = "";
String picture = "";

if (session.getAttribute("itemData") != null) {
	HashMap<String, String> itemData = (HashMap<String, String>)session.getAttribute("itemData");
	name = itemData.get("name");
	price = itemData.get("price");
	stock = itemData.get("stock");
	setumei = itemData.get("setumei");
	syousai = itemData.get("syousai");
	picture = itemData.get("filename");
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面 -商品入力-</title>
</head>
<body>
	<p>管理画面</p>
	<a>ログアウト</a>
	<a>TOP</a>
	<p>商品追加</p>
	<p>商品情報</p>
	<% if (request.getAttribute("error") != null) { %>
		<% if (request.getAttribute("error").equals("error")) { %>
		<p>入力が不正です</p>
		<%} %>
	<%} %>
	<form action="CheckInsertItemServlet" method="post" enctype="multipart/form-data">
		<span>商品名</span> 
		<input name="name" value="<%= v.h(name) %>" required><br> 
		
		<span>価格</span>
		<input name="price" value="<%= v.h(price) %>" required>円<br> 
		
		<span>個数</span> 
		<input name="stock" value="<%= v.h(stock) %>" required><br> 
		
		<span>画像</span> 
		<input type="file" name="image" required><br> 
		
		<span>商品説明</span>
		<textarea name="setumei" required><%= v.h(setumei) %></textarea>

		<span>詳細情報</span>
		<textarea name="syousai" required><%= v.h(syousai) %></textarea>

		<a>戻る</a>
		<button type="submit">確認する</button>
	</form>

</body>
</html>