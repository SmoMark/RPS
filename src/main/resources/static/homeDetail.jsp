<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>信息展示</title>
        <!-- CSS -->
        <link rel="stylesheet" href="../css/pintuer.css">
        <link rel="stylesheet" href="../css/admin.css">
        <script src="/jquery.js"></script>
        <script src="/pintuer.js"></script>
        <link href="/bootstrap.css" rel="stylesheet">
        <style>
            body{text-align:center}
            table{width:100%;height:100%}
        </style>
    </head>

    <div class="title">招聘信息</div>
    <style type="text/css">
        table.hovertable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #999999;
            border-collapse: collapse;
        }
        table.hovertable th {
            background-color:#c3dde0;
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }
        table.hovertable tr {
            background-color:#d4e3e5;
        }
        table.hovertable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }
    </style>
    <!--用count进行统计，有顺序的显示-->
    <table class="hovertable" border="1" align="center" >
        <tr align="center" valign="middle">
            <th>ID</th>
            <th>岗位名称</th>
            <th>岗位简介</th>
            <th>数量</th>
            <th>具体要求</th>
            <th>备注</th>
        </tr>
        <tbody th:remove="all-but-first">
        <tr th:each="job:${jobList}">
        <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center" valign="middle">
            <td th:text="${jobStat.count}">1</td>
            <td th:text="${job.name}">市场经理</td>
            <td th:text="${job.description}">市场部负责人</td>
            <td th:text="${job.count}">2</td>
            <td th:text="${job.detail}">啦啦啦</td>
            <td th:text="${job.else}">年薪10万</td>
        </tr>
        <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';" align="center" valign="middle">
            <td>Item 1A</td>
            <td>Item 1B</td>
            <td>Item 1C</td>
            <td>Item 1D</td>
            <td>Item 1E</td>
            <td>Item 1F</td>
        </tr>
        <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"align="center" valign="middle">
            <td>Item 2A</td>
            <td>Item 2B</td>
            <td>Item 2C</td>
            <td>Item 2D</td>
            <td>Item 2E</td>
            <td>Item 2F</td>
        </tr>
        <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';"align="center" valign="middle">
            <td>Item 3A</td>
            <td>Item 3B</td>
            <td>Item 3C</td>
            <td>Item 3D</td>
            <td>Item 3E</td>
            <td>Item 3F</td>
        </tr>
        </tbody>
    </table>
</html>