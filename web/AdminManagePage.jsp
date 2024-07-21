<%-- 
    Document   : AdminManagePage
    Created on : Mar 18, 2024, 12:38:53 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Manage Page</title>
        <style>
            body, html {
                height: 100%;
                margin: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                background-color: #f4f4f4; /* Màu nền giống với bo tròn */
            }

            .button-container {
                text-align: center;
            }

            input[type="submit"] {
                padding: 15px 30px;
                font-size: 18px;
                margin: 10px; /* Khoảng cách giữa các nút */
                border-radius: 20px; /* Bo tròn 4 góc */
                background-color: #007bff; /* Màu nền của nút */
                color: #fff; /* Màu chữ của nút */
                border: none; /* Loại bỏ viền */
                cursor: pointer; /* Đổi con trỏ thành hình bàn tay khi di chuột qua */
            }

            input[type="submit"]:hover {
                background-color: #0056b3; /* Màu nền khi hover */
            }
        </style>
    </head>
    <body>
        <div class="button-container">
            <form action="MainController">
                <input type="submit" name="action" value="Manage User" /><br/>
            </form>
            <form action="MainController">
                <input type="submit" name="action" value="Manage Product" /><br/>
            </form>
            <form action="MainController">
                <input type="submit" name="action" value="ManageOrder" /><br/>
            </form>     
            <form action="MainController">
                <input type="submit" name="action" value="Store" /><br/>
            </form>
        </div>
    </body>
</html>
