<%@ page import="com.accenture.flowershop.be.entity.account.AccountType" %>
<% session.invalidate(); %>
<html>
<body>
    <hr>
    <h1 align=center>MAIN PAGE</h1>
    <hr>
    <form action="login.jsp">
        <p align=center><input type="submit" name = "submitButton" value="login"></p>
    </form>
</body>
</html>
