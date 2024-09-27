<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br/>
<br/>
<form action="${pageContext.request.contextPath}/api/saving-products/fetch-saveDeposit" method="GET">
  <button type="submit" style="padding: 10px; background-color: lightblue;">API에서 예금 데이터 받아오기</button>
</form>
<form action="/api/stock/fetch" method="get">
  <button type="submit">주식 DB 추가하기</button>
</form>
<br/>
<br/>
<form action="${pageContext.request.contextPath}/api/saving-products/fetch-saveSaving" method="GET">
  <button type="submit" style="padding: 10px; background-color: lightblue;">API에서 적금 데이터 받아오기</button>
</form>
<br/>
<br/>
<form action="${pageContext.request.contextPath}/api/fund-products/upload" method="post" enctype="multipart/form-data">
<label for="file">CSV 파일 선택:</label>
<input type="file" id="file" name="file" accept=".csv">
<br><br>
<button type="submit" style="padding: 10px; background-color: lightblue;">파일 업로드</button>
</form>
<!-- 채권 데이터 DB에 저장하는 버튼 -->
<form action="${pageContext.request.contextPath}/api/bond/fetch-save" method="GET">
  <button type="submit" style="padding: 10px 20px;">채권 데이터 받아오기</button>
</form>
  <form action="/api/portfolio" method="GET">
  <label for="portfolioID">Portfolio ID:</label>
  <input type="text" id="portfolioID" name="portfolioID" required>
  <button type="submit">Submit</button>
</body>
</html>