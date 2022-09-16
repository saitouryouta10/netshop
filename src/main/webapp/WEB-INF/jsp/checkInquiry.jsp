<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Model.inquiry.db.InquiryDto" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="Config.Validation" %>
<%HashMap<String, String> i = (HashMap<String, String>)session.getAttribute("inquiryData"); %>

<%
String name = i.get("name");
String email = i.get("email");
String title = i.get("title");
String inquiry = i.get("inquiry");

Validation v = new Validation();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGESHOP -お問い合わせ内容確認-</title>
</head>
<body>
<h1>お問い合わせ内容確認</h1>

<span>お名前</span><br>
<span><%= v.h(name) %>さん</span>

<span>メールアドレス</span><br>
<span><%= v.h(email) %></span>

<span>件名</span><br>
<span><%= v.h(email) %></span>

<span>お問い合わせ内容</span><br>
<span><%= v.h(inquiry) %></span>

<a href="SendInquiryServlet">戻る</a>
<a href="ExecuteInsertServlet">送信する</a>

</html>