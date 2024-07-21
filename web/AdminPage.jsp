<!DOCTYPE html>
<html lang="en">
    <!-- https://cocoshop.vn/ -->
    <!-- http://mauweb.monamedia.net/vanihome/ -->
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Page</title>
        <!-- Font roboto -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
        <!-- Icon fontanwesome -->
        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
        <!-- Reset css & grid sytem -->
        <link rel="stylesheet" href="./assets/css/library.css">
        <!-- Layout -->
        <link rel="stylesheet" href="./assets/css/common.css">
        <!-- index -->
        <link rel="stylesheet" type="text/css" href="./assets/css/product.css">
        <link rel="stylesheet" type="text/css" href="./assets/css/productSale.css">
        <style>
            .manage{
                margin-right:50px; 
                display: inline-block;
                padding: 12px 24px;
                background-color: #007bff; 
                color: #fff; 
                text-decoration: none; 
                border: none; 
                border-radius: 20px; 
                cursor: pointer; 
                font-size: 16px;

            }
        </style>
    </head>

    <body>
        <div class="header scrolling" id="myHeader">
            <div class="grid wide">
                <div class="header__top">
                    <div class="navbar-icon">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>
                    <a href="GetListProductController" class="header__logo">
                        <img src="./assets/logo.png" alt="">
                    </a>
                    <div class="header__search">
                        <%
                            String search = request.getParameter("search");
                            if (search == null) {
                                search = "";
                            }
                        %>
                        <form id="search" action="MainController">
                            <div class="header__search-wrap">

                                <input type="text" class="header__search-input" name="search" placeholder="Tìm kiếm" value="<%= search%>"/>
                                <input type="hidden" name="action" value="SearchProduct"/>
                                <input class="header__search-button" type="submit" value="Search"/>
                                <input class="header__search-icon" type="submit" value=""/>
                                <i ></i>
                            </div>
                        </form>
                    </div>
                    <a class="manage" href="AdminManagePage.jsp">Manage</a>
                    <div class="header__account">
                        <c:set var="fullname" value="${LOGIN_USER.getFullName()}"/>
                        <c:if test="${not empty fullname}">
                            <a href="user.jsp" style="font-size: larger;">${fullname} </a>
                        </c:if>
                        <c:if test="${empty fullname}">
                            <a href="login.jsp" style="font-size: larger;">Login</a>
                        </c:if>
                        <!--
                                                <a href="#my-Login" class="header__account-login">Đăng Nhập</a>
                                                <a href="#my-Register" class="header__account-register">Đăng Kí</a>-->
                    </div>
                    <!-- Cart -->
                    <div class="header__cart have">
                        <a class="btn fa fa-shopping-basket fa-2x" href="viewCart.jsp"></a>
                    </div>
                </div>
            </div>
            <!-- Menu -->
            <div class="header__nav">
                <ul class="header__nav-list">
                    <li class="header__nav-item nav__search">
                        <div class="nav__search-wrap">
                            <input class="nav__search-input" type="text" name="" id="" placeholder="Tìm sản phẩm...">
                        </div>
                        <div class="nav__search-btn">
                            <i class="fas fa-search"></i>
                        </div>
                    </li>

                    <li class="header__nav-item index">
                        <a href="GetListProductController" class="header__nav-link">Trang chủ</a>
                    </li>
                    <li class="header__nav-item">
                        <a href="#" class="header__nav-link">Giới Thiệu</a>
                    </li>
                    <li class="header__nav-item">
                        <a href="#" class="header__nav-link">Sản Phẩm</a>
                    <li class="header__nav-item">
                        <a href="news.html" class="header__nav-link">Tin Tức</a>
                    </li>
                    <li class="header__nav-item">
                        <a href="contact.html" class="header__nav-link">Liên Hệ</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="main">
            <div class="grid wide">
                <div class="main__taskbar">
                    <div class="main__breadcrumb">
                        <div class="breadcrumb__item">
                            <a href="GetListProductController" class="breadcrumb__link">Trang chủ</a>
                        </div>
                        <div class="breadcrumb__item">
                            <a href="GetListProductController" class="breadcrumb__link">Cửa hàng</a>
                        </div>
                        <!--                        <div class="breadcrumb__item">
                                                    <a href="#" class="breadcrumb__link">Hãng DHC</a>
                                                </div>-->
                    </div>
                    <div class="main__sort">
                        <h3 class="sort__title">
                            Hiển thị kết quả theo
                        </h3>
                        <select class="sort__select"> name="" id="">
                            <option value="1">Thứ tự mặc định</option>
                            <option value="2">Mức độ phổ biến</option>
                            <option value="3">Điểm đánh giá</option>
                            <option value="4">Mới cập nhật</option>
                            <option value="5">Giá : Cao đến thấp</option>
                            <option value="6">Giá Thấp đến cao</option>
                        </select>
                    </div>
                </div>
                <div class="productList">
                    <div class="listProduct">
                        <div class="row">  
                            <c:choose>
                                <c:when test="${LIST_PRODUCT == null || LIST_PRODUCT.size() == 0}">
                                    <h3>Not founds</h3>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="product" items="${LIST_PRODUCT}">
                                        <c:if test="${product.quantity>=0}">

                                            <div class="col l-2 m-4 s-6">
                                                <div class="product">
                                                    <div class="product__avt" style="background-image: url(${product.img})">
                                                    </div>
                                                    <div class="product__info">
                                                        <h3 class="product__name">${product.name}</h3>
                                                        <div class="product__price">
                                                            <!--                                                        <div class="price__old">340.000 <span class="price__unit">đ</span></div>-->
                                                            <div class="price__new">${product.price} <span class="price__unit">đ</span></div>
                                                        </div>
                                                    </div>
                                                    <div class="product__sale">
                                                        <!--                                                    <span class="product__sale-percent">22%</span>
                                                                                                            <span class="product__sale-text">Giảm</span>-->
                                                    </div>
                                                    <a href="MainController?action=ViewProductDetails&productID=${product.id}" class="viewDetail">Xem chi tiết</a>
                                                    <a href="MainController?action=Add&cmbQuantity=1&cmbComestics=${product.id}-${product.name.replaceAll("\\s", "")}-${product.price}" " class="addToCart">Thêm vào giỏ</a>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
            <div class="grid wide">
                <div class="row">
                    <div class="col l-3 m-6 s-12">
                        <h3 class="footer__title">Menu</h3>
                        <ul class="footer__list">
                            <li class="footer__item">
                                <a href="#" class="footer__link">Trang điểm</a>
                            </li>
                            <li class="footer__item">
                                <a href="#" class="footer__link">Chăm sóc da</a>
                            </li>
                            <li class="footer__item">
                                <a href="#" class="footer__link">Chăm sóc tóc</a>
                            </li>
                            <li class="footer__item">
                                <a href="#" class="footer__link">Nước hoa</a>
                            </li>
                            <li class="footer__item">
                                <a href="#" class="footer__link">Chăm sóc toàn thân </a>
                            </li>
                        </ul>
                    </div>
                    <div class="col l-3 m-6 s-12">
                        <h3 class="footer__title">Hỗ trợ khách hàng</h3>
                        <ul class="footer__list">
                            <li class="footer__item">
                                <a href="#" class="footer__link">Hướng dẫn mua hàng</a>
                            </li>
                            <li class="footer__item">
                                <a href="#" class="footer__link">Giải đáp thắc mắc</a>
                            </li>
                            <li class="footer__item">
                                <a href="#" class="footer__link">Chính sách mua hàng</a>
                            </li>
                            <li class="footer__item">
                                <a href="#" class="footer__link">Chính sách đổi trả</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col l-3 m-6 s-12">
                        <h3 class="footer__title">Liên hệ</h3>
                        <ul class="footer__list">
                            <li class="footer__item">
                                <span class="footer__text">
                                    <i class="fas fa-map-marked-alt"></i> 319 C16 Lý Thường Kiệt, Phường 15, Quận 11, Tp.HCM
                                </span>
                            </li>
                            <li class="footer__item">
                                <a href="#" class="footer__link">
                                    <i class="fas fa-phone"></i> 076 922 0162
                                </a>
                            </li>
                            <li class="footer__item">
                                <a href="#" class="footer__link">
                                    <i class="fas fa-envelope"></i> phuonggiang150@gmail.com
                                </a>
                            </li>
                            <li class="footer__item">
                                <div class="social-group">
                                    <a href="#" class="social-item"><i class="fab fa-facebook-f"></i>
                                    </a>
                                    <a href="#" class="social-item"><i class="fab fa-twitter"></i>
                                    </a>
                                    <a href="#" class="social-item"><i class="fab fa-pinterest-p"></i>
                                    </a>
                                    <a href="#" class="social-item"><i class="fab fa-invision"></i>
                                    </a>
                                    <a href="#" class="social-item"><i class="fab fa-youtube"></i>  
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="col l-3 m-6 s-12">
                        <h3 class="footer__title">Đăng kí</h3>
                        <ul class="footer__list">
                            <li class="footer__item">
                                <span class="footer__text">Đăng ký để nhận được được thông tin ưu đãi mới nhất từ chúng tôi.</span>
                            </li>
                            <li class="footer__item">
                                <div class="send-email">
                                    <input class="send-email__input" type="email" placeholder="Nhập Email...">
                                    <a href="#" class="send-email__link">
                                        <i class="fas fa-paper-plane"></i>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="copyright">
                <span class="footer__text"> &copy Bản quyền thuộc về <a class="footer__link" href="#"> Phương Giang</a></span>
            </div>
        </div>
        <!-- Script common -->
        <script src="./assets/js/commonscript.js"></script>
    </body>

</html>