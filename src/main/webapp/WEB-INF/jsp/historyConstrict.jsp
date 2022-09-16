<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOGEHOGESHOP -絞り込み-</title>
</head>
<body>
	<a href="ExecuteHistoryServlet">戻る</a>
	<div class="container">
		<form action="ExecuteConstrictServlet" method="get">
			<table>
				<tr>
					<td>購入時期</td>
					<td><input type="radio" name="time" id="1" value="1">
						<label for="1">新しい順</label><br> 
						<input type="radio"name="time" id="2" value="2"> 
						<label for="2">古い順</label><br>
					</td>
				</tr>
				<tr>
					<td>値段</td>
					<td><input type="radio" name="money" id="4" value="4">
						<label for="4">～1000円</label><br> <input type="radio"
						name="money" id="5" value="5"> <label for="5">1001円～5000円</label><br>
						<input type="radio" name="money" id="6" value="6"> <label
						for="6">5001円～10000円</label><br> <input type="radio"
						name="money" id="7" value="7"> <label for="7">10001円～50000円</label><br>
						<input type="radio" name="money" id="8" value="8"> <label
						for="8">50001円～</label></td>
				</tr>
			</table>
			<button type="submit">適用する</button>
		</form>

	</div>
</body>
</html>