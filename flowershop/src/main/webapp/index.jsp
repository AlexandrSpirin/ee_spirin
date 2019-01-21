<%@ page import="com.accenture.flowershop.be.entity.account.AccountType" %>
<html>
<body>
    <hr>
    <h1 align=center>MAIN PAGE</h1>
    <hr>
    <form action="flowers" method="post">
        <p align=center><input type="submit" name = "submitButton" value="flowers"></p>
    </form>
    <form action="login.jsp">
        <p align=center><input type="submit" name = "submitButton" value="login"></p>
    </form>
    <% if (session.getAttribute("userType") == AccountType.ADMIN) { %>
        <form action="registration.jsp">
            <p align=center><input type="submit" name = "submitButton" value="registration"></p>
        </form>
    <% } %>
</body>
</html>
