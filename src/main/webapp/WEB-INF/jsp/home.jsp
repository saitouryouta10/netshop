<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Model.items.db.ItemsDto"%>
<%@ page import="java.util.*"%>
<%@ page import="Config.Validation"%>

<%
List<ItemsDto> dtoList = (List<ItemsDto>) request.getAttribute("allItemInfo");
Validation v = new Validation();
ItemsDto itemDto;
boolean flag = true;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="css/index.css">

<title>HOGEHGOE SHOP</title>
</head>
<body>
	<header><%@ include file="/include/header.jsp"%></header>
	
	<% if (dtoList.size() == 0) { %>
	<div class="error-contents">
		<img src="images/nayami.png">
		<p>該当商品はありませんでした</p>
	</div>
	<% } %>
	<div class="items-table">
	<table class="items-list">
		<% for (int i = 1; i <= dtoList.size(); i++) { %>
		<% itemDto = dtoList.get(i-1); %>  
		<% if (flag){ %> 
		<% flag = false; %>
			<tr>
		<% } %>
				<td>
					<div class="one-block">
						<a href="ExecuteSelectItemServlet?itemId=<%=itemDto.getId()%>">
							<% if (itemDto.getPicture() != null) { %> 
							<img src="<%="img/" + v.h(itemDto.getPicture())%>" width="150px;" height="150px;"> 
							<% } else { %>
							<img src="img/noimage.png" width="150px; " height="150px;">
							<% } %>
							<br>
							<span class="item-name"><%=v.h(itemDto.getName())%></span><br>
							<span class="item-yen"><%=v.h(String.valueOf(itemDto.getPrice()))%>円</span>
						</a>
					</div>
				</td>
		<% if (i%4 == 0 ){ %>
			</tr>
		<% flag = true; %>
		<% } %>
		<% } %>
		</table>
	</div>
	
	
	
	
	
	
	
	
	
	<footer><%@ include file="/include/footer.jsp"%></footer>
</body>
</html>