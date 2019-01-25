<html>
<body>
    <hr>
    <h1 align=center>REGISTRATION</h1>
    <hr>
    <br><br>
    <form action="registration" method="post">
	    <p align=center>Please enter login:</p>
		<p align=center><input type="text" name = "login" size="20px"></p>
		<br>
		<p align=center>Please enter password:</p>
		<p align=center><input type="password" name = "passwordOne" size="20px"></p>
		<p align=center>Please enter password one more time:</p>
		<p align=center><input type="password" name = "passwordTwo" size="20px"></p>
		<hr>
		<p align=center>Please enter first name:</p>
        <p align=center><input type="text" name = "firstName" size="20px"></p>
        <p align=center>Please enter middle name:</p>
        <p align=center><input type="text" name = "middleName" size="20px"></p>
        <p align=center>Please enter last name:</p>
        <p align=center><input type="text" name = "lastName" size="20px"></p>
        <br>
        <p align=center>Please enter e-mail:</p>
        <p align=center><input type="text" name = "email" size="20px"></p>
        <p align=center>Please enter phone number:</p>
        <p align=center><input type="text" name = "phoneNumber" onkeypress = "return event.charCode >= 48" +
            "&& event.charCode <= 57" size="20px"></p>
        <hr>
        <p align=center>Please enter money:</p>
        <p align=center><input type="text" name = "money" onkeypress = "return event.charCode >= 48" +
            "&& event.charCode <= 57" size="20px"></p>
        <p align=center>Please enter discount:</p>
        <p align=center><input type="text" name = "discount" onkeypress = "return event.charCode >= 48" +
            "&& event.charCode <= 57" size="20px"></p>
        <hr>
		<p align=center><input type="submit" name = "submitRegistrationButton" value="Register"></p>
	</form>

    <form action='index.jsp' style='display:inline-block; margin-top:0; margin-bottom:0; margin-left:18'>
    	<p align=center><input type=submit name='mainPageButton' value='Main page'/></p>
    </form>
</body>
</html>
