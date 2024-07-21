<%--
    Document   : admin
    Created on : May 31, 2023, 2:27:58 PM
    Author     : ADMIN
--%>

<%@page import="java.util.List"%>
<%@page import="sample.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                margin: 0;
                padding: 20px;
            }

            h1 {
                text-align: center;
                color: #333333;
                margin-bottom: 20px;
            }



            form {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-bottom: 20px;
            }

            input[type="text"],
            input[type="submit"] {
                padding: 10px;
                font-size: 14px;
                border-radius: 3px;
            }

            input[type="submit"] {
                background-color: #0066cc;
                color: #ffffff;
                border: none;
                margin-left: 10px;
                cursor: pointer;
                transition: background-color 0.3s ease-in-out;
            }

            input[type="submit"]:hover {
                background-color: #004c99;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            th,
            td {
                padding: 23px;
                text-align: left;
            }

            th {
                background-color: #f9f9f9;
                color: #333333;
            }

            tbody tr:nth-child(even) {
                background-color: #ffffff;
            }

            td input[type="text"] {
                width: 100%;
                padding: 5px;
                border: 1px solid black;
                border-radius: 3px;
                font-size: 14px;
            }

            td a {
                color: #cc0000;
                text-decoration: none;
                font-weight: bold;
                cursor: pointer;
            }

            p.error {
                text-align: center;
                color: #cc0000;
                margin-top: 10px;
            }
            .store {
                display: inline-block;
                background-color: #4CAF50;
                color: #ffffff;
                border: none;
                border-radius: 5px;
                padding: 20px 200px;
                font-size: 16px;
                font-weight: bold;
                text-transform: uppercase;
                cursor: pointer;
                transition: background-color 0.3s ease-in-out;
                margin-left: 670px;
            }
            .store:hover {
                background-color: #166b16;
            }
            input[value="Logout"]{
                display: inline-block;
                background-color: #f44336;
                color: #ffffff;
                border: none;
                border-radius: 5px;
                padding: 12px 200px;
                font-size: 16px;
                font-weight: bold;
                text-transform: uppercase;
                cursor: pointer;
                transition: background-color 0.3s ease-in-out;
            }

            input[value="Logout"]:hover {
                background-color: #d32f2f;
            }
            .search{
                display: block;
            }
            #inp{
                text-align: center;
            }
            #text{

            }
        </style>
    </head>

    <body>
        <% UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.html");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <a class="store" href="MainController?action=ShoppingPageAdmin">Manage Store</a><br/>
        <h1>Welcome:${LOGIN_USER.getFullName()} </h1> 
        <form action="MainController">
            <input type="submit" name="action" value="Logout">
        </form>
        <form action="MainController" class="search">
            <div id="inp">
                <input type="text" name="search" placeholder="Type UserName here" value="<%= search%>">
                <input type="submit" name="action" value="Search">
            </div>
        </form>

        <% List<UserDTO> list = (List<UserDTO>) request.getAttribute("LIST_USER");
            if (list != null && list.size() > 0) { %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>User ID</th>
                    <th>Full Name</th>
                    <th>Role ID</th>
                    <th>Password</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <% int count = 1;
                    for (UserDTO user : list) {
                %>
                <tr>
            <form action="MainController">
                <td><%= count++%></td>
                <td><%= user.getUserID()%></td>
                <td>
                    <input id="text" type="text" name="fullName" value="<%= user.getFullName()%>" required="">
                </td>
                <td>
                    <input id="text" type="text" name="roleID" value="<%= user.getRoleID()%>" required="">
                </td>
                <td><%= user.getPassword()%></td>
                <td>

                    <input type="hidden" name="userID" value="<%= user.getUserID()%>">
                    <input type="hidden" name="search" value="<%= search%>">
                    <input type="submit" name="action" value="Update">

                </td>
                <td>
                    <a href="MainController?search=<%= search%>&action=Delete&userID=<%= user.getUserID()%>">Delete</a>
                </td>
                </tr>
            </form>
            <% } %>
        </tbody>
    </table>
    <% String error = (String) request.getAttribute("ERROR");
        if (error == null) {
            error = "";
        }%>
    <p class="error"><%= error%></p>
    <% }%>
</body>
</html>
