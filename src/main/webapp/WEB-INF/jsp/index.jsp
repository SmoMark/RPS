<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>HR人才推荐系统</title>
  </head>
  <script language="javascript" ></script>
  <style type="text/css">
    <!--
    * {
      margin:0px;
      padding:0px;
    }
    html, body{
      height:100%;
      overflow: hidden;

    }
    html>body{		/*-- for !IE6.0 --*/
      width: auto;
      height: auto;
      position: absolute;
      top: 0px;
      left: 0px;
      right: 0px;
      bottom: 0px;

    }
    body {
      border:0px solid #ffffff;
      background-color:#ffffff;
    }
    #mainDiv {
      width: 100%;
      height: 100%;
      padding:60px 0px 25px 0px;;
    }
    #centerDiv{
      width: 100%;
      height:20%;
      background-color:#FFFFFF;
    }
    #mainDiv>#centerDiv{		/*-- for !IE6.0 --*/
      width: auto;
      height: auto;
      position: absolute;
      top: 70%;
      left: 0px;
      right: 0px;
      bottom: 10%;

    }
    #topDiv{
      width:100%;
      height:70%;
      line-height:450px;
      background:url("images/background -1.jpg") repeat-x;
      position:absolute;
      top:0px;
      overflow:hidden;
    }
    #topDiv h1{
      text-align:center;
      list-style:none;
      font-size:72px;
    }
    #bottomDiv{
      width:100%;
      height:10%;
      background-color:#CCCCFF;
      position:absolute;
      bottom:0px;
      bottom:-1px;		 /*-- for IE6.0 --*/
    }
    #left ul{
      list-style:none;
      font-size:12px;
      margin:50px 0 0 8px;
    }
    #left ul li a{
      display:block;
      width:140px;
      height:25px;
      line-height:25px;
      background:url("images/background.jpg") repeat-x;
      color:#FFFFFF;
      direction:none;
      text-align:center;
      border-bottom:1px #000066 solid;
      border:1px #06597D solid;
    }
    #left ul li a:hover{

      background:url("images/background.jpg") 0px 25px;
      color:#99FFCC;
      direction:none;
      text-align:center;
      border-bottom:1px #000066 solid;
    }
    #form{
      width:90%;
      height:99%;
      margin:0 auto;
      _margin-left:2%;
    }

    #sub1{
      width:150px;
      height:80px;
      line-height:80px;
      background-color:#CCCCFF;
      border:none;
      border-radius:8px;
      text-align:center;
      color:#fff;
      font-size:24px;
      margin:19px 5px 5px 280px;
    }
    #sub1:hover{border:solid 1px #333;}
    #sub2{
      width:150px;
      height:80px;
      line-height:80px;
      background-color:#CCCCFF;
      border:none;
      border-radius:8px;
      text-align:center;
      color:#fff;
      font-size:24px;
      margin:19px 5px 5px 100px;
    }
    #sub2:hover{border:solid 1px #333;}
    #sub3{
      width:150px;
      height:80px;
      line-height:80px;
      background-color:#CCCCFF;
      border:none;
      border-radius:8px;
      text-align:center;
      color:#fff;
      font-size:24px;
      margin:19px 5px 5px 100px;
    }
    #sub3:hover{border:solid 1px #333;}
    -->
  </style>
  <body>
  <div id="mainDiv">
    <div id="topDiv">
       <h1 align="center"><font color="#FFFFFF">人才推荐系统</font></h1>
    </div>
    <div id="centerDiv">
        <div id="form">
        <form action="/login/selectLogin" method="get">
          <input type="submit" name="btn" value="HR入口" id="sub1" onclick="location.href='/hr'">
          <input type="submit" name="btn" value="推荐人入口" id="sub2" onclick="location.href='/re'">
          <input type="submit" name="btn" value="管理员入口" id="sub3" onclick="location.href='/ad'">
        </form>
        </div>

      </div>
    <div id="bottomDiv">

    </div>
  </div>

  </body>
</html>
