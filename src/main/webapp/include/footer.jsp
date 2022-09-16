<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="footer_list">
    <ul class="list_1">
    <% if (session.getAttribute("loginData") == null ) {%>
        <li><a href="LoginServlet">ログイン</a></li>
    <%} else { %>
        <li><a href="LogoutServlet">ログアウト</a></li>
    <%} %>
        <li><a href="HomeServlet">トップページへ</a></li>
    </ul>
    <ul class="list_2">
        <li><a href="SendInquiryServlet">お問い合わせ</a></li>
        <li><a href="SendMemberInfoServlet">会員情報</a></li>
    </ul>
    <ul class="list_3">
        <li><a href="ExecuteSelectFavoriteServlet">お気に入り</a></li>
        <li><a href="ExecuteSelectCartServlet">カート</a></li>
    </ul>
</div>
<p class="copyright">© 2022 Kensyu_netshop</p>
</body>
</html>