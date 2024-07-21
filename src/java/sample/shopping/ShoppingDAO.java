/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import sample.users.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author ADMIN
 */
public class ShoppingDAO {

    private static final String SEARCH = "SELECT productID, productName, productPrice, productQuantity, productImg, Status FROM tblProduct WHERE productName like ? ";
    private static final String UPDATE = "UPDATE tblProduct SET  productName = ?, productPrice = ?, productQuantity = ?, productImg = ? WHERE productID = ? ";
    private static final String DELETE = "UPDATE tblProduct SET Status = ? WHERE productID = ? ";
    private static final String INSERT = "INSERT INTO tblProduct (productID, productName, productPrice, productQuantity, productImg,Status) VALUES (?,?,?,?,?,?) ";
    private static final String CHECKPRODUCTID = "SELECT productID FROM tblProduct WHERE productID = ? ";

    private static final String GETPRODUCT = "SELECT productPrice FROM tblProduct WHERE productID = ? ";
    private static final String GETPRODUCT_DETAILS = "SELECT productName, productPrice, productQuantity, productImg FROM tblProduct WHERE productID = ? ";

    private static final String GETLISTPRODUCT = "SELECT productID, productName, productPrice, productQuantity, productImg, Status FROM tblProduct WHERE productName like ? ";
    private static final String INSERTORDERDETAILS = "INSERT INTO tblOrderDetail (orderDetailID, orderID, productID, producPrice, productQuantity) VALUES (?,?,?,?,?) ";
    private static final String INSERTORDER = "INSERT INTO tblOrder (orderID, userID, date, total) VALUES (?,?,?,?) ";
    private static final String UPDATEPRODUCT = "UPDATE tblProduct SET productQuantity = ? WHERE productID = ? ";
    private static final String GETORDER_MANAGE = "SELECT O.orderID, O.userID, U.fullName, O.date, O.total \n"
            + "FROM tblOrder o\n"
            + "LEFT join tblUsers u\n"
            + "ON o.userID = u.userID";
    private static final String SEARCH_ORDER_MANAGE = "SELECT O.orderID, O.userID, U.fullName, O.date, O.total \n"
            + "FROM tblOrder o\n"
            + "LEFT join tblUsers u\n"
            + "ON o.userID = u.userID\n"
            + "WHERE o.userID like ?";
        private static final String SEARCH_ORDER_USER = "SELECT O.orderID, O.userID, U.fullName, O.date, O.total \n"
            + "FROM tblOrder o\n"
            + "LEFT join tblUsers u\n"
            + "ON o.userID = u.userID\n"
            + "WHERE o.userID = ?";
    private static final String GETORDERDETAILS = "SELECT od.orderID, od.productID, p.productName, od.producPrice, od.productQuantity\n"
            + "FROM tblOrderDetail od\n"
            + "LEFT join tblProduct p\n"
            + "ON od.productID = p.productID\n"
            + "WHERE od.orderID = ?";
    private static final String GET_QUANRITY_PRICE = "SELECT od.productQuantity, od.producPrice\n"
            + "FROM tblOrderDetail od\n"
            + "WHERE OD.orderID = ? AND productID = ?";
    private static final String UPDATE_ORDER_DETAILS = "UPDATE tblOrderDetail SET productQuantity = ? WHERE orderID = ? AND productID = ? ";

    private static final String GET_QUANTITY_PRODUCT = "SELECT productQuantity FROM tblProduct WHERE productID = ? ";

    private static final String GET_TOTAL = "SELECT o.total\n"
            + "FROM tblOrder o\n"
            + "WHERE o.orderID = ?";
    private static final String SET_TOTAL = "UPDATE tblOrder SET total = ?\n"
            + "WHERE orderID = ?";
    private static final String DELETE_ORDER_DETAILS = "DELETE tblOrderdetail WHERE orderID = ? AND productID = ? ";
    private static final String DELETE_ORDER = "DELETE tblOrder WHERE orderID = ?";

    private static final String SEARCH_ORDER_DETAILS = "SELECT od.orderID, od.productID, p.productName, od.producPrice, od.productQuantity\n"
            + "FROM tblOrderDetail od\n"
            + "LEFT join tblProduct p\n"
            + "ON od.productID = p.productID\n"
            + "WHERE od.orderID = ? AND p.productName like ?";

    public List<Comestic> SearchListProduct(String search) throws SQLException {
        List<Comestic> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    double productPrice = rs.getDouble("productPrice");
                    int productQuantity = rs.getInt("productQuantity");
                    String productImg = rs.getString("productImg");
                    boolean Status = rs.getBoolean("Status");
                    if (Status == true) {
                        listProduct.add(new Comestic(productID, productName, productPrice, productQuantity, productImg, Status));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return listProduct;
    }

    public boolean updateProduct(Comestic comestic) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, comestic.getName());
                ptm.setDouble(2, comestic.getPrice());
                ptm.setInt(3, comestic.getQuantity());
                ptm.setString(4, comestic.getImg());
                ptm.setString(5, comestic.getId());
                check = ptm.executeUpdate() > 0 ? true : false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

