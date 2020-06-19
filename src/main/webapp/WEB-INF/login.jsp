<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login:</title>
</head>
<body>
${accountCreation}
<h2>Login:</h2>
<form action="/login" method="post">
<pre>
User Name: <input type="text" name="userName" />
           ${userNameError}
Password: <input type="password" name="password" />
          ${passwordError}
<input type="submit" value="Login" />
</pre>
</form>
</body>
</html>