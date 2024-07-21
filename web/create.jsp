<%-- 
    Document   : create
    Created on : Jun 10, 2023, 2:20:59 PM
    Author     : ADMIN
--%>

<%@page import="sample.users.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                padding: 20px;
            }

            form {
                max-width: 400px;
                margin: 0 auto;
                padding: 20px 80px 20px 50px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            }

            input[type="text"],
            input[type="password"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            input[type="submit"],
            input[type="reset"] {
                padding: 10px 20px;
                background-color: #0066cc;
                color: #fff;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }

            input[type="submit"]:hover,
            input[type="reset"]:hover {
                background-color: #0056b3;
            }

            .error-message {
                color: red;
                font-style: italic;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) {
                userError = new UserError();
            }
        %>
        <form action="MainController" method="POST">
            User ID <input type="text" name="userID" required=""/><%=userError.getUserIDError()%>
            </br>Full Name<input type="text" name="fullName" required=""/><%=userError.getFullNameError()%>
            </br>Role ID<input type="text" name="roleID" value="US" readonly=""/>
            </br>Password<input type="password" name="password" required=""/>
            </br>Confirm<input type="password" name="confirm" required=""/><%=userError.getConfirmError()%>
            </br><input type="submit" name="action" value="Create"/>
            <input type="reset" value="Reset"/>
            <a href="MainController?action=Login">Login</a><br/>
            <%
                String error = userError.getError();
            %>
            <p class="error-message"><%=error%></p>
        </form>
    </body>
</html>
