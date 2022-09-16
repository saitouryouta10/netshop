<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Model.reviews.db.ReviewsDto"%>
<%@ page import="Model.items.db.ItemsDto"%>
<%@ page import="Model.users.db.LoginDto"%>
<%@ page import="Config.Validation"%>
<%
Validation v = new Validation();
List<ReviewsDto> allReviewDto = (List<ReviewsDto>) request.getAttribute("allReviewsInfo");
ItemsDto itemDto = (ItemsDto) request.getAttribute("itemData");

int itemId = itemDto.getId();
int count = 0;
long aveStar = 0;
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
<script type="text/javascript" src="js/index.js">
	
</script>
<title>HOGEHOGESHOP -レビュー画面-</title>
</head>
<body>


	<header><%@ include file="/include/header.jsp"%></header>
	<h2 class="subtitle-review">口コミ:<%=itemDto.getName()%>	<a class="btn btn--orange btn--radius" href='ExecuteSelectItemServlet?itemId=<%=itemId%>'>商品詳細に戻る</a></h2>
	<div class="container container-review">
		<form action="ExecuteInsertReviewServlet" method="get">
			<input type="hidden" name="itemId" value="<%=itemId%>">
			<div class="input-contents"> 
				<select	name="star">
					<option value="5">★★★★★</option>
					<option value="4">★★★★</option>
					<option value="3">★★★</option>
					<option value="2">★★</option>
					<option value="1">★</option>
				</select>
				<button type="submit" class="btn btn--orange btn--radius">投稿する</button>
				
			</div>
			<div class="review-input-comment">
				<textarea name="comment"></textarea>
			</div>
		</form>
		<%
		if (allReviewDto.size() == 0) {
		%>
		<p>まだ口コミがありません。あなたのレビューを待ってます。</p>
		<%
		}
		%>
		<%
		for (int i = 0; i < allReviewDto.size(); i++) {
			ReviewsDto d = allReviewDto.get(i);
		%>
		<form action="ExecuteDeleteReviewServlet?" method="get">
			<input type="hidden" name="reviewId" value="<%=d.getId()%>">
			<input type="hidden" name="itemId" value="<%=itemDto.getId()%>">
			<div class="review-container">
				<span class="review-name"><%=v.h(d.getNickname())%></span> <span
					class="review-time"><%=v.h(d.getCreated())%></span>
				<%
				// 星関係　---------------------------------
				for (int j = 0; j < d.getStar(); j++) {
				%>
				★
				<%
				}
				%>
				<%
				for (int k = 0; k < (5 - d.getStar()); k++) {
				%>
				☆
				<%
				}
				// ここまで-------------------------------

				if (dto.getId() == d.getUser_id()) {
				%>

				<button type="submit">削除する</button>

				<%
				}
				%>


				<div>
					<%=v.h(d.getComment())%>
				</div>
			</div>
		</form>
		<%
		}
		%>
	</div>
	<footer><%@ include file="/include/footer.jsp"%></footer>
</body>
</html>