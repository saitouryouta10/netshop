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
		<form action="CheckDeleteItemServlet" method="post" onsubmit="return deleteAlert()">
		<tr>
			<td><input type="hidden" name="itemId" value="<%=d.getId()%>"></td>
			<td><%=d.getCreated()%></td>
			<td><%=d.getName()%></td>
			<td><%=d.getPrice()%></td>
			<td><%=d.getRetention_stock() - d.getStock()%></td>
			<td><%=d.getStock()%></td>
			<td><button type="submit">削除</button></td>
		</tr>
		</form>
		<%
		}
		%>
	</table>

	<a>戻る</a>
	
<script type="text/javascript">
	const deleteAlert = () => {
		if (confirm("本当に削除してもよろしいですか？")){
			return true;
		} else {
			return false;
		} 
			
		}
</script>
</body>
</html>