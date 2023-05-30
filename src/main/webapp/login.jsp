<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login Page</title>
</head>
<body>
<div align="center">
    <h1>User Login Form</h1>
    <form action="user-login" method="post">
        <table style="with: 100%">
            <tr>
                <td>Identity Number</td>
                <td><input type="text" name="identity_number" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>

        </table>

        <button type="submit" value="Submit" class="btn btn-primary">
            Submit
        </button>

    </form>
</div>
</body>
</html>

