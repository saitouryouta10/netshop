<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Model.items.db.ItemsDto"%>
<%
List<ItemsDto> dtoList = (List<ItemsDto>) request.getAttribute("itemInfo");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面 -商品削除-</title>
</head>
<body>
	<p>管理画面</p>
	<a>ログアウト</a>
	<a>TOP</a>
	<p>商品削除</p>

	<P>出品商品情報</P>
	<p>商品一覧</p>
	<table>
		<%
		for (int i = 0; i < dtoList.size(); i++) {
			ItemsDto d = dtoList.get(i);
		%>
		<tr>
			<td><%=d.getCreated()%></td>
			<td><%=d.getName()%></td>
			<td><%=d.getPrice()%></td>
			<td><%=d.getRetention_stock() - d.getStock()%></td>
			<td><%=d.getStock()%></td>
		</tr>
		<%
		}
		%>
	</table>

	<a href="SendAdminServlet">戻る</a>
<a href="DeleteAdminServlet">削除する</a>
<a href="InsertAdminServlet">追加する</a>
</body>
</html>