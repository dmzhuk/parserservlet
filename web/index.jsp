<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 14.07.2014
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
      <form name="Simple" action="ParserServlet" method="POST">
          <input type="checkbox" name = "validateCheckbox"/>Validate xml document before parsing<br>
          <input type="submit" name="parseButton" value="SAX"/>
          <input type="submit" name="parseButton" value="DOM"/>
          <input type="submit" name="parseButton" value="STAX"/>
      </form>
  </body>
</html>
