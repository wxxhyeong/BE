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
<form action="${pageContext.request.contextPath}/api/saving-products/fetch-save" method="GET">
  <button type="submit" style="padding: 10px; background-color: lightblue;">API에서 예/적금 데이터 받아오기</button>
</form>
</body>
</html>