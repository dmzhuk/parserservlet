
<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 14.07.2014
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>
        phones
    </title>
</head>
<body>
    <jsp:text>Validation: ${validation}</jsp:text><br>
    <c:if test="${!parsingDone}">
        <jsp:text>xml file is corrupted or has a wrong format</jsp:text>
    </c:if>
    <c:if test="${parsingDone}">
        <table border = "1" style="width:500px">
            <tr>
                <th>Name</th>
                <th>Size</th>
                <th>Manufacturer</th>
                <th>OS</th>
                <th>Display type</th>
                <th>SDCart type</th>
                <th>Release date</th>
            </tr>
            <c:forEach items="${phones}" var="phone">
                <tr>
                    <td><c:out value="${phone.getName()}" /></td>
                    <td><c:out value="${phone.getSize()}" /></td>
                    <td><c:out value="${phone.getManuf()}" /></td>
                    <td><c:out value="${phone.getOs()}" /></td>
                    <td><c:out value="${phone.getDisplay()}" /></td>
                    <td><c:out value="${phone.getSd()}" /></td>
                    <td><c:out value="${phone.getManufDate()}" /></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</body>
</html>
