/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.OrderDetails;
import sample.shopping.OrderManage;
import sample.shopping.ShoppingDAO;
import sample.users.UserDTO;

/**
 *
 * @author Admin
 */
public class ViewOrderDetailsController extends HttpServlet {

    private static final String ERROR = "ViewOrderDetails.jsp";
    private static final String SUCCESS = "ViewOrderDetails.jsp";
    
    private static final String ORDER_DETAILS_USER_PAGE = "OrderDetailsUser.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO)session.getAttribute("LOGIN_USER");
            String orderID = request.getParameter("orderID");
            List<OrderDetails> list = new ArrayList<>();
            ShoppingDAO dao = new ShoppingDAO();
            list  = dao.ListOrderDetails(orderID);
            if(list.size() > 0){
                request.setAttribute("LIST_ORDER_DETAILS", list);
                url = SUCCESS;
            }
            if(user.getRoleID().equals("US")){
                url = ORDER_DETAILS_USER_PAGE;
            }
        } catch (Exception e) {
            log("error at ManageOrderController" + e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
