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
        <link rel="stylesheet" href="/pintuer.css">
        <link rel="stylesheet" href="/admin.css">
        <script src="/jquery.js"></script>
        <script src="/pintuer.js"></script>
        <link href="/bootstrap.css" rel="stylesheet">
        <style type="text/css">
            body{text-align:center}
            table{width:100%;height:7%;align:center;align-content: center}
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
    <body>
        <table class="hovertable">
            <tr>
                <th>ID</th>
                <th>岗位名称</th>
                <th>岗位简介</th>
                <th>数量</th>
                <th>具体要求</th>
                <th>备注</th>
            </tr>
        </table>


    </body>
</html>