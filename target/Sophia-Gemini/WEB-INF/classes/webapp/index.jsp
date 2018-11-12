<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="UTF-8">

    <script>

        function showTask() {
            console.log(1);
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open("GET", "http://localhost:8080/tasks.do");
            xmlHttp.send();
            xmlHttp.onreadystatechange = function (ev) {
                if (xmlHttp.readyState === 4 && xmlHttp.status === 200){
                    alert("yourenwu");
                }
            }
        }
    </script>
</head>
<body>


<h2>Hello World!</h2>

<h2>任务列表</h2>
<ul class="task">

</ul>

<button onclick="showTask()">刷新任务</button>



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
<br>
<form action="/tasks.do">
    <input type="submit"  value="查询所有任务">
</form>
</body>
</html>
