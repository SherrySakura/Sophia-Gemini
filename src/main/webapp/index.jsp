<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<h2>Hello World!</h2>
<form action="/upload.do" method="get">
    交易发起者：<input type="text" name="ori">
    交易目标：<input type="text" name="des">
    交易数量：<input type="text" name="number"><br>
    <input type="submit" name="id" value="转账"><br>

</form>
<form action="/query.do">
    查询对象：<input type="text" name="men" id="men">
    <input type="submit">
</form>

<form action="/transaction.do">
    <input type="submit" value="查看交易">
</form>
</body>
</html>
