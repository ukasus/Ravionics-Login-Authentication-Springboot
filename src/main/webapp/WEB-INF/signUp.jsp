<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
</head>
<body>
<h2>User Registration:</h2>
<form action="/addUser" method="post">
<pre>
User Name: <input type="text" name="userName" />
           ${userNameError}
Password: <input type="password" name="password" />
          ${passwordError}
<input type="submit" value="Register" />
</pre>
</form>
</body>
</html>