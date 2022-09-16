<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="Config.Validation"%>
<%
String name = "";
String email = "";
String title = "";
String inquiry = "";
if (session.getAttribute("inquiryData") != null) {
	HashMap<String, String> h = (HashMap<String, String>) session.getAttribute("inquiryData");
	name = h.get("name");
	email = h.get("email");
	title = h.get("title");
	inquiry = h.get("inquiry");

}

Validation v = new Validation();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGESHOP -お問い合わせ-</title>
</head>
<body>
	<h1>お問い合わせ</h1>

	<%if (request.getAttribute("error") != null) {%>
	<p>入力情報に誤りがあります</p>
	<%
	}
	%>

	<form action="ValidationInquiryServlet" method="post">
		<span>お名前</span><span>必須</span><br> 
		<input name="name"value="<%=v.h(name)%>"><br> 
		
		<span>メールアドレス</span><span>必須</span><br>
		<input type="email" name="email" value="<%=v.h(email)%>"><br>

		<span>件名</span><span>必須</span><br> 
		<input name="title"value="<%=v.h(title)%>"><br>
		
		<span>お問い合わせ内容</span><span>必須</span><br>
		<textarea name="inquiry"><%=v.h(inquiry)%></textarea><br>

		<button type="submit">入力内容を確認する</button>
	</form>
</body>
</html>