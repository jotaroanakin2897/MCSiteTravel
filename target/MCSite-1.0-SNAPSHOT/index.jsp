<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>

<body>

<jsp:include page="base.jsp"></jsp:include>

<br>

<head>
    <meta charset="UTF-8">
    <title>TRAVEL GAME</title>
    <link rel="stylesheet"  href="Menu.css">
</head>
<body>

<div style="background: url(../images/Travel-game.png)">
    <br><br><br><br><br>
    <div id="allthethings">
        <div id="left"></div>
        <a href="${pageContext.request.contextPath}/login.jsp" >
            <div id="single" ><p>GIOCA</p></div>
        </a>

        <br><br>
        <a href="${pageContext.request.contextPath}/classifica">
            <div id="options"><p>CLASSIFICA</p></div>
        </a>
        <br>
        <a href="${pageContext.request.contextPath}/info.jsp" >
            <div id="credits"><p>INFO</p></div>
        </a>
        <div id="right"></div>


    </div>


</body>
</html>