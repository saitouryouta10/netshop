<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Model.items.db.ItemsDto"%>
<%@ page import="Model.reviews.db.ReviewsDto"%>
<%@ page import="Config.Validation"%>
<%@ page import="Model.users.db.LoginDto"%>
<%
Validation v = new Validation();
ItemsDto itemDto = (ItemsDto) request.getAttribute("itemData");
List<ReviewsDto> reviewDto = (List<ReviewsDto>) request.getAttribute("reviewsInfo");
List<ReviewsDto> allReviewDto = (List<ReviewsDto>) request.getAttribute("allReviewsInfo");

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

<title>HOGEHOGE SHOP -詳細画面-</title>
</head>
<body>
	<header><%@ include file="/include/header.jsp"%></header>
	<form action="ExecuteInsertCartServlet" method="get">
		<div class="container">
			<div class="infomation">
				<div class="imageContainer">
					<%
					if (itemDto.getPicture() != null) {
					%>
					<img src="<%="img/" + itemDto.getPicture()%>" width="200px">
					<%
					} else {
					%>
					<img src="img/noimage.png" width="200px;">
					<%
					}
					%>
				</div>

				<div class="anoter-block">
					<%
					//星関係の処理------------------------------------
					if (allReviewDto != null && allReviewDto.size() != 0) {
						for (int i = 0; i < allReviewDto.size(); i++) {

							ReviewsDto d = allReviewDto.get(i);
							count += d.getStar();
						}
						aveStar = Math.round(count / allReviewDto.size());
					}
					%>
					<%
					if (aveStar != 0) {
						for (int r = 0; r < aveStar; r++) {
					%>
					★
					<%
					}
					%>
					<%
					for (int s = 0; s < (5 - aveStar); s++) {
					%>
					☆
					<%
					}
					%>
					<%
					}
					//ここまで--------------------------------------------
					%>

					<h3><%=itemDto.getName()%></h3>

					<p>
						\<%=itemDto.getPrice()%>
					</p>

					<%
					if (itemDto.getStock() == 0) {
					%>
					<p>在庫切れです</p>
					<%
					} else {
					%>
					<span>購入数選択<br></span> <select name="stock" style="width: 160px;">
						<%
						for (int i = 1; i <= itemDto.getStock(); i++) {
						%>
						<option value="<%=i%>"><%=i%></option>
						<%}%>
					</select>
					<%
					}
					%>
				</div>
				<div class="setButton">
					<input type="hidden" name="itemId" value="<%=itemId%>">
					<button type="submit" class="btn btn-warning" id="1">カートに入れる</button>

					<a class="btn btn-danger" href="ExecuteSelectCartServlet"
						id="button">購入へ進む</a><br>
					<%
					if (request.getAttribute("already") == null) {
					%>
					<a class="btn btn-info"
						href="ExecuteInsertFavoriteServlet?itemId=<%=itemId%>"
						id="button">お気に入り</a>
					<%
					} else {
					%>
					<a class="btn btn-secondary"
						href="ExecuteDeleteFavoriteServlet?itemId=<%=itemId%>"
						id="button">お気に入り解除</a>
					<%
					}
					%>
				</div>

			</div>
			<div class="infomation-info">
				<p>商品説明</p>
				<div class="setumei">
					<span><%=itemDto.getSetumei()%></span>
				</div>

				<p>商品詳細</p>
				<div class="syousai">
					<span><%=itemDto.getSyousai()%></span>
				</div>
			</div>


			<p>口コミ</p>
			<div class="review">

				<%
				if (reviewDto != null && reviewDto.size() != 0) {
					for (int i = 0; i < reviewDto.size(); i++) {
						ReviewsDto d = reviewDto.get(i);
				%>
				<div class="review-container">
					<span class="review-name"><%=v.h(d.getNickname())%></span> <span
						class="review-time"><%=v.h(d.getCreated())%></span>
					<%
					//星関係----------------------------------
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
					//ここまで----------------------------------
					%>
					<div class="comment">
						<%=v.h(d.getComment())%>
					</div>
				</div>

				<%
				}
				} else {
				%>
				<div class="comment">
					<p>口コミはまだ投稿されていません</p>
				</div>
				<%
				}
				%>
			</div>
			<div class="review-input">
				<a href="ExecuteSelectReviewServlet?itemId=<%=itemId%>">口コミを投稿する</a>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		var stock =
	<%=itemDto.getStock()%>
		var button = document.getElementById("1");
		if (stock == 0) {
			button.disabled = true;
		}
	</script>


	<footer><%@ include file="/include/footer.jsp"%></footer>
</body>
</html>