<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%
HashMap<String, String> itemData = (HashMap<String, String>)session.getAttribute("itemData");

String name = itemData.get("name");
String price = itemData.get("price");
String stock = itemData.get("stock");
String pictre = itemData.get("filename");
String setumei = itemData.get("setumei");
String syousai = itemData.get("syousai");


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面 -商品入力確認-</title>
</head>
<body>
	<p>管理画面</p>
	<a>ログアウト</a>
	<a>TOP</a>
	<p>商品追加</p>
	<p>商品情報</p>

		<span>商品名:<%= name %></span> 
		
		<span>価格:<%= price %></span>
				
		<span>個数:<%= stock %></span> 
				
		<span>画像:</span> <img src="<%= "tmp/"+ pictre %>" >
		
		<span>商品説明:<%= setumei %></span>

		<span>詳細情報:<%= syousai %></span>
		
		<a>戻る</a>
		<a href="ExecuteInsertItemServlet">追加する</a>

</body>
</html>