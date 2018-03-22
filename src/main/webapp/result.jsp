<%--
  Created by IntelliJ IDEA.
  User: HC
  Date: 2018/3/22
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>结果</title>
</head>
<body>
<table border="5">
    <tr>
        <td>课程号</td>
        <td>课程名</td>
        <td>周几</td>
        <td>第几节</td>
        <td>老师</td>
        <td>教室</td>
        <td>上几周</td>
        <td>单周双周</td>
        <td>必修选修</td>
    </tr>
    <c:forEach var="course" items="${list}">
    <tr>
        <td>${course.courseNum}</td>
        <td>${course.course}</td>
        <td>${course.day}</td>
        <td>${course.lesson}</td>
        <td>${course.teacher}</td>
        <td>${course.classroom}</td>
        <td>${course.rawWeek}</td>
        <td>${course.weekModel}</td>
        <td>${course.type}</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