    public boolean deleteProduct(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setBoolean(1, false);
                ptm.setString(2, productID);
                check = ptm.executeUpdate() > 0 ? true : false;

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

    public boolean insertProduct(Comestic comestic) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, comestic.getId());
                ptm.setString(2, comestic.getName());
                ptm.setDouble(3, comestic.getPrice());
                ptm.setInt(4, comestic.getQuantity());
                ptm.setString(5, comestic.getImg());
                ptm.setBoolean(6, true);
                check = ptm.executeUpdate() > 0 ? true : false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

    public boolean CheckProductID(String productID) throws SQLException {
        boolean check = true;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECKPRODUCTID);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<Comestic> ListProduct() throws SQLException {
        List<Comestic> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETLISTPRODUCT);
                ptm.setString(1, "%" + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    double productPrice = rs.getDouble("productPrice");
                    int productQuantity = rs.getInt("productQuantity");
                    String productImg = rs.getString("productImg");
                    boolean Status = rs.getBoolean("Status");
                    if (Status == true) {
                        listProduct.add(new Comestic(productID, productName, productPrice, productQuantity, productImg, Status));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return listProduct;
    }

    public String insertCheckout(String userID, Cart cart, double total) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        String orderID = null;
        String orderDertailsID = null;
        List<Comestic> ListProduct = ListProduct();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                //insert into table Order
                ptm = conn.prepareStatement(INSERTORDER);
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                orderID = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
                ptm.setString(1, orderID);
                ptm.setString(2, userID);
                ptm.setDate(3, sqlDate);
                ptm.setDouble(4, total);
                check = ptm.executeUpdate() > 0 ? true : false;
                if (check == false) {
                    return null;
                } else {
                    if (ptm != null) {
                        ptm.close();
                    }
                }

                //insert into table orderDetails
                //check Quantity
                for (Comestic ca : cart.getCart().values()) {
                    for (Comestic pro : ListProduct) {
                        if (ca.getId().equals(pro.getId())) {
                            if (ca.getQuantity() > pro.getQuantity()) {
                                return null;
                            }
                        }
                    }
                }

                //insert
                for (Comestic co : cart.getCart().values()) {
                    orderDertailsID = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
                    ptm = conn.prepareStatement(INSERTORDERDETAILS);
                    ptm.setString(1, orderDertailsID);
                    ptm.setString(2, orderID);
                    ptm.setString(3, co.getId());
                    ptm.setDouble(4, co.getPrice());
                    ptm.setInt(5, co.getQuantity());
                    check = ptm.executeUpdate() > 0 ? true : false;
                    if (check == false) {
                        return null;
                    }
                    if (ptm != null) {
                        ptm.close();
                    }

                    //update new quantity for product
                    ptm = conn.prepareStatement(UPDATEPRODUCT);
                    for (Comestic cos : ListProduct) {
                        if (cos.getId().equals(co.getId())) {
                            ptm.setInt(1, cos.getQuantity() - co.getQuantity());
                            ptm.setString(2, cos.getId());
                            check = ptm.executeUpdate() > 0 ? true : false;
                            if (check == false) {
                                return null;
                            } else {
                                if (ptm != null) {
                                    ptm.close();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return orderID;
    }

    public List<OrderManage> ListOrderManage() throws SQLException {
        List<OrderManage> listOder = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETORDER_MANAGE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderid = rs.getString("orderID");
                    String userid = rs.getString("userID");
                    String fullname = rs.getString("fullName");
                    Date date = rs.getDate("date");
                    double total = rs.getDouble("total");
                    OrderManage order = new OrderManage(orderid, userid, fullname, date, total);
                    listOder.add(order);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return listOder;
    }

    public List<OrderManage> SearchListOrder(String search) throws SQLException {
        List<OrderManage> ListOrder = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_ORDER_MANAGE);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderid = rs.getString("orderID");
                    String userid = rs.getString("userID");
                    String fullname = rs.getString("fullName");
                    Date date = rs.getDate("date");
                    double total = rs.getDouble("total");
                    OrderManage order = new OrderManage(orderid, userid, fullname, date, total);
                    ListOrder.add(order);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return ListOrder;
    }
    
        public List<OrderManage> OrderUser(String UserID) throws SQLException {
        List<OrderManage> ListOrder = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_ORDER_USER);
                ptm.setString(1, UserID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderid = rs.getString("orderID");
                    String userid = rs.getString("userID");
                    String fullname = rs.getString("fullName");
                    Date date = rs.getDate("date");
                    double total = rs.getDouble("total");
                    OrderManage order = new OrderManage(orderid, userid, fullname, date, total);
                    ListOrder.add(order);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return ListOrder;
    }

    public List<OrderDetails> ListOrderDetails(String orderID) throws SQLException {
        List<OrderDetails> listOder = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETORDERDETAILS);
                ptm.setString(1, orderID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderid = rs.getString("orderID");
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    float productPrice = rs.getFloat("producPrice");
                    int productQuantity = rs.getInt("productQuantity");
                    OrderDetails order = new OrderDetails(orderid, productID, productName, productPrice, productQuantity);
                    listOder.add(order);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return listOder;
    }

    public void UpdateQuantity(String orderID, String ProductID, int Quantity) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int newquantity = 0;
        float newTotal = 0;
        float current_total = 0;
        float resuil = 0;
        int resuil_quantity = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_QUANRITY_PRICE);
                ptm.setString(1, orderID);
                ptm.setString(2, ProductID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    float productPrice = rs.getFloat("producPrice");
                    int productQuantity = rs.getInt("productQuantity");
                    newquantity = Quantity - productQuantity;
                    newTotal = newquantity * productPrice;
                }
            }
            if (conn != null) {
                ptm = conn.prepareStatement(GET_QUANTITY_PRODUCT);
                ptm.setString(1, ProductID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int productQuantity = rs.getInt("productQuantity");
                    resuil_quantity = productQuantity - newquantity;
                }
            }
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATEPRODUCT);
                ptm.setInt(1, resuil_quantity);
                ptm.setString(2, ProductID);
                ptm.executeUpdate();
            }
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_ORDER_DETAILS);
                ptm.setInt(1, Quantity);
                ptm.setString(2, orderID);
                ptm.setString(3, ProductID);
                ptm.executeUpdate();
            }
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TOTAL);
                ptm.setString(1, orderID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    current_total = rs.getFloat("total");
                    resuil = newTotal + current_total;
                }
            }
            if (conn != null) {
                ptm = conn.prepareStatement(SET_TOTAL);
                ptm.setFloat(1, resuil);
                ptm.setString(2, orderID);
                ptm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void DeleteOrderdetails(String orderID, String ProductID, int Quantity, float price) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        float newTotal = 0;
        float current_total = Quantity * price;
        float resuil = 0;
        int resuil_quantity = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_ORDER_DETAILS);
                ptm.setString(1, orderID);
                ptm.setString(2, ProductID);
                ptm.executeUpdate();
            }
            if (conn != null) {
                ptm = conn.prepareStatement(GET_QUANTITY_PRODUCT);
                ptm.setString(1, ProductID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int productQuantity = rs.getInt("productQuantity");
                    resuil_quantity = productQuantity + Quantity;
                }
            }
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATEPRODUCT);
                ptm.setInt(1, resuil_quantity);
                ptm.setString(2, ProductID);
                ptm.executeUpdate();
            }
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TOTAL);
                ptm.setString(1, orderID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    newTotal = rs.getFloat("total");
                    resuil = newTotal - current_total;
                }
            }
            if (conn != null) {
                ptm = conn.prepareStatement(SET_TOTAL);
                ptm.setFloat(1, resuil);
                ptm.setString(2, orderID);
                ptm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<OrderDetails> SearchListOrderDetails(String search, String orderID) throws SQLException {
        List<OrderDetails> ListOrder = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_ORDER_DETAILS);
                ptm.setString(1, orderID);
                ptm.setString(2, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderid = rs.getString("orderID");
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    float producPrice = rs.getFloat("producPrice");
                    int productQuantity = rs.getInt("productQuantity");
                    OrderDetails order = new OrderDetails(orderid, productID, productName, producPrice, productQuantity);
                    ListOrder.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return ListOrder;
    }

    public boolean AddProductDetais(String orderID, String productID, int quantity) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        float producPrice = 0;
        float total_new = 0;
        float total_cur = 0;
        float resuil = 0;
        int resuil_quantity = 0;
        String oderdetailsID = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETPRODUCT);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    producPrice = rs.getFloat("productPrice");
                    total_new = producPrice * quantity;
                } else {
                    return false;
                }
            }
            if (conn != null) {
                ptm = conn.prepareStatement(GET_QUANTITY_PRODUCT);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int productQuantity = rs.getInt("productQuantity");
                    resuil_quantity = productQuantity - quantity;
                }
            }
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATEPRODUCT);
                ptm.setInt(1, resuil_quantity);
                ptm.setString(2, productID);
                ptm.executeUpdate();
            }
            if (conn != null) {
                ptm = conn.prepareStatement(INSERTORDERDETAILS);
                ptm.setString(1, oderdetailsID);
                ptm.setString(2, orderID);
                ptm.setString(3, productID);
                ptm.setFloat(4, producPrice);
                ptm.setInt(5, quantity);
                ptm.executeUpdate();
            }
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TOTAL);
                ptm.setString(1, orderID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    total_cur = rs.getFloat("total");
                    resuil = total_cur + total_new;
                }
            }
            if (conn != null) {
                ptm = conn.prepareStatement(SET_TOTAL);
                ptm.setFloat(1, resuil);
                ptm.setString(2, orderID);
                ptm.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return true;
    }

    public boolean DeleteOrder(String orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_ORDER);
                ptm.setString(1, orderID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

    public Comestic ProductDetails(String productID) throws SQLException {
        Comestic comestic = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETPRODUCT_DETAILS);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String productName = rs.getString("productName");
                    float productPrice = rs.getFloat("productPrice");
                    int productQuantity = rs.getInt("productQuantity");
                    String productImg = rs.getString("productImg");
                    comestic = new Comestic(null, productName, productPrice, productQuantity, productImg, true);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return comestic;
    }

}
