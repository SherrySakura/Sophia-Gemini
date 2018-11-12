<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="UTF-8">

    <script>

        function showTask() {
            console.log(1);
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open("GET", "http://localhost:8080/tasks.do");
            xmlHttp.setRequestHeader("Accept", "text/html,*/*;q=0.8");
            console.log("setHeader success");
            xmlHttp.send();
            xmlHttp.onreadystatechange = function (ev) {
                if (xmlHttp.readyState === 4 && xmlHttp.status === 200){
                    var data = xmlHttp.responseText;
                    var jsonData = JSON.parse(data);
                    document.write(jsonData);
                    alert(jsonData);
                }
            }
        }
    </script>
</head>
<body>


<h2>Hello World!</h2>

<h2>任务列表</h2>
<ul class="task">
    <li><a href="/choose.do?taskUID=lfg20181110124000">location_info</a></li>
    <li><a href="/choose.do?taskUID=energency20190101090500">accident_info</a></li>
</ul>

<button onclick="showTask()">刷新任务</button>

<form action="/login.do">
    账号:<input type="text" name="id"><br>
    密码:<<input type="password" name="passwordHash">
    <input type="submit" value="登录">
</form>
<a href="regist.html">注册</a>
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
<form action="/tasks.do" method="get">
    <input type="submit"  value="查询所有任务">
</form>
</body>
</html>
